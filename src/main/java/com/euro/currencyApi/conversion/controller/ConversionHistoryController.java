package com.euro.currencyApi.conversion.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.euro.currencyApi.conversion.bean.HistoryResourceBean;
import com.euro.currencyApi.conversion.service.HistoryService;

@RestController
public class ConversionHistoryController {
	
	@Autowired
	HistoryService historyService;
	
	@GetMapping("/loadSource")
	public ResponseEntity<String> loadHistoryData()
	{
		historyService.saveHistoryDet();
			return ResponseEntity.ok("Data Loaded to Db");
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("getHistoryData")
	public  List<HistoryResourceBean> getHistoryDetails()
	{
		return historyService.getHistoryDetails();
		 
	}
	

}
