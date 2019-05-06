package com.twinzom.apexa.caleng.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalPosition {

	private BigDecimal qty;
	
	private BigDecimal bkCostLocl;
	
	private BigDecimal bkCostAcct;
	
	private BigDecimal income;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date valDate;
	
	public CalPosition () {
		
		this.qty = BigDecimal.ZERO;
		this.bkCostLocl = BigDecimal.ZERO;
		this.bkCostAcct = BigDecimal.ZERO;
		this.income = BigDecimal.ZERO;
		this.startDate = null;
		this.endDate = null;
		this.valDate = null;
		
	}
	
	public CalPosition (CalPosition p) {
		
		this.qty = p.getQty();
		this.bkCostLocl = p.getBkCostLocl();
		this.bkCostAcct = p.getBkCostAcct();
		this.income = p.getIncome();
		this.startDate = p.getStartDate();
		this.endDate = p.getEndDate();
		this.valDate = p.getValDate();
		
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
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

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getValDate() {
		return valDate;
	}

	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}
	
	
	
}
