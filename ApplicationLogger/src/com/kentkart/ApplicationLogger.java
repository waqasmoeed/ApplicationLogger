/**
 * 
 */
package com.kentkart;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import utils.Utils;

import com.kentkart.bean.ApplicationLog;

/**
 * @author waqas.moeed
 * 
 */
public class ApplicationLogger implements Runnable {

	private static Logger logger = LogManager
			.getLogger(ApplicationLogger.class);
	private Queue<ApplicationLog> logQue;
	private String logserver;
	private String app_name;
	private String currentTime_format = "yyyyMMddHHmmss";

	/**
	 * 
	 */
	public ApplicationLogger(String logserver, String app_name) {
		logQue = new LinkedList<>();
		this.logserver = logserver;
		this.app_name = app_name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		String jsonData = null;
		while (true) {
			try {
				
				ApplicationLog appLog = logQue.poll();
				if (appLog != null) {
					System.out.println("getting object : " + appLog.getDate());
					jsonData = Utils.getJson(appLog);
					int httpResponse = Utils.postLogDetails(this.logserver,
							jsonData);
					System.out.println("Http Response:" + httpResponse);
					if (httpResponse != 200) {
						logger.info(jsonData);
					}
				}
			} catch (Exception e) {
				logger.error(Utils.getExceptionTrace(e));
				logger.info(jsonData);

			}// End of catch block...
		}// End of while loop...

	}// End of run...

	private boolean addToQue(ApplicationLog appLog) {

		return logQue.add(appLog);

	}

	/**
	 * Logs Fatal
	 * 
	 * @param description
	 */
	public void fatal(Exception e) {

		if (logger.getLevel().isGreaterOrEqual(Level.FATAL)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(Utils.getExceptionTrace(e));
			appLog.setLog_level(Level.FATAL.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

	/**
	 * Logs Fatal
	 * 
	 * @param description
	 */
	public void fatal(String description) {

		if (logger.getLevel().isGreaterOrEqual(Level.FATAL)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(description);
			appLog.setLog_level(Level.FATAL.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

	/**
	 * Logs Debug
	 * 
	 * @param description
	 */
	public void debug(String description) {

		if (logger.getLevel().isGreaterOrEqual(Level.DEBUG)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(description);
			appLog.setLog_level(Level.DEBUG.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

	/**
	 * Logs Info
	 * 
	 * @param description
	 */
	public void info(String description) {

		if (logger.isEnabledFor(Level.INFO)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(description);
			appLog.setLog_level(Level.INFO.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

	/**
	 * Logs Errors
	 * 
	 * @param description
	 */
	public void error(Exception e) {

		if (logger.isEnabledFor(Level.ERROR)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(Utils.getExceptionTrace(e));
			appLog.setLog_level(Level.ERROR.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

	/**
	 * Logs Errors
	 * 
	 * @param description
	 */
	public void error(String description) {

		if (logger.isEnabledFor(Level.ERROR)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(description);
			appLog.setLog_level(Level.ERROR.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

	/**
	 * Logs Warnings
	 * 
	 * @param description
	 */
	public void warn(String description) {

		if (logger.isEnabledFor(Level.WARN)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(description);
			appLog.setLog_level(Level.WARN.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

	/**
	 * Logs all Level
	 * 
	 * @param description
	 */
	public void log(String description) {

		if (logger.isEnabledFor(Level.ALL)) {
			ApplicationLog appLog = new ApplicationLog();
			appLog.setApp_name(app_name);
			appLog.setDate(Utils
					.getFormattedCurrentDatetime(currentTime_format));
			appLog.setDescription(description);
			appLog.setLog_level(Level.ALL.toString());
			boolean isadded = this.addToQue(appLog);
			
		}
	}

}
