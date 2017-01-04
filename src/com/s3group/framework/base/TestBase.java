package com.s3group.framework.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.s3group.framework.utils.Util;

public abstract class TestBase {
	public static WebDriver driver = null;
	protected static org.apache.log4j.Logger log = null;
	protected static Properties config = null;
	protected static Properties data = null;
	protected static Connection conn = null;

	protected TestBase() {
		initLog();
		initConfig();
		initData();
		initDriver();
	}

	private void initLog() {
		if (log == null) {
			PropertyConfigurator.configure(System.getProperty("user.dir")
					+ File.separator + "config" + File.separator
					+ "log4j.properties");
			log = org.apache.log4j.Logger.getLogger("Logger");
			System.setProperty("org.apache.commons.logging.Log",
					"org.apache.commons.logging.impl.Jdk14Logger");
			log.debug("Initialized log file successfully!!");
		}
	}

	private static void initConfig() {
		if (config == null) {
			config = new Properties();
			FileInputStream ip = null;

			String fileName = "config.properties";
			try {
				String path = System.getProperty("user.dir") + File.separator
						+ "config" + File.separator + fileName;
				ip = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void initData() {
		if (data == null) {
			data = new Properties();
			FileInputStream ip = null;

			String fileName = "data.properties";
			try {
				String path = System.getProperty("user.dir") + File.separator
						+ "config" + File.separator + fileName;
				ip = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				data.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void initDriver() {
		if (driver == null) {
			System.out.println("Browser = " + config.getProperty("browser"));

			if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();
			System.out.println("Base Url:"+config.getProperty("url"));
			driver.get(config.getProperty("url"));
		}
	}

	public static void quitDriver() {
		driver.quit();
		driver = null;
		log.info("Closing the Browser!!");
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		System.out.println("Method Name: " + result.getMethod().getMethodName());
		System.out.println("Success Status: " + result.isSuccess());

		if (!result.isSuccess()) {
			Util.takeScreenShot(driver, result.getMethod().getMethodName());
		}
		quitDriver();
	}

	public static boolean verifyPageTitle(String title){
		if(driver.getWindowHandles().size()>1)
		{
			for(String winHandle : driver.getWindowHandles()){
				if(!driver.switchTo().window(winHandle).getTitle().trim().equalsIgnoreCase(title))
					continue;
			}
		}

		if(driver.getTitle().trim().equalsIgnoreCase(title.trim()))
			return true;
		else
			return false;
	}

	public String fetchData(String key){
		return data.getProperty(key);
	}

	public static void waitInSeconds(int seconds){
		long time=seconds*1000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
