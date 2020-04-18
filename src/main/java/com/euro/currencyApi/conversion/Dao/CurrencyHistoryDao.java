package com.euro.currencyApi.conversion.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.euro.currencyApi.conversion.entity.CurrencyConversionEntity;

public interface CurrencyHistoryDao {
	
	public boolean saveHistioryData(List<CurrencyConversionEntity> currencyList);

	public Long getHisId();

	public List<BigDecimal> getCurrencyHistDetails( String base);

}
