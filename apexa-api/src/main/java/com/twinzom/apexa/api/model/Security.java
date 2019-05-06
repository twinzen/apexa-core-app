package com.twinzom.apexa.api.model;

import java.math.BigDecimal;

public class Security {

	private Long secid;
	
	private String code;
	
	private String name;
	
	private String ccy;
	
	private Integer riskLvl;
	
	private BigDecimal stdev;
	
	private BigDecimal annRtrn;
	
	private BigDecimal sharpe;
	
	private BigDecimal price;

	public Long getSecid() {
		return secid;
	}

	public void setSecid(Long secid) {
		this.secid = secid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Integer getRiskLvl() {
		return riskLvl;
	}

	public void setRiskLvl(Integer riskLvl) {
		this.riskLvl = riskLvl;
	}

	public BigDecimal getStdev() {
		return stdev;
	}

	public void setStdev(BigDecimal stdev) {
		this.stdev = stdev;
	}

	public BigDecimal getAnnRtrn() {
		return annRtrn;
	}

	public void setAnnRtrn(BigDecimal annRtrn) {
		this.annRtrn = annRtrn;
	}

	public BigDecimal getSharpe() {
		return sharpe;
	}

	public void setSharpe(BigDecimal sharpe) {
		this.sharpe = sharpe;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
	
	
	
	
}
