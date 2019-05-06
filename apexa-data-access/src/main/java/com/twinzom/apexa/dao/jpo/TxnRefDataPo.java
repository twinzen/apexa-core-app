package com.twinzom.apexa.dao.jpo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TT_TXN_REF_DATA")
public class TxnRefDataPo {

	@Id
	private Long txnid;
	
	@Column(nullable = false)
	private String refData;
	
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

	public String getRefData() {
		return refData;
	}

	public void setRefData(String refData) {
		this.refData = refData;
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
