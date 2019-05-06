package com.twinzom.apexa.tools.datageneration.ratehistory;

import java.util.Date;

public class RateHistory {

	private String Ccy;
	
	private Double rate;
	
	private Date rateDt;

	public String getCcy() {
		return Ccy;
	}

	public void setCcy(String ccy) {
		Ccy = ccy;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getRateDt() {
		return rateDt;
	}

	public void setRateDt(Date rateDt) {
		this.rateDt = rateDt;
	}
	
	
}
