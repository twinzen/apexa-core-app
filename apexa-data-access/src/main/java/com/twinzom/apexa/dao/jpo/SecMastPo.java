package com.twinzom.apexa.dao.jpo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TT_SEC_MAST")
public class SecMastPo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long secid;
	
	@Column(nullable = false)
	private String secCdePri;
	
	@Column(nullable = false)
	private String secTypeCde;
	
	private String name;
	
	@Column(nullable = false)
	private String loclCcy;
	
	@Column(nullable = false)
	private String srcSysCde;
	
	@Column(nullable = false)
	private Integer prcDiv;
	
	private Date mturDt;
	
	@Column(nullable = false)
	private String secStat;
	
	@Column(nullable = false)
	private Date dlstDtTm;
	
	@Column(nullable = false)
	private Date crtDtTm;
	
	@Column(nullable = false)
	private Date updtDtTm;
	
	public Long getSecid() {
		return secid;
	}
	public void setSecid(Long secid) {
		this.secid = secid;
	}
	public String getSecCdePri() {
		return secCdePri;
	}
	public void setSecCdePri(String secCdePri) {
		this.secCdePri = secCdePri;
	}
	public String getSecTypeCde() {
		return secTypeCde;
	}
	public void setSecTypeCde(String secTypeCde) {
		this.secTypeCde = secTypeCde;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoclCcy() {
		return loclCcy;
	}
	public void setLoclCcy(String loclCcy) {
		this.loclCcy = loclCcy;
	}
	public String getSrcSysCde() {
		return srcSysCde;
	}
	public void setSrcSysCde(String srcSysCde) {
		this.srcSysCde = srcSysCde;
	}
	public Integer getPrcDiv() {
		return prcDiv;
	}
	public void setPrcDiv(Integer prcDiv) {
		this.prcDiv = prcDiv;
	}
	public Date getMturDt() {
		return mturDt;
	}
	public void setMturDt(Date mturDt) {
		this.mturDt = mturDt;
	}
	public String getSecStat() {
		return secStat;
	}
	public void setSecStat(String secStat) {
		this.secStat = secStat;
	}
	public Date getDlstDtTm() {
		return dlstDtTm;
	}
	public void setDlstDtTm(Date dlstDtTm) {
		this.dlstDtTm = dlstDtTm;
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
