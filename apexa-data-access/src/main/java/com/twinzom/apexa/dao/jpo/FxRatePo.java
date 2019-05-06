package com.twinzom.apexa.dao.jpo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TT_FX_RATE")
public class FxRatePo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long fxid;
	
	@Column(nullable = false)
	private String fromCcy;
	
	@Column(nullable = false)
	private String toCcy;
	
	@Column(nullable = false)
	private BigDecimal rate;
	
	@Column(nullable = false)
	private Date effDtTm;
	
	@Column(nullable = false)
	private Date expDtTm;
	
	@Column(nullable = false)
	private Date crtDtTm;
	
	@Column(nullable = false)
	private Date updtDtTm;
	
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
	public Date getCrtDtTm() {
		return crtDtTm;
	}
	public void setCrtDtTm(Date crtDtTm) {
		this.crtDtTm = crtDtTm;
	}
	public Date getUpdtDtTm() {
		return updtDtTm;
	}
	public void setUpdtDtTm(Date updtDtTm) {
		this.updtDtTm = updtDtTm;
	}
}
