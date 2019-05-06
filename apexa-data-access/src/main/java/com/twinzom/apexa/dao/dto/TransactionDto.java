package com.twinzom.apexa.dao.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TransactionDto {

	private Long txnid;
	private String txnTypeCde;
	private String extTxnRef;
	private String extTxnTypeCde;
	private Long acid;
	private Long secid;
	private Date trdDtTm;
	private Integer tzdb;
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
	private BigDecimal mktValBase;
	private BigDecimal bkCostLocl;
	private BigDecimal bkCostAcct;
	private BigDecimal bkCostBase;
	private String extLotRef;
	private String narrTxt;
	private String editRmkTxt;
	private String editStafId;
	private List<TransactionHistoryDto> txnHists;
	private Map<String, String> txnRefData;
	
	public Long getTxnid() {
		return txnid;
	}
	public void setTxnid(Long txnid) {
		this.txnid = txnid;
	}
	public String getTxnTypeCde() {
		return txnTypeCde;
	}
	public void setTxnTypeCde(String txnTypeCde) {
		this.txnTypeCde = txnTypeCde;
	}
	public String getExtTxnRef() {
		return extTxnRef;
	}
	public void setExtTxnRef(String extTxnRef) {
		this.extTxnRef = extTxnRef;
	}
	public String getExtTxnTypeCde() {
		return extTxnTypeCde;
	}
	public void setExtTxnTypeCde(String extTxnTypeCde) {
		this.extTxnTypeCde = extTxnTypeCde;
	}
	public Long getAcid() {
		return acid;
	}
	public void setAcid(Long acid) {
		this.acid = acid;
	}
	public Long getSecid() {
		return secid;
	}
	public void setSecid(Long secid) {
		this.secid = secid;
	}
	public Date getTrdDtTm() {
		return trdDtTm;
	}
	public void setTrdDtTm(Date trdDtTm) {
		this.trdDtTm = trdDtTm;
	}
	public Integer getTzdb() {
		return tzdb;
	}
	public void setTzdb(Integer tzdb) {
		this.tzdb = tzdb;
	}
	public Date getPostDtTm() {
		return postDtTm;
	}
	public void setPostDtTm(Date postDtTm) {
		this.postDtTm = postDtTm;
	}
	public BigDecimal getExePrc() {
		return exePrc;
	}
	public void setExePrc(BigDecimal exePrc) {
		this.exePrc = exePrc;
	}
	public String getPrcCcy() {
		return prcCcy;
	}
	public void setPrcCcy(String prcCcy) {
		this.prcCcy = prcCcy;
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
	public String getSetlCcy() {
		return setlCcy;
	}
	public void setSetlCcy(String setlCcy) {
		this.setlCcy = setlCcy;
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
	public String getMktCde() {
		return mktCde;
	}
	public void setMktCde(String mktCde) {
		this.mktCde = mktCde;
	}
	public String getSrcSysCde() {
		return srcSysCde;
	}
	public void setSrcSysCde(String srcSysCde) {
		this.srcSysCde = srcSysCde;
	}
	public Integer getTxnStat() {
		return txnStat;
	}
	public void setTxnStat(Integer txnStat) {
		this.txnStat = txnStat;
	}
	public String getLongShtInd() {
		return longShtInd;
	}
	public void setLongShtInd(String longShtInd) {
		this.longShtInd = longShtInd;
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
	public String getExtLotRef() {
		return extLotRef;
	}
	public void setExtLotRef(String extLotRef) {
		this.extLotRef = extLotRef;
	}
	public String getNarrTxt() {
		return narrTxt;
	}
	public void setNarrTxt(String narrTxt) {
		this.narrTxt = narrTxt;
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
	public List<TransactionHistoryDto> getTxnHists() {
		return txnHists;
	}
	public void setTxnHists(List<TransactionHistoryDto> txnHists) {
		this.txnHists = txnHists;
	}
	public Map<String, String> getTxnRefData() {
		return txnRefData;
	}
	public void setTxnRefData(Map<String, String> txnRefData) {
		this.txnRefData = txnRefData;
	}
	
}
