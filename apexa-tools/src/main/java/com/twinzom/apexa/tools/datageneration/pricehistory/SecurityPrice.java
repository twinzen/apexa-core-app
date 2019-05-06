package com.twinzom.apexa.tools.datageneration.pricehistory;

public class SecurityPrice {

	private Long secid;
	
	private String secCde;
	
	private String Ccy;
	
	private Double prc;

	public Long getSecid() {
		return secid;
	}

	public void setSecid(Long secid) {
		this.secid = secid;
	}

	public String getSecCde() {
		return secCde;
	}

	public void setSecCde(String secCde) {
		this.secCde = secCde;
	}

	public String getCcy() {
		return Ccy;
	}

	public void setCcy(String ccy) {
		Ccy = ccy;
	}

	public Double getPrc() {
		return prc;
	}

	public void setPrc(Double prc) {
		this.prc = prc;
	}
	
}
