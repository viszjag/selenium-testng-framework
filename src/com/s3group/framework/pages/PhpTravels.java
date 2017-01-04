package com.s3group.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.s3group.framework.base.PageBase;

public class PhpTravels extends PageBase {
	public By loginBtn = By.xpath("//a[contains(.,'Login')]");
	public By email = By.id("inputEmail");

	public PhpTravels(WebDriver driver) {
		super(driver);
	}

	public void gotoLoginPage() {
		elementExists(this.loginBtn).click();
		elementExists(email);//wait until Login page is loaded
	}
}