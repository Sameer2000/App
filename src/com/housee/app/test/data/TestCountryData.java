package com.housee.app.test.data;

import java.util.HashMap;

public class TestCountryData {

	private static HashMap<String, String> countryMap = new HashMap<String,String>();
	static{
		countryMap.put("Asia", "Asia");
		countryMap.put("USA", "USA");
		countryMap.put("UK", "UK");
	}
	
	public static HashMap<String,String> getCountryMap(){
		return countryMap;
	}
}
