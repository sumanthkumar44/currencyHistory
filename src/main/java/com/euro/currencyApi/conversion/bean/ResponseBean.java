package com.euro.currencyApi.conversion.bean;

import java.math.BigDecimal;
import java.util.List;

public class ResponseBean {
	private String base;
	private List<BigDecimal> historyRates;
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public List<BigDecimal> getHistoryRates() {
		return historyRates;
	}
	public void setHistoryRates(List<BigDecimal> historyRates) {
		this.historyRates = historyRates;
	}

	

}
