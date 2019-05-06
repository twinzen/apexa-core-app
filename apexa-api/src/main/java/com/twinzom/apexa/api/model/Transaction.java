package com.twinzom.apexa.api.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.twinzom.apexa.caleng.model.CalTxn;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction extends SecurityContainer implements Comparable<Transaction> {

	private Long txnid;
	
	private String txnTypeCde;
	
	private String extTxnRef;
	
	private String extTxnTypeCde;
	
	private Long acctExtCde;
	
	private Date trdDtTm;
	
	private Date postDtTm;
	
	private BigDecimal exePrc;
	
	private String prcCcy;
	
	private BigDecimal qty;
	
	private BigDecimal pripAmtLocl;
	
	private Date setlDtTm;

	private String setlCcy;
	
	private BigDecimal setlAmtSetl;
	
	private BigDecimal setlLoclRate;
	
	private String mktCde;
	
	private String srcSysCde;
	
	private Integer txnStat;
	
	private String longShtInd;
	
	private BigDecimal mktValLocl;
	
	private BigDecimal mktValAcct;
	
	private BigDecimal bkCostLocl;
	
	private BigDecimal bkCostAcct;
	
	private String extLotRef;


	public Long getTxnid() {
		return txnid;
	}


	public String getTxnTypeCde() {
		return txnTypeCde;
	}


	public String getExtTxnRef() {
		return extTxnRef;
	}


	public String getExtTxnTypeCde() {
		return extTxnTypeCde;
	}


	public Long getAcctExtCde() {
		return acctExtCde;
	}


	public Date getTrdDtTm() {
		return trdDtTm;
	}


	public Date getPostDtTm() {
		return postDtTm;
	}


	public BigDecimal getExePrc() {
		return exePrc;
	}


	public String getPrcCcy() {
		return prcCcy;
	}


	public BigDecimal getQty() {
		return qty;
	}


	public BigDecimal getPripAmtLocl() {
		return pripAmtLocl;
	}


	public Date getSetlDtTm() {
		return setlDtTm;
	}


	public String getSetlCcy() {
		return setlCcy;
	}


	public BigDecimal getSetlAmtSetl() {
		return setlAmtSetl;
	}


	public BigDecimal getSetlLoclRate() {
		return setlLoclRate;
	}


	public String getMktCde() {
		return mktCde;
	}


	public String getSrcSysCde() {
		return srcSysCde;
	}


	public Integer getTxnStat() {
		return txnStat;
	}


	public String getLongShtInd() {
		return longShtInd;
	}


	public BigDecimal getMktValLocl() {
		return mktValLocl;
	}


	public BigDecimal getMktValAcct() {
		return mktValAcct;
	}


	public BigDecimal getBkCostLocl() {
		return bkCostLocl;
	}


	public BigDecimal getBkCostAcct() {
		return bkCostAcct;
	}


	public String getExtLotRef() {
		return extLotRef;
	}


	public void setTxnid(Long txnid) {
		this.txnid = txnid;
	}


	public void setTxnTypeCde(String txnTypeCde) {
		this.txnTypeCde = txnTypeCde;
	}


	public void setExtTxnRef(String extTxnRef) {
		this.extTxnRef = extTxnRef;
	}


	public void setExtTxnTypeCde(String extTxnTypeCde) {
		this.extTxnTypeCde = extTxnTypeCde;
	}


	public void setAcctExtCde(Long acctExtCde) {
		this.acctExtCde = acctExtCde;
	}


	public void setTrdDtTm(Date trdDtTm) {
		this.trdDtTm = trdDtTm;
	}


	public void setPostDtTm(Date postDtTm) {
		this.postDtTm = postDtTm;
	}


	public void setExePrc(BigDecimal exePrc) {
		this.exePrc = exePrc;
	}


	public void setPrcCcy(String prcCcy) {
		this.prcCcy = prcCcy;
	}


	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}


	public void setPripAmtLocl(BigDecimal pripAmtLocl) {
		this.pripAmtLocl = pripAmtLocl;
	}


	public void setSetlDtTm(Date setlDtTm) {
		this.setlDtTm = setlDtTm;
	}


	public void setSetlCcy(String setlCcy) {
		this.setlCcy = setlCcy;
	}


	public void setSetlAmtSetl(BigDecimal setlAmtSetl) {
		this.setlAmtSetl = setlAmtSetl;
	}


	public void setSetlLoclRate(BigDecimal setlLoclRate) {
		this.setlLoclRate = setlLoclRate;
	}


	public void setMktCde(String mktCde) {
		this.mktCde = mktCde;
	}


	public void setSrcSysCde(String srcSysCde) {
		this.srcSysCde = srcSysCde;
	}


	public void setTxnStat(Integer txnStat) {
		this.txnStat = txnStat;
	}


	public void setLongShtInd(String longShtInd) {
		this.longShtInd = longShtInd;
	}


	public void setMktValLocl(BigDecimal mktValLocl) {
		this.mktValLocl = mktValLocl;
	}


	public void setMktValAcct(BigDecimal mktValAcct) {
		this.mktValAcct = mktValAcct;
	}


	public void setBkCostLocl(BigDecimal bkCostLocl) {
		this.bkCostLocl = bkCostLocl;
	}


	public void setBkCostAcct(BigDecimal bkCostAcct) {
		this.bkCostAcct = bkCostAcct;
	}


	public void setExtLotRef(String extLotRef) {
		this.extLotRef = extLotRef;
	}


	@Override
	public int compareTo(Transaction t) {
		return (this.trdDtTm.compareTo(t.trdDtTm));
	}
}
