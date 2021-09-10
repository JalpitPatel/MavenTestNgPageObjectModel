package com.selenium.test;

import org.testng.annotations.Test;

import com.selenium.pages.Scenario2.SearchTours;
import com.selenium.utility.Base;
import com.selenium.utility.Constants;
import com.selenium.utility.TestListeners;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(TestListeners.class)
public class SearchToursTest extends Base {

	SearchTours tours;

	@BeforeMethod(alwaysRun = true)
	public void setUpBrowser() {
		launchBrowser("Chrome", Constants.URL2);
	}

	@Parameters({ "searchText" })
	@Test(groups = { "Functional" })
	public void SC02_TC01_SearchTours(String searchText) {
		tours = new SearchTours();
		tours.navigateToTours();
		tours.selectDestination(searchText);
		tours.selectDate();
		tours.selectTravellers();
		tours.submitSearch();
		Assert.assertTrue(tours.verifySearchResult());

	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		tearDown();
	}

}
