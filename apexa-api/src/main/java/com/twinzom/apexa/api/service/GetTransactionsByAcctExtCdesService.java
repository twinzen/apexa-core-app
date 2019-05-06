package com.twinzom.apexa.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twinzom.apexa.api.model.Transaction;
import com.twinzom.apexa.api.support.SecurityPopulator;
import com.twinzom.apexa.api.support.TransactionMapper;
import com.twinzom.apexa.dao.AccountDao;
import com.twinzom.apexa.dao.TransactionDao;
import com.twinzom.apexa.dao.dto.AccountDto;
import com.twinzom.apexa.dao.dto.TransactionDto;

@Service
public class GetTransactionsByAcctExtCdesService {

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	TransactionMapper transactionMapper;
	
	@Autowired
	SecurityPopulator securityPopulator;
	
	public List<Transaction> process (String acctExtCde) throws InterruptedException, ExecutionException {
		
		/**
		 * convert external account id to internal account id
		 */
		//TODO: valid input account number list
		
		
		AccountDto acctDto = accountDao.getAcctByExtCdePri(acctExtCde);
		
		// Format acid list
		List<Long> acids = new ArrayList<Long>();
		Map<Long, String> acidAcctNumMap = new HashMap<Long, String>();
		acids.add(acctDto.getAcid());
		acidAcctNumMap.put(acctDto.getAcid(), acctDto.getAcctExtCdePri());
		
		// Get transactions from DB
		List<TransactionDto> txnDtos = transactionDao.getTxnsByAcctIds(acids);
		
		List<Transaction> txns = transactionMapper.mapObjects(txnDtos);
		
		Collections.sort(txns);
		
		securityPopulator.populateMultiple(txns);
		
		return txns;
	}
	
}
