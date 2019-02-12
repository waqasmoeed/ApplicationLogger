package com.kentkart.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.text.Utilities;

import utils.Utils;

public class PropertyTest {

	public PropertyTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		
		Properties properties = new Properties();
		InputStream inputStream = PropertyTest.class.getClassLoader().getResourceAsStream("log4j");
				
		if(inputStream!=null){
			properties.load(inputStream);
			
		}
		
		properties.keySet();
		System.out.println(properties.getProperty("logserver"));
		System.out.println(properties.getProperty("system[028]"));

	}

}
