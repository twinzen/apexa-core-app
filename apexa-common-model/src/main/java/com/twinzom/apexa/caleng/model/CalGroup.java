package com.twinzom.apexa.caleng.model;

import java.util.List;

public class CalGroup {

	private String groupId;
	
	private CalPosition position;
	
	private List<CalTxn> txns;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public CalPosition getPosition() {
		return position;
	}

	public void setPosition(CalPosition position) {
		this.position = position;
	}

	public List<CalTxn> getTxns() {
		return txns;
	}

	public void setTxns(List<CalTxn> txns) {
		this.txns = txns;
	}
	
	
	
}
