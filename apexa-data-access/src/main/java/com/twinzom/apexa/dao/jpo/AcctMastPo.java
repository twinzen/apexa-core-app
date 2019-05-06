package com.twinzom.apexa.dao.jpo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TT_ACCT_MAST")
public class AcctMastPo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long acid;
	
	@Column(nullable = false)
	private String acctExtCdePri;
	
	private String acctName;
	
	@Column(nullable = false)
	private String acctMthd;
	
	@Column(nullable = false)
	private String acctCcy;
	
	@Column(nullable = false)
	private Date strtDtTm;
	
	@Column(nullable = false)
	private Date termDtTm;
	
	@Column(nullable = false)
	private String acctStat;
	
	@Column(nullable = false)
	private Date crtDtTm;
	
	@Column(nullable = false)
	private Date updtDtTm;
	
	public Long getAcid() {
		return acid;
	}
	public void setAcid(Long acid) {
		this.acid = acid;
	}
	public String getAcctExtCdePri() {
		return acctExtCdePri;
	}
	public void setAcctExtCdePri(String acctExtCdePri) {
		this.acctExtCdePri = acctExtCdePri;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getAcctMthd() {
		return acctMthd;
	}
	public void setAcctMthd(String acctMthd) {
		this.acctMthd = acctMthd;
	}
	public String getAcctCcy() {
		return acctCcy;
	}
	public void setAcctCcy(String acctCcy) {
		this.acctCcy = acctCcy;
	}
	public Date getStrtDtTm() {
		return strtDtTm;
	}
	public void setStrtDtTm(Date strtDtTm) {
		this.strtDtTm = strtDtTm;
	}
	public Date getTermDtTm() {
		return termDtTm;
	}
	public void setTermDtTm(Date termDtTm) {
		this.termDtTm = termDtTm;
	}
	public String getAcctStat() {
		return acctStat;
	}
	public void setAcctStat(String acctStat) {
		this.acctStat = acctStat;
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
