package com.euro.currencyApi.conversion.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.euro.currencyApi.conversion.Dao.CurrencyHistoryDao;
import com.euro.currencyApi.conversion.bean.HelperBean;
import com.euro.currencyApi.conversion.bean.HistoryResourceBean;
import com.euro.currencyApi.conversion.entity.CurrencyConversionEntity;


@Service
public class HistoryService {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CurrencyHistoryDao currencyHistoryDao;
	
	
	
	/**
	 * @return
	 * Method makes rest call to fetch all the currency rates against EURo for current date of last sixt months
	 */
	public boolean saveHistoryDet() {
		boolean result = false;
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.now();
		List<ResponseEntity<HelperBean>> responseEntity  =null;
		Map<String,List<BigDecimal> > values = new HashMap<>();
		
		List<String> baseList = new ArrayList<String>();
		baseList.add("USD");
		baseList.add("GBP");
		baseList.add("HKD");
		
		
		List<CurrencyConversionEntity> currencyList = new ArrayList<CurrencyConversionEntity>();
		try {
		for(String base : baseList)
		{
				responseEntity = new ArrayList<>();
			for(int i=1; i<=6; i++)
				{
				
					
				responseEntity.add(new RestTemplate().getForEntity(
				        "https://api.ratesapi.io/api/"+date.minusMonths(i)+"?base="+base+"&symbols=EUR", HelperBean.class));
				}
			
			for( ResponseEntity<HelperBean> res : responseEntity)
				{
				HelperBean hBean = res.getBody();
				CurrencyConversionEntity currEntity = new CurrencyConversionEntity();
				currEntity.setBase(hBean.getBase());
				currEntity.setSymbols("EUR");
				currEntity.setDate(hBean.getDate());
				currEntity.setRate(hBean.getRates().get("EUR"));
				//currEntity.setId(currencyHistoryDao.getHisId());
				
				currencyList.add(currEntity);
				}
			
			
		}
		System.out.println("service");
		 result = currencyHistoryDao.saveHistioryData(currencyList);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return result;
	}
		
		return result;
	
	}

	public List<HistoryResourceBean> getHistoryDetails() {
		// TODO Auto-generated method stub
		List<HistoryResourceBean> values = new ArrayList<>();
		List<String> baseList = new ArrayList<String>();
		baseList.add("USD");
		baseList.add("GBP");
		baseList.add("HKD");
		
		try
		{
			for(String base: baseList)
			{
				HistoryResourceBean historyResourceBean = new HistoryResourceBean();
				historyResourceBean.setBase(base);
				historyResourceBean.setRate(currencyHistoryDao.getCurrencyHistDetails(base));
				values.add(historyResourceBean );
			
			}
		
		
		}
	catch(Exception e)
	{
		e.printStackTrace();
		return null;
	}
		
		return values;
	
	}
}
