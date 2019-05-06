package com.twinzom.apexa.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Holding {

	private Security security;
	
	private Long secid;
	
	private BigDecimal qty;
	
	private BigDecimal mktValLocl;
	
	private BigDecimal mktValAcct;
	
	private BigDecimal bkCostLocl;
	
	private BigDecimal bkCostAcct;
	
	private BigDecimal uglLocl;
	
	private BigDecimal uglAcct;

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public Long getSecid() {
		return secid;
	}

	public void setSecid(Long secid) {
		this.secid = secid;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public BigDecimal getMktValLocl() {
		return mktValLocl;
	}

	public void setMktValLocl(BigDecimal mktValLocl) {
		this.mktValLocl = mktValLocl;
	}

	public BigDecimal getMktValAcct() {
		return mktValAcct;
	}

	public void setMktValAcct(BigDecimal mktValAcct) {
		this.mktValAcct = mktValAcct;
	}

	public BigDecimal getBkCostLocl() {
		return bkCostLocl;
	}

	public void setBkCostLocl(BigDecimal bkCostLocl) {
		this.bkCostLocl = bkCostLocl;
	}

	public BigDecimal getBkCostAcct() {
		return bkCostAcct;
	}

	public void setBkCostAcct(BigDecimal bkCostAcct) {
		this.bkCostAcct = bkCostAcct;
	}

	public BigDecimal getUglLocl() {
		return uglLocl;
	}

	public void setUglLocl(BigDecimal uglLocl) {
		this.uglLocl = uglLocl;
	}

	public BigDecimal getUglAcct() {
		return uglAcct;
	}

	public void setUglAcct(BigDecimal uglAcct) {
		this.uglAcct = uglAcct;
	}
	
	
	
}
