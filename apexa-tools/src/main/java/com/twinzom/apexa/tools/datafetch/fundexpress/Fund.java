package com.twinzom.apexa.tools.datafetch.fundexpress;

import java.util.Date;

public class Fund {

	private String fundCode;
	
	private String name;
	
	private String ccy;
	
	private Integer riskLvl;
	
	private Double deviation;
	
	private Double annualReturn;
	
	private Double sharpe;
	
	private Double navPrice;
	
	private Double bidPrice;
	
	private Double offerPrice;
	
	private Double min52Price;
	
	private Double max52Price;
	
	private Date priceDate;
	
	private Date statisticDate;
	
	private String statusCode;
	
	private String fundHouse;

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRiskLvl() {
		return riskLvl;
	}

	public void setRiskLvl(Integer riskLvl) {
		this.riskLvl = riskLvl;
	}

	public Double getDeviation() {
		return deviation;
	}

	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}

	public Double getAnnualReturn() {
		return annualReturn;
	}

	public void setAnnualReturn(Double annualReturn) {
		this.annualReturn = annualReturn;
	}

	public Double getSharpe() {
		return sharpe;
	}

	public void setSharpe(Double sharpe) {
		this.sharpe = sharpe;
	}

	public Double getNavPrice() {
		return navPrice;
	}

	public void setNavPrice(Double navPrice) {
		this.navPrice = navPrice;
	}

	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Double getMin52Price() {
		return min52Price;
	}

	public void setMin52Price(Double min52Price) {
		this.min52Price = min52Price;
	}

	public Double getMax52Price() {
		return max52Price;
	}

	public void setMax52Price(Double max52Price) {
		this.max52Price = max52Price;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public Date getStatisticDate() {
		return statisticDate;
	}

	public void setStatisticDate(Date statisticDate) {
		this.statisticDate = statisticDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getFundHouse() {
		return fundHouse;
	}

	public void setFundHouse(String fundHouse) {
		this.fundHouse = fundHouse;
	}
	
	
	
}
