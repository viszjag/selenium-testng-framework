package com.s3group.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class PageBase {

	protected WebDriver driver = null;
	private WebDriverWait wait =null;

	
	public PageBase(WebDriver driver) {
		this.driver = driver;
	}


	//wait for web element until it is visible.
	public WebElement elementExists(By locator){
		wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return driver.findElement(locator);  
		}catch(ElementNotVisibleException e){
			return null;
		} catch (TimeoutException e) {
			return null;
		}
	}


}
