package com.twinzom.apexa.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twinzom.apexa.api.model.AccountHoldings;
import com.twinzom.apexa.api.model.Transaction;
import com.twinzom.apexa.api.service.GetHoldingsByAcctExtCdesService;
import com.twinzom.apexa.api.service.GetTransactionsByAcctExtCdesService;

@RestController
public class TransactionController {

	private static final Logger logger = LogManager.getLogger(TransactionController.class);

	@Autowired
	GetTransactionsByAcctExtCdesService getTransactionsByAcctExtCdesService;
	
	@RequestMapping(value="/transactions/{acctExtCdes}", method=RequestMethod.GET)
    public List<Transaction> getByAcctExtCdes (
    			@PathVariable List<String> acctExtCdes, 
	    		Date startDate,
	    		Date endDate,
	    		List<String> txnTypeCdes) {
		
		
		List<Transaction> txns = new ArrayList<Transaction>();
		
		try {
			txns = getTransactionsByAcctExtCdesService.process(acctExtCdes.get(0));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        return txns;
    }
	
}
