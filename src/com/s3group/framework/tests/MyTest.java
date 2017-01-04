package com.s3group.framework.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.s3group.framework.base.TestBase;
import com.s3group.framework.pages.ClientArea;
import com.s3group.framework.pages.GoogleSearch;
import com.s3group.framework.pages.LoginPage;
import com.s3group.framework.pages.PhpTravels;


public class MyTest extends TestBase {

	@Test
	public void search_Login() {
		GoogleSearch gs=new GoogleSearch(driver);
		String searchText=fetchData("searchText");//search for text 'phptravels'
		System.out.println("Search Text: "+searchText);
		gs.search(searchText);
		WebElement searchedLink=gs.isDataSearched(searchText);
		if(searchedLink!=null)
			searchedLink.click();
		else
			Assert.assertTrue(false,"No matching search results found..");
		waitInSeconds(10);
		Assert.assertTrue(verifyPageTitle(fetchData("homePageTitle")),"Verifying home screen!!");
		System.out.println("Current Page: "+fetchData("homePageTitle"));
		PhpTravels pt=new PhpTravels(driver);
		pt.gotoLoginPage();
		Assert.assertTrue(verifyPageTitle(fetchData("loginPageTitle")),"Verifying login screen!!");
		System.out.println("Current Page: "+fetchData("loginPageTitle"));
		LoginPage lp=new LoginPage(driver);
		lp.login(fetchData("email"), fetchData("password"));
		ClientArea ca=new ClientArea(driver);
		Assert.assertTrue(ca.verifyLoggedInUser(fetchData("firstName")),"Verifying user login!!");
		ca.logout();
	}

}
