package com.twinzom.apexa.dao.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FxRateDto {

	private Long fxid;
	private String fromCcy;
	private String toCcy;
	private BigDecimal rate;
	private Date effDtTm;
	private Date expDtTm;
	private Date postDtTm;
	
	public Long getFxid() {
		return fxid;
	}
	public void setFxid(Long fxid) {
		this.fxid = fxid;
	}
	public String getFromCcy() {
		return fromCcy;
	}
	public void setFromCcy(String fromCcy) {
		this.fromCcy = fromCcy;
	}
	public String getToCcy() {
		return toCcy;
	}
	public void setToCcy(String toCcy) {
		this.toCcy = toCcy;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
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
	public Date getPostDtTm() {
		return postDtTm;
	}
	public void setPostDtTm(Date postDtTm) {
		this.postDtTm = postDtTm;
	}
	
}
