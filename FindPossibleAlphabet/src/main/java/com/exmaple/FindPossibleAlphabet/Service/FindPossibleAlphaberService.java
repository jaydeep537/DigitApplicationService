package com.exmaple.FindPossibleAlphabet.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class FindPossibleAlphaberService {

	Logger logger=LoggerFactory.getLogger(FindPossibleAlphaberService.class);
	
	ArrayList<String> possibleListValue = null;
	
	public ArrayList<String> getAllPossibleListValue(){
		return possibleListValue;
	}
	
	
	public ArrayList<String> generateLatterCombination(int[] number) {
		
		try {
			// To store the generated letter combinations
			possibleListValue  = new ArrayList<>();
			
			//List of possible value base on normal phone
			String[] table = { "", "", "abc", "def", "ghi", "jkl","mno", "pqrs", "tuv", "wxyz" };
			int n=number.length;

			Queue<String> q = new LinkedList<>();
			q.add("");

			while (!q.isEmpty()) {
				String s = q.remove();

				// If complete word is generated
				// push it in the list
				if (s.length() == n)
					possibleListValue.add(s);
				else {
					String val = table[number[s.length()]];
					for (int i = 0; i < val.length(); i++) {
						q.add(s + val.charAt(i));
					}
				}
			}
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			
		}
		return possibleListValue;
	}

	public List<String> getListPossibleValuebyPageNumberAndRecored(int page,int record){
		List<String> response =new ArrayList<String>();
		
		try {
			int startIntex=(page*record+1)-record;
			int endIndex=page*record;
			
		for(int a=startIntex;a<=endIndex;a++) {
			response.add(possibleListValue.get(a));
			System.out.println(possibleListValue.get(a));
		}
			
			
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		return response;
		
	}
}
