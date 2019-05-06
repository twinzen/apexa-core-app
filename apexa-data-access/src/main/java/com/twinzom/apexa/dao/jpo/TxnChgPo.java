package com.twinzom.apexa.dao.jpo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TT_TXN_CHG")
public class TxnChgPo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long chgid;
	
	@Column(nullable = false)
	private Long txnid;
	
	private String chgTypeCde;
	
	private String chgDesc;
	
	private BigDecimal chgAmtLocl;
	
	private BigDecimal chgAmtSetl;
	
	private BigDecimal chgAmtBase;
	
	private String narrTxt;
	
	public Long getChgid() {
		return chgid;
	}
	public void setChgid(Long chgid) {
		this.chgid = chgid;
	}
	public Long getTxnid() {
		return txnid;
	}
	public void setTxnid(Long txnid) {
		this.txnid = txnid;
	}
	public String getChgTypeCde() {
		return chgTypeCde;
	}
	public void setChgTypeCde(String chgTypeCde) {
		this.chgTypeCde = chgTypeCde;
	}
	public String getChgDesc() {
		return chgDesc;
	}
	public void setChgDesc(String chgDesc) {
		this.chgDesc = chgDesc;
	}
	public BigDecimal getChgAmtLocl() {
		return chgAmtLocl;
	}
	public void setChgAmtLocl(BigDecimal chgAmtLocl) {
		this.chgAmtLocl = chgAmtLocl;
	}
	public BigDecimal getChgAmtSetl() {
		return chgAmtSetl;
	}
	public void setChgAmtSetl(BigDecimal chgAmtSetl) {
		this.chgAmtSetl = chgAmtSetl;
	}
	public BigDecimal getChgAmtBase() {
		return chgAmtBase;
	}
	public void setChgAmtBase(BigDecimal chgAmtBase) {
		this.chgAmtBase = chgAmtBase;
	}
	public String getNarrTxt() {
		return narrTxt;
	}
	public void setNarrTxt(String narrTxt) {
		this.narrTxt = narrTxt;
	}
	
}
