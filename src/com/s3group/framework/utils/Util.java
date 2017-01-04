package com.s3group.framework.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class Util {
	public static void takeScreenShot(WebDriver driver, String fileName) {
		System.out.println("Capturing ScreenShot: ");
		File srcFile = ((TakesScreenshot) (driver))
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")
					+ "/screenshots/" + fileName + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
