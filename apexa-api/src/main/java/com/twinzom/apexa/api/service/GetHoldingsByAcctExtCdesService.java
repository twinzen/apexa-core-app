package com.twinzom.apexa.api.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twinzom.apexa.api.model.AccountHoldings;
import com.twinzom.apexa.api.model.Holding;
import com.twinzom.apexa.api.model.Security;
import com.twinzom.apexa.api.remoteclient.CalEngClient;
import com.twinzom.apexa.api.support.TransactionMapper;
import com.twinzom.apexa.caleng.model.CalGroup;
import com.twinzom.apexa.caleng.model.CalPosition;
import com.twinzom.apexa.caleng.model.CalResponse;
import com.twinzom.apexa.caleng.model.CalSnapshot;
import com.twinzom.apexa.caleng.model.CalTxn;
import com.twinzom.apexa.dao.AccountDao;
import com.twinzom.apexa.dao.SecurityDao;
import com.twinzom.apexa.dao.TransactionDao;
import com.twinzom.apexa.dao.dto.AccountDto;
import com.twinzom.apexa.dao.dto.SecurityDto;
import com.twinzom.apexa.dao.dto.TransactionDto;

@Service
public class GetHoldingsByAcctExtCdesService {

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	SecurityDao securityDao;
	
	@Autowired
	CalEngClient calEngClient;
	
	@Autowired
	TransactionMapper transactionMapper;
	
	private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public AccountHoldings process (String acctNum, String datesStr) throws InterruptedException, ExecutionException {
		
		/**
		 * convert external account id to internal account id
		 */
		//TODO: valid input account number list

		AccountDto acctDto = accountDao.getAcctByExtCdePri(acctNum);

		// Format acid list
		List<Long> acids = new ArrayList<Long>();
		acids.add(acctDto.getAcid());
		Map<Long, String> acidAcctNumMap = new HashMap<Long, String>();
		acidAcctNumMap.put(acctDto.getAcid(), acctDto.getAcctExtCdePri());
		
		// Get transactions from DB
		List<TransactionDto> txnDtos = transactionDao.getTxnsByAcctIds(acids);
		
		/*
		 *  Group txn by account first, then by security
		 *  Map
		 *   |-Account_A 
		 *   |  |-Security_X
		 *   |  |  |-Txn_1
		 *   |  |  |-Txn_2
		 *   |  |
		 *   |  |-Security_Y
		 *   |     |-Txn_3
		 *   |     |-Txn_4
		 *   |
		 *   |-Account_B
		 *      |-Security_P
		 *      |  |-Txn_5
		 *      |  |-Txn_6
		 *      |
		 *      |-Security_Q
		 *         |-Txn_7
		 *         |-Txn_8 
		 */
		Map<Long, Map<Long, List<TransactionDto>>> groupByAcidSecidMap = 
				txnDtos.stream().collect(Collectors.groupingBy(TransactionDto::getAcid, Collectors.groupingBy(TransactionDto::getSecid)));
		
		// Prepare to call calculation engine parallel
		// Each thread manage one account
		List<CompletableFuture<CalResponse>> pages = new ArrayList<CompletableFuture<CalResponse>>();
		
		for (Long acid : groupByAcidSecidMap.keySet()) {
			Map<Long, List<TransactionDto>> groupBySecidMap = groupByAcidSecidMap.get(acid);
			List<CalGroup> calGroups = formatCalculateHoldingSnapshotsBody(groupBySecidMap);
			pages.add(calEngClient.callCalculateHoldingSnapshots(calGroups, datesStr));
		}
		
		// Wait all thread complete
		CompletableFuture.allOf(pages.toArray(new CompletableFuture[pages.size()])).join();
		
		CalResponse calResponse = pages.get(0).get();
		
		Map<String, List<Holding>> holdingGroup = groupHoldingsByDate(calResponse);
		
		populateSecurityMkVal(holdingGroup);
		
		AccountHoldings acctHldgs = new AccountHoldings();
		
		acctHldgs.setAcctNum(acctNum);
		
		acctHldgs.setHoldingGroup(holdingGroup);
		
//		ObjectMapper om = new ObjectMapper();
//		try {
//			System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(acctHldgs));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return acctHldgs;
	}
	
	private void populateSecurityMkVal (Map<String, List<Holding>> holdingGroup) {
		
		List<Long> secids = new ArrayList<Long>();
		
		// Exclude those holdings at zero quantity 
		for (List<Holding> holdings : holdingGroup.values()) {
			List<Holding> zeroHoldings = new ArrayList<Holding>();
			for (Holding h : holdings) {
				if (h.getQty().compareTo(BigDecimal.ZERO) == 0)
					zeroHoldings.add(h);
				else 
					secids.add(h.getSecid());
			}
			holdings.removeAll(zeroHoldings);
		}
		
		List<SecurityDto> secDtos = securityDao.getSecsWithPriceBySecids(secids);
		
		Map<Long, Security> secs = convertSecDtoToSec (secDtos);
		
		Map<String, Double> rates = getRates();
		
		for (List<Holding> holdings : holdingGroup.values()) {
			for (Holding h : holdings) {
				Security s = secs.get(h.getSecid());
				h.setSecurity(s);
				System.out.println(h.getQty().multiply(s.getPrice()));
				h.setMktValLocl(h.getQty().multiply(s.getPrice()));
				h.setMktValAcct(h.getQty().multiply(s.getPrice()).multiply(BigDecimal.valueOf(rates.get(s.getCcy()))));
			}
		}
		
	}
	
	private Map<Long, Security> convertSecDtoToSec (List<SecurityDto> dtos) {
		
		Map<Long, Security> secs = new HashMap<Long, Security>();
		
		for (SecurityDto dto : dtos) {
			Security sec = new Security();
			sec.setSecid(dto.getSecid());
			sec.setCode(dto.getSecCdePri());
			sec.setName(dto.getName());
			sec.setCcy(dto.getLoclCcy());
			System.out.println(sec.getSecid());
			sec.setPrice(dto.getSecPrc().getMktPrc());
			secs.put(sec.getSecid(), sec);
		}
		
		return secs;
		
	}
	
	private Map<String, List<Holding>> groupHoldingsByDate (CalResponse calResponse) {
		
		Map<String, List<Holding>> groupByDate = new HashMap<String, List<Holding>>();
		
		for (String secidStr : calResponse.getSnapshotsByGroup().keySet()) {
			
			List<CalSnapshot> snapshots = calResponse.getSnapshotsByGroup().get(secidStr);
			
			for (CalSnapshot snapshot : snapshots) {
				
				CalPosition p = snapshot.getPosition();
				
				List<Holding> holdings = groupByDate.get(snapshot.getSnapshotKey());
				if (holdings == null) {
					holdings = new ArrayList<Holding>();
					groupByDate.put(snapshot.getSnapshotKey(), holdings);
				}
				Holding h = new Holding();
				h.setSecid(Long.parseLong(secidStr));
				h.setQty(p.getQty());
				h.setBkCostLocl(p.getBkCostLocl());
				h.setBkCostAcct(p.getBkCostAcct());
				holdings.add(h);
			}
		}
		
		return groupByDate;
		
	}
	
	private List<CalGroup> formatCalculateHoldingSnapshotsBody (Map<Long, List<TransactionDto>> groupBySecidMap) {
		
		List<CalGroup> groups = new ArrayList<CalGroup>();
		
		for (Long secid: groupBySecidMap.keySet()) {
			CalGroup g = new CalGroup();
			g.setGroupId(String.valueOf(secid));
			
			List<CalTxn> calTxns = new ArrayList<CalTxn>();
			g.setTxns(calTxns);
			
			List<TransactionDto> txns = groupBySecidMap.get(secid);
			
			for (TransactionDto t : txns) {
				CalTxn calTxn = new CalTxn();
				calTxn.setId(String.valueOf(t.getTxnid()));
				calTxn.setTxnTypeCde(t.getTxnTypeCde());
				calTxn.setTrdDtTm(t.getTrdDtTm());
				calTxn.setPrc(t.getExePrc());
				calTxn.setPrcCcy(t.getPrcCcy());
				calTxn.setQty(t.getQty());
				calTxn.setPripLocl(t.getPripAmtLocl());
				calTxn.setSetlDtTm(t.getSetlDtTm());
				calTxn.setSetlAmtSetl(t.getSetlAmtSetl());
				calTxn.setSetlCcy(t.getSetlCcy());
				calTxn.setMktValLocl(t.getMktValLocl());
				calTxn.setMktValAcct(t.getMktValAcct());
				calTxn.setBkCostLocl(t.getBkCostLocl());
				calTxn.setBkCostAcct(t.getBkCostAcct());
				calTxns.add(calTxn);
			}
			groups.add(g);
		}
	
		return groups;
	}
	
	private Map<String, Double> getRates () {
		Map<String, Double> rateSeed = new HashMap<String, Double>();
		
		rateSeed.put("USD", 7.84990973);
		rateSeed.put("EUR", 8.82691 );
		rateSeed.put("AUD", 5.58616 );
		rateSeed.put("SGD", 5.80404 );
		rateSeed.put("GBP", 10.3373 );
		rateSeed.put("RMB", 1.17005 );
		rateSeed.put("NZD", 5.33381 );
		rateSeed.put("CAD", 5.90015 );
		rateSeed.put("CNY", 1.17005 );
		rateSeed.put("JPY", 0.0703964 );
		rateSeed.put("HKD", 1.0 );
		
		return rateSeed;
	}
	
}
