package com.twinzom.apexa.dao.jpo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TT_SEC_CDE")
public class SecCdePo {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long scdeid;
	
	@Column(nullable = false)
	private Long secid;
	
	@Column(nullable = false)
	private String secCdeAlt;
	
	@Column(nullable = false)
	private String secCdeAltType;
	
	@Column(nullable = false)
	private Date crtDtTm;
	
	@Column(nullable = false)
	private Date updtDtTm;

	public Long getScdeid() {
		return scdeid;
	}

	public void setScdeid(Long scdeid) {
		this.scdeid = scdeid;
	}

	public Long getSecid() {
		return secid;
	}

	public void setSecid(Long secid) {
		this.secid = secid;
	}

	public String getSecCdeAlt() {
		return secCdeAlt;
	}

	public void setSecCdeAlt(String secCdeAlt) {
		this.secCdeAlt = secCdeAlt;
	}

	public String getSecCdeAltType() {
		return secCdeAltType;
	}

	public void setSecCdeAltType(String secCdeAltType) {
		this.secCdeAltType = secCdeAltType;
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
