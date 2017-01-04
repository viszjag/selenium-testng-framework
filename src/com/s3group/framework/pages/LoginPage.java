package com.s3group.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.s3group.framework.base.PageBase;

public class LoginPage extends PageBase {
	public By email = By.id("inputEmail");
	public By password = By.id("inputPassword");
	public By loginBtn = By.id("login");
	
	

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String email,String password) {
		elementExists(this.email).sendKeys(email);
		elementExists(this.password).sendKeys(password);
		elementExists(this.loginBtn).click();
	}
}