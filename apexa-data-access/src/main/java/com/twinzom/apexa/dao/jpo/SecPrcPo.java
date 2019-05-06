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
@Table(name = "TT_SEC_PRC")
public class SecPrcPo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long sprcid;
	
	@Column(nullable = false)
	private Long secid;
	
	@Column(nullable = false)
	private BigDecimal mktPrc;
	
	@Column(nullable = false)
	private Date effDtTm;
	
	@Column(nullable = false)
	private Date expDtTm;
	
	@Column(nullable = false)
	private Date crtDtTm;
	
	@Column(nullable = false)
	private Date updtDtTm;

	public Long getSprcid() {
		return sprcid;
	}

	public void setSprcid(Long sprcid) {
		this.sprcid = sprcid;
	}

	public Long getSecid() {
		return secid;
	}

	public void setSecid(Long secid) {
		this.secid = secid;
	}

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
