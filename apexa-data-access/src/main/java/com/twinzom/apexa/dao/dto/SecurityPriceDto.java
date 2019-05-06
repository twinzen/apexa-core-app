package com.twinzom.apexa.dao.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SecurityPriceDto {

	
	private BigDecimal mktPrc;

	private Date effDtTm;

	private Date expDtTm;

	public BigDecimal getMktPrc() {
		return mktPrc;
	}
	public void setMktPrc(BigDecimal mktPrc) {
		this.mktPrc = mktPrc;
	}
	public Date getEffDtTm() {
		return effDtTm;
	}
	public void setEffDtTm(Date effDtTm) {
		this.effDtTm = effDtTm;
	}
	public Date getExpDtTm() {
		return expDtTm;
	}
	public void setExpDtTm(Date expDtTm) {
		this.expDtTm = expDtTm;
	}
	
}
