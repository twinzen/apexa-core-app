package com.twinzom.apexa.dao.dto;

import java.util.Date;

public class AccountDto {

	private Long acid;
	private String acctExtCdePri;
	private String acctName;
	private String acctMthd;
	private String acctCcy;
	private Date strtDtTm;
	private Date termDtTm;
	private String acctStat;
	private Date crtDtTm;
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
