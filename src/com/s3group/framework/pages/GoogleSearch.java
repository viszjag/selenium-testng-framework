package com.s3group.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.s3group.framework.base.PageBase;

public class GoogleSearch extends PageBase {
	public By searchBox = By.xpath("//input[@name='q']");
	public By googleSearchBtn = By.xpath("//button[@name='btnG']");
	private boolean searchFlag=false;

	public GoogleSearch(WebDriver driver) {
		super(driver);
	}

	public void search(String searchText) {
		elementExists(this.searchBox).sendKeys(searchText);
		elementExists(this.googleSearchBtn).click();
	}

	public WebElement isDataSearched(String searchText) {
		int i;
		for(i=1;1<=10;i++){
			if(elementExists(By.xpath("(//*[@id='rso']//h3/a)["+i+"]")).getText().toLowerCase().contains(searchText.toLowerCase())){
				searchFlag=true;
				break;
			}
		}
		if(!searchFlag){
			System.out.println("No matching search results found..");
			return null;
		}
		return elementExists(By.xpath("(//*[@id='rso']//h3/a)["+i+"]"));
	}

}