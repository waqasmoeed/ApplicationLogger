package com.kentkart.test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kentkart.bean.ApplicationLog;

public class JsonTest {

	public JsonTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationLog applog = new ApplicationLog();
		applog.setApp_name("Test");
		applog.setDate("20.12.2018");
		applog.setDescription("Testing json string");
		applog.setLog_level("info");
		
		
		
		//System.out.println(json);

	}

}
