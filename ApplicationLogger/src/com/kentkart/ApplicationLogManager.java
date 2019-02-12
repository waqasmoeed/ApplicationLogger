package com.kentkart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ApplicationLogManager {

	private static Logger logger = null;

	private static ApplicationLogger appLogger;
	private static String logServer;
	private static String app_name;

	/** Configure Log library.
	 * 
	 * @param context_path : Context path for application
	 * @param app_name :  Application Name. Log file will be created with this name.
	 * @throws IOException
	 */
	public static void configure(String context_path, String app_name)
			throws IOException {
		System.setProperty("log4j.defaultInitOverride", "true");
		Properties properties = null;

		File logProperties = new File(context_path + "env.properties");
		if (!logProperties.exists()) {

			InputStream configStream = ApplicationLogManager.class
					.getClassLoader().getResourceAsStream("env.properties");
			FileOutputStream out = new FileOutputStream(context_path
					+ "log4j.properties");
			properties = new Properties();
			properties.load(configStream);
			properties.replace("log4j.appender.myAppender.File", app_name
					+ ".log");
			properties.store(out, "");
			out.flush();
			out.close();
			configStream.close();

		} else {

			properties = new Properties();
			properties.load((new FileInputStream(logProperties)));

		}

		PropertyConfigurator.configure(properties);
		ApplicationLogManager.logServer = properties.getProperty("logserver");
		ApplicationLogManager.app_name = app_name;
		logger = LogManager.getLogger(ApplicationLogManager.class);
		appLogger = new ApplicationLogger(ApplicationLogManager.logServer,
				ApplicationLogManager.app_name);
		Thread initiator = new Thread(appLogger);
		initiator.start();

	}

	/** Gets the configured logger.
	 * @return ApplicationLogger
	 */
	public static ApplicationLogger getLogger() {
		if (appLogger == null) {
			appLogger = new ApplicationLogger(ApplicationLogManager.logServer,
					ApplicationLogManager.app_name);
			Thread initiator = new Thread(appLogger);
			initiator.start();
		}
		return appLogger;
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException,
			IOException {

		ApplicationLogManager
				.configure(
						"C:\\Oracle\\Middleware\\user_projects\\domains\\base_domain\\VRS\\",
						"AppLoggerTest");
		ApplicationLogger applogger = ApplicationLogManager.getLogger();

		for (int i = 0; i < 10; i++) {
			// System.out.println("Sending "+i);
			applogger.info("Sending to server" + i);
		}
		// i=10;
		System.out.println("===========================");
		Thread.sleep(10000);
		System.out.println("===========================");
		applogger = ApplicationLogManager.getLogger();
		try {
			throw new Exception("Testing ");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			applogger.error("Exception: " + e.getMessage());
		}

	}

}
