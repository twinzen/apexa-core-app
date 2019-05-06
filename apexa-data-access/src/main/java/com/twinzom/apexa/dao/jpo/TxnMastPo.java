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
@Table(name = "TT_TXN_MAST")
public class TxnMastPo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long txnid;

	@Column(nullable = false)
	private String txnTypeCde;
	
	@Column(nullable = false)
	private String extTxnRef;
	
	@Column(nullable = false)
	private String extTxnTypeCde;
	
	@Column(nullable = false)
	private Long acid;
	
	@Column(nullable = false)
	private Long secid;
	
	@Column(nullable = false)
	private Date trdDtTm;
	
	@Column(nullable = false)
	private Date postDtTm;
	
	@Column(nullable = false)
	private BigDecimal exePrc;
	
	@Column(nullable = false)
	private String prcCcy;
	
	@Column(nullable = false)
	private BigDecimal exePrcLocl;
	
	@Column(nullable = false)
	private BigDecimal qty;
	
	@Column(nullable = false)
	private BigDecimal pripAmtLocl;
	
	private Date setlDtTm;
	
	private String setlCcy;
	
	private BigDecimal setlAmtSetl;
	
	private BigDecimal setlLoclRate;
	
	private Date setlLoclRateDtTm;
	
	@Column(nullable = false)
	private String mktCde;
	
	@Column(nullable = false)
	private String srcSysCde;
	
	@Column(nullable = false)
	private Integer txnStat;
	
	@Column(nullable = false)
	private String longShtInd;
	
	@Column(nullable = false)
	private BigDecimal mktValLocl;
	
	private BigDecimal mktValAcct;
	
	private BigDecimal mktValBase;
	
	@Column(nullable = false)
	private BigDecimal bkCostLocl;
	
	private BigDecimal bkCostAcct;
	
	private BigDecimal bkCostBase;
	
	private String extLotRef;
	
	private String narrTxt;
	
	private String txnRmk;
	
	private String editRmkTxt;
	
	private String editStafId;
	
	private Integer tzdb;
	
	private String cnlInd;
	
	private Date cnlDtTm;
	
	@Column(nullable = false)
	private Date crtDtTm;
	
	@Column(nullable = false)
	private Date updtDtTm;

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

	public BigDecimal getExePrcLocl() {
		return exePrcLocl;
	}

	public void setExePrcLocl(BigDecimal exePrcLocl) {
		this.exePrcLocl = exePrcLocl;
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

	public Date getSetlLoclRateDtTm() {
		return setlLoclRateDtTm;
	}

	public void setSetlLoclRateDtTm(Date setlLoclRateDtTm) {
		this.setlLoclRateDtTm = setlLoclRateDtTm;
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

	public Integer getTzdb() {
		return tzdb;
	}

	public void setTzdb(Integer tzdb) {
		this.tzdb = tzdb;
	}

	public String getCnlInd() {
		return cnlInd;
	}

	public void setCnlInd(String cnlInd) {
		this.cnlInd = cnlInd;
	}

	public Date getCnlDtTm() {
		return cnlDtTm;
	}

	public void setCnlDtTm(Date cnlDtTm) {
		this.cnlDtTm = cnlDtTm;
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
