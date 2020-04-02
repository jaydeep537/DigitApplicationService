package com.exmaple.FindPossibleAlphabet.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exmaple.FindPossibleAlphabet.Service.FindPossibleAlphaberService;

@RestController
public class FetchPossibleAlphabetController {
	
	Logger logger=LoggerFactory.getLogger(FetchPossibleAlphabetController.class);
	
	@Autowired
	FindPossibleAlphaberService FindPossibleAlphaberService;

	@GetMapping("/FindPossibleAlphabet")
	public Map<String,Object> FindPossibleAlphabet(@RequestParam String number, @RequestParam String recordperpage) {
		logger.info("Inside FindPossibleAlphabet");
		Map<String,Object> response=new HashMap<String,Object>();
		Map<String,Object> dataObj=new HashMap<String,Object>();
		String responseCode="";
		String responseDesc="";
		try {
			int[] numberarry=new int[number.length()];
			for (int i = 0; i < number.length(); i++) {
				numberarry[i] = Character.getNumericValue(number.charAt(i)); 
	        } 
			List<String> listRecored=FindPossibleAlphaberService.generateLatterCombination(numberarry);
			if(null !=listRecored && !listRecored.isEmpty()) {
				dataObj.put("combinationList",FindPossibleAlphaberService.getListPossibleValuebyPageNumberAndRecored(1,Integer.parseInt(recordperpage)));
				dataObj.put("totalNumberOfRecord", listRecored.size());
			}
			response.put("data",dataObj);
			responseCode="0000";
			responseDesc="SUCCESS";
			
		}catch(Exception e) {
			responseCode="9999";
			responseDesc="ERROR";
			logger.info(e.getMessage(),e);
		}
		response.put("responseCode", responseCode);
		response.put("responseDesc", responseDesc);
		logger.info("Exiting from FindPossibleAlphabet");
		return response;
	}
	
	@GetMapping("/FillterPossibleAlphabetbyPageNumber")
	public Map<String,Object> FillterPossibleAlphabetByPageNumber(@RequestParam String page, @RequestParam String recordperpage) {
		logger.info("Inside FillterPossibleAlphabetByPageNumber");
		Map<String,Object> response=new HashMap<String,Object>();
		Map<String,Object> dataObj=new HashMap<String,Object>();
		String responseCode="";
		String responseDesc="";
		try {
			 
			List<String> listRecored=FindPossibleAlphaberService.getAllPossibleListValue();
			if(null !=listRecored && !listRecored.isEmpty()) {
				dataObj.put("combinationList",FindPossibleAlphaberService.getListPossibleValuebyPageNumberAndRecored(Integer.parseInt(page),Integer.parseInt(recordperpage)));
				dataObj.put("totalNumberOfRecord", listRecored.size());
			}
			response.put("data",dataObj);
			responseCode="0000";
			responseDesc="SUCCESS";
			
		}catch(Exception e) {
			responseCode="9999";
			responseDesc="ERROR";
			logger.info(e.getMessage(),e);
		}
		response.put("responseCode", responseCode);
		response.put("responseDesc", responseDesc);
		logger.info("Exiting from FillterPossibleAlphabetByPageNumber");
		return response;
	}

}
