package com.selenium.pages.Scenario2;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.utility.Base;

public class SearchTours extends Base {

	public SearchTours() {
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@FindBy(xpath = "//button[@id='tours-tab']/span[contains(text(),' Tours')]")
	WebElement tbTours;

	@FindBy(id = "select2-tours_city-container")
	WebElement drpDestination;

	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']/child::input")
	WebElement txtSearchDestination;

	@FindBy(xpath = "//*[@id='select2-tours_city-results']/li[text()='New Delhi,India']")
	WebElement optNewDelhi;

	@FindBy(id = "date")
	WebElement datePicker;
	
	@FindBy(xpath= "/html/body/div[@style]//div[@class='datepicker-days']/table/child::thead/tr/th[@class='next']/i" )
	WebElement nextMonth;

	@FindBy(xpath = "//*[@id='tours-search']/div//following-sibling::a")
	WebElement drpTravellers;

	@FindBy(xpath = "//input[@id='tours_adults']/following-sibling::div/child::i[@class='la la-plus']")
	WebElement plusAdults;

	@FindBy(xpath = "//input[@id='tours_child']/following-sibling::div/child::i[@class='la la-plus']")
	WebElement plusChild;

	@FindBy(xpath = "//button[@id='flights-search']/following::span[text()=' Search']")
	WebElement searchFlight;

	@FindBy(xpath = "//h3[@class='card-title' and text()='City Tour New Delhi old Delhi']")
	WebElement searchResult;

	/*
	 * @Method: navigateToTours - Navigating to Tours Tab
	 */
	public void navigateToTours() {
		wait.until(ExpectedConditions.visibilityOf(tbTours));
		tbTours.click();
	}

	/*
	 * @Method: selectDestination- Select Destination from Destination Drop Down
	 * 
	 * @Param: cityText - Text to search destination city
	 */
	public void selectDestination(String cityText) {
		drpDestination.click();
		wait.until(ExpectedConditions.visibilityOf(txtSearchDestination));
		txtSearchDestination.sendKeys(cityText);
		wait.until(ExpectedConditions.visibilityOf(optNewDelhi));
		optNewDelhi.click();

	}

	/*
	 * @Method: selectDate - Select +20 Day date from today's date
	 */
	public void selectDate() {
		LocalDate todayDate = LocalDate.now();
		int month = todayDate.getMonthValue();
		LocalDate nextTwentyDayDate = todayDate.plusDays(20);
		int nextTwentyDay = nextTwentyDayDate.getDayOfMonth();
		int nextTwentyDayMonth = nextTwentyDayDate.getMonthValue();
		System.out.println("Month: " + nextTwentyDayMonth);
		
		wait.until(ExpectedConditions.visibilityOf(datePicker));
		datePicker.click();
				
		if (nextTwentyDayMonth > month || month == 12 && nextTwentyDayMonth == 1) {	
			wait.until(ExpectedConditions.visibilityOf(nextMonth));
			nextMonth.click();
		}	 

		String nextTwentyDayXpath = "/html/body/div[@style]/div//table/tbody//following-sibling::tr/td[@class='day ' and text()='"+ nextTwentyDay + "']";
		WebElement plusTwentyDate = driver.findElement(By.xpath(nextTwentyDayXpath));
		wait.until(ExpectedConditions.visibilityOf(plusTwentyDate));
		plusTwentyDate.click();
	}

	/*
	 * @Method: selectTravellers - Select number of travellers Adults and Child from
	 * Travellers drop down
	 */
	public void selectTravellers() {
		wait.until(ExpectedConditions.visibilityOf(drpTravellers));
		drpTravellers.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(plusAdults, plusChild));
		plusAdults.click();
		plusChild.click();

	}

	/*
	 * @Method: submitSearch - Hit the Search button to search tour flight
	 */
	public void submitSearch() {
		wait.until(ExpectedConditions.visibilityOf(searchFlight));
		searchFlight.click();
	}

	/*
	 * @Method: verifySearchResult - Verify the visibility of search result
	 */
	public boolean verifySearchResult() {
		wait.until(ExpectedConditions.visibilityOf(searchResult));
		return searchResult.isDisplayed();	
	}

}
