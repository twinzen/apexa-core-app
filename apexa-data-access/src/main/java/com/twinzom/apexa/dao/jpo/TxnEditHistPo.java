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
@Table(name = "TT_EDIT_HIST")
public class TxnEditHistPo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long tehid;
	
	@Column(nullable = false)
	private Long txnid;
	
	@Column(nullable = false)
	private Date expDtTm;
	
	private BigDecimal exePrc;
	
	private BigDecimal qty;
	
	private BigDecimal pripAmtLocl;
	
	private Date setlDtTm;
	
	private BigDecimal setlAmtSetl;
	
	private BigDecimal setlLoclRate;
	
	private Date setlLoclRateDtTm;
	
	private BigDecimal mktValLocl;
	
	private BigDecimal mktValAcct;
	
	private BigDecimal mktValBase;
	
	private BigDecimal bkCostLocl;
	
	private BigDecimal bkCostAcct;
	
	private BigDecimal bkCostBase;
	
	private String narrTxt;
	
	private String txnRmk;
	
	private String editRmkTxt;
	
	private String editStafId;
	
	@Column(nullable = false)
	private Date crtDtTm;
	
	@Column(nullable = false)
	private Date updtDtTm;

	public Long getTehid() {
		return tehid;
	}

	public void setTehid(Long tehid) {
		this.tehid = tehid;
	}

	public Long getTxnid() {
		return txnid;
	}

	public void setTxnid(Long txnid) {
		this.txnid = txnid;
	}

	public Date getExpDtTm() {
		return expDtTm;
	}

	public void setExpDtTm(Date expDtTm) {
		this.expDtTm = expDtTm;
	}

	public BigDecimal getExePrc() {
		return exePrc;
	}

	public void setExePrc(BigDecimal exePrc) {
		this.exePrc = exePrc;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getPripAmtLocl() {
		return pripAmtLocl;
	}

	public void setPripAmtLocl(BigDecimal pripAmtLocl) {
		this.pripAmtLocl = pripAmtLocl;
	}

	public Date getSetlDtTm() {
		return setlDtTm;
	}

	public void setSetlDtTm(Date setlDtTm) {
		this.setlDtTm = setlDtTm;
	}

	public BigDecimal getSetlAmtSetl() {
		return setlAmtSetl;
	}

	public void setSetlAmtSetl(BigDecimal setlAmtSetl) {
		this.setlAmtSetl = setlAmtSetl;
	}

	public BigDecimal getSetlLoclRate() {
		return setlLoclRate;
	}

	public void setSetlLoclRate(BigDecimal setlLoclRate) {
		this.setlLoclRate = setlLoclRate;
	}

	public Date getSetlLoclRateDtTm() {
		return setlLoclRateDtTm;
	}

	public void setSetlLoclRateDtTm(Date setlLoclRateDtTm) {
		this.setlLoclRateDtTm = setlLoclRateDtTm;
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

	public BigDecimal getMktValBase() {
		return mktValBase;
	}

	public void setMktValBase(BigDecimal mktValBase) {
		this.mktValBase = mktValBase;
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

	public BigDecimal getBkCostBase() {
		return bkCostBase;
	}

	public void setBkCostBase(BigDecimal bkCostBase) {
		this.bkCostBase = bkCostBase;
	}

	public String getNarrTxt() {
		return narrTxt;
	}

	public void setNarrTxt(String narrTxt) {
		this.narrTxt = narrTxt;
	}

	public String getTxnRmk() {
		return txnRmk;
	}

	public void setTxnRmk(String txnRmk) {
		this.txnRmk = txnRmk;
	}

	public String getEditRmkTxt() {
		return editRmkTxt;
	}

	public void setEditRmkTxt(String editRmkTxt) {
		this.editRmkTxt = editRmkTxt;
	}

	public String getEditStafId() {
		return editStafId;
	}

	public void setEditStafId(String editStafId) {
		this.editStafId = editStafId;
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
