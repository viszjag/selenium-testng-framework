package com.s3group.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.s3group.framework.base.PageBase;

public class ClientArea extends PageBase {
	public By userName = By.xpath("//strong");
	public By accountMenuDropDown = By.id("Secondary_Navbar-Account");
	public By logoutLink = By.id("Secondary_Navbar-Account-Logout");
	public By loginBtn = By.id("login");
	public By logoutConfirmationMessage=By.xpath("//div[contains(.,'You have been successfully logged out.')]");

	public ClientArea(WebDriver driver) {
		super(driver);
	}

	public boolean verifyLoggedInUser(String firstName) {
		String userName=elementExists(this.accountMenuDropDown).getText().trim();
		if(userName.contains("Hello, "+firstName+"!")){
			System.out.println("User logged in successfully!!");
			return true;
		}
		else
			return false;
	}

	public void logout() {
		elementExists(this.accountMenuDropDown).click();
		elementExists(this.logoutLink).click();
		if(elementExists(logoutConfirmationMessage)!=null)
			System.out.println("User logged out successfully!!");
	}


}