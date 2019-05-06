package com.twinzom.apexa.caleng.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CalTxn implements Comparable<CalTxn> {

	private String id;
	
	private String txnTypeCde;
	
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date trdDtTm;
	
	private BigDecimal prc;
	
	private String prcCcy;
	
	private BigDecimal qty;
	
	private BigDecimal pripLocl;
	
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date setlDtTm;
	
	private BigDecimal setlAmtSetl;
	
	private String setlCcy;
	
	private BigDecimal mktValLocl;
	
	private BigDecimal mktValAcct;
	
	private BigDecimal bkCostLocl;
	
	private BigDecimal bkCostAcct;
	
	private BigDecimal rglLocl;
	
	private BigDecimal rglAcct;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxnTypeCde() {
		return txnTypeCde;
	}

	public void setTxnTypeCde(String txnTypeCde) {
		this.txnTypeCde = txnTypeCde;
	}

	public Date getTrdDtTm() {
		return trdDtTm;
	}

	public void setTrdDtTm(Date trdDtTm) {
		this.trdDtTm = trdDtTm;
	}

	public BigDecimal getPrc() {
		return prc;
	}

	public void setPrc(BigDecimal prc) {
		this.prc = prc;
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

	public BigDecimal getPripLocl() {
		return pripLocl;
	}

	public void setPripLocl(BigDecimal pripLocl) {
		this.pripLocl = pripLocl;
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

	public String getSetlCcy() {
		return setlCcy;
	}

	public void setSetlCcy(String setlCcy) {
		this.setlCcy = setlCcy;
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

	public BigDecimal getRglLocl() {
		return rglLocl;
	}

	public void setRglLocl(BigDecimal rglLocl) {
		this.rglLocl = rglLocl;
	}

	public BigDecimal getRglAcct() {
		return rglAcct;
	}

	public void setRglAcct(BigDecimal rglAcct) {
		this.rglAcct = rglAcct;
	}

	@Override
	public int compareTo(CalTxn t) {
		return (this.trdDtTm.compareTo(t.trdDtTm));
	}
	
	
	
	
}
