package com.twinzom.apexa.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinzom.apexa.api.model.AccountHoldings;
import com.twinzom.apexa.api.model.Holding;
import com.twinzom.apexa.api.remoteclient.CalEngClient;
import com.twinzom.apexa.api.service.GetHoldingsByAcctExtCdesService;
import com.twinzom.apexa.dao.AccountDao;
import com.twinzom.apexa.dao.TransactionDao;
import com.twinzom.apexa.dao.jpo.AcctMastPo;

@RestController
public class HoldingController {

	private static final Logger logger = LogManager.getLogger(HoldingController.class);
	
	@Autowired
	GetHoldingsByAcctExtCdesService getHoldingsByAccNumsService;
	
//	public String greeting() {
//		
//		List<CompletableFuture<String>> pages = new ArrayList<CompletableFuture<String>>();
//		
//		for (int i=0; i < 200; i++) {
//			pages.add(calEngClient.callCalculateHoldingSnapshots("Twinsen"+i));
//		}
//		
//		CompletableFuture.allOf(pages.toArray(new CompletableFuture[pages.size()])).join();
//		
//		return "HelloWorld";
//		
//	}
	
	@RequestMapping(value="/holding/getByAcctNums", method=RequestMethod.GET)
    public AccountHoldings getByAcctNums (
	    		String acctNums, 
	    		String dates
    		) {
		
		/**TODO 001
		 * DONE: 001.01: convert external account id to internal account id
		 * 001.02: get all transactions of given account
		 * 001.03: group transactions by product id
		 * 001.04: call calculation engine per transaction group (per product id)
		 * 001.05: wait all calls replied
		 * 001.06: consolidate all replies and construct response
		 * 
		 */
		
		List<String> acctNumsList = Arrays.asList(acctNums.split(","));
		
		AccountHoldings acctHldgs= null;
		try {
			acctHldgs = getHoldingsByAccNumsService.process(acctNumsList.get(0), dates);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        return acctHldgs;
    }
	
}


		
		
