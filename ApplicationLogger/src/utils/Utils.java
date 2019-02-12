package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kentkart.bean.ApplicationLog;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}

	public static String getFormattedCurrentDatetime(String format) {

		DateFormat df = new SimpleDateFormat(format);
		Date today = Calendar.getInstance().getTime();
		String datetime = df.format(today);
		return datetime;
	}
	
	public static String getExceptionTrace(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public static String getJson(ApplicationLog applog){
		Gson gson = new GsonBuilder()
		.setPrettyPrinting()
		.serializeNulls()
		.setFieldNamingPolicy(
				FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();	
		String json = gson.toJson(applog);
		return json;
	}
	
	public static int postLogDetails(String logServerUrl, String logData)
			throws Exception {

		URL urlObject = new URL(logServerUrl);
		HttpURLConnection httpconnection = (HttpURLConnection) urlObject
				.openConnection();

		httpconnection.setRequestMethod("POST");
		httpconnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		httpconnection.setRequestProperty("Content-Type", "application/json");

		System.out.println("Log Data sending : "+logData);
		// Send post request
		httpconnection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(
				httpconnection.getOutputStream());
		wr.writeBytes(logData);
		wr.flush();
		wr.close();

		String response = convertStreamToString(httpconnection.getInputStream(), 1024, "utf8");
		System.out.println("Server Resp: "+response);
		
		return httpconnection.getResponseCode();
	}
	
	
	public static String convertStreamToString(InputStream is, int bufferSize, String encoding) 
			throws IOException {
			     
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
	    StringBuffer content = new StringBuffer();
	    char[] buffer = new char[bufferSize];
	    int n;	  
	    while ( ( n = reader.read(buffer)) != -1 ) {
	        content.append(buffer,0,n);
	    }
	     
	    return content.toString();
	}

	

}
