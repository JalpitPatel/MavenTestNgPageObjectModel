package com.selenium.pages.Scenario1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.utility.Base;

public class OrderItem extends Base {

	public OrderItem() {
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@FindBy(xpath = "//li/ancestor::li/following-sibling::li/a[@title='T-shirts']")
	WebElement lnkTShirts;

	@FindBy(xpath = "//h5[@itemprop='name']/a[@title='Faded Short Sleeve T-shirts']")
	WebElement lnkViewItem;

	@FindBy(xpath = "//i[@class='icon-plus']")
	WebElement iconAddQty;

	@FindBy(id = "group_1")
	WebElement drpSelectSize;

	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	WebElement btnAddToCart;

	@FindBy(xpath = "//i[@class='icon-ok']/ancestor::h2")
	public WebElement cartSuccessMsg;

	@FindBy(id = "layer_cart_product_title")
	public WebElement lblCartItem;

	@FindBy(id = "layer_cart_product_attributes")
	public WebElement lblCartItemColor;

	@FindBy(xpath = "//strong[@class='dark' and text()='Quantity']")
	public WebElement lblQuantity;

	@FindBy(id = "layer_cart_product_quantity")
	public WebElement qauntity;

	@FindBy(xpath = "//strong[@class='dark' and text()='Total']")
	public WebElement lblTotal;

	@FindBy(id = "layer_cart_product_price")
	public WebElement price;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement btnProceedToChkOut;

	@FindBy(xpath = "//p[@class='product-name']/a[text()='Faded Short Sleeve T-shirts']")
	public WebElement lblDescription;

	/*
	 * @Method: selectCategory- Click on T-shirts category link
	 */
	public void selectCategory() {
		wait.until(ExpectedConditions.visibilityOf(lnkTShirts));
		lnkTShirts.click();
	}

	/*
	 * @Method: viewItem- Click on the product: Faded Short Sleeve T-shirts
	 */
	public void viewItem() {
		wait.until(ExpectedConditions.visibilityOf(lnkViewItem));
		lnkViewItem.click();
	}

	/*
	 * @Method: checkOutItem- Add qty, Select size and Cart item. Also Assert the
	 * values of Cart item
	 */
	public void checkOutItem() {
		wait.until(ExpectedConditions.visibilityOfAllElements(iconAddQty, btnAddToCart));
		iconAddQty.click();
		Select size = new Select(drpSelectSize);
		size.selectByVisibleText("M");
		btnAddToCart.click();
	}

	/*
	 * @Method: proceedToCheckout- Click on Proceed Checkout Button and verify the
	 * visibility of cart item description
	 */
	public void proceedToCheckout() {
		wait.until(ExpectedConditions.visibilityOf(btnProceedToChkOut));
		btnProceedToChkOut.click();
		
	}

}
