package com.euro.currencyApi.conversion.bean;

import java.math.BigDecimal;
import java.util.List;

public class HistoryResourceBean {
	
	private String base;
	private List<BigDecimal> rate;
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public List<BigDecimal> getRate() {
		return rate;
	}
	public void setRate(List<BigDecimal> rate) {
		this.rate = rate;
	}
	

}
