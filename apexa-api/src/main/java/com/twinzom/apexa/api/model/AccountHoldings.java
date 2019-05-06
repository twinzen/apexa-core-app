package com.twinzom.apexa.api.model;

import java.util.List;
import java.util.Map;

public class AccountHoldings {

	private String acctNum;
	
	private Map<String, List<Holding>> holdingGroup;

	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public Map<String, List<Holding>> getHoldingGroup() {
		return holdingGroup;
	}

	public void setHoldingGroup(Map<String, List<Holding>> holdingGroup) {
		this.holdingGroup = holdingGroup;
	}


	
	
	
}
