package com.twinzom.apexa.dao.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionHistoryDto {

	private Long txnehid;
	private Date expDtTm;
	private BigDecimal exePrc;
	private BigDecimal qty;
	private BigDecimal pripAmtLocl;
	private Date setlDtTm;
	private BigDecimal setlAmtSetl;
	private BigDecimal setlLoclRate;
	private BigDecimal mktValLocl;
	private BigDecimal mktValAcct;
	private BigDecimal mktValBase;
	private BigDecimal bkCostLocl;
	private BigDecimal bkCostAcct;
	private BigDecimal bkCostBase;
	private String narrTxt;
	private String editTypeCde;
	private String editRmkTxt;
	private String editStafId;
	
	public Long getTxnehid() {
		return txnehid;
	}
	public void setTxnehid(Long txnehid) {
		this.txnehid = txnehid;
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
	public String getEditTypeCde() {
		return editTypeCde;
	}
	public void setEditTypeCde(String editTypeCde) {
		this.editTypeCde = editTypeCde;
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
	
}
