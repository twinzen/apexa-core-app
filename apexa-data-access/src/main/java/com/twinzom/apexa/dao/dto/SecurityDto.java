package com.twinzom.apexa.dao.dto;

import java.util.Date;

public class SecurityDto {

	private Long secid;
	private String secCdePri;
	private String secTypeCde;
	private String name;
	private String loclCcy;
	private String secStat;
	private Integer prcDiv;
	private String srcSysCde;
	private Date mturDt;
	private SecurityPriceDto secPrc;
	private Date crtDtTm;
	private Date updDtTm;
	
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
	public String getSecStat() {
		return secStat;
	}
	public void setSecStat(String secStat) {
		this.secStat = secStat;
	}
	public Integer getPrcDiv() {
		return prcDiv;
	}
	public void setPrcDiv(Integer prcDiv) {
		this.prcDiv = prcDiv;
	}
	public String getSrcSysCde() {
		return srcSysCde;
	}
	public void setSrcSysCde(String srcSysCde) {
		this.srcSysCde = srcSysCde;
	}
	public Date getMturDt() {
		return mturDt;
	}
	public void setMturDt(Date mturDt) {
		this.mturDt = mturDt;
	}
	public SecurityPriceDto getSecPrc() {
		return secPrc;
	}
	public void setSecPrc(SecurityPriceDto secPrc) {
		this.secPrc = secPrc;
	}
	public Date getCrtDtTm() {
		return crtDtTm;
	}
	public void setCrtDtTm(Date crtDtTm) {
		this.crtDtTm = crtDtTm;
	}
	public Date getUpdDtTm() {
		return updDtTm;
	}
	public void setUpdDtTm(Date updDtTm) {
		this.updDtTm = updDtTm;
	}
	
}
