package com.selenium.test;

import org.testng.annotations.Test;
import com.selenium.pages.Scenario1.OrderItem;
import com.selenium.utility.Base;
import com.selenium.utility.Constants;
import com.selenium.utility.TestListeners;
import org.testng.annotations.Listeners;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Listeners(TestListeners.class)
public class OrderItemTest extends Base {
		
	OrderItem order;
	
	@BeforeMethod(alwaysRun = true)
	public void setBrowser() {
		launchBrowser("Chrome", Constants.URL);
	}

	@Test(groups = { "Functional" })
	public void SC01_TC01_OrderItem() {
		order = new OrderItem();
		order.selectCategory();
		order.viewItem();
		order.checkOutItem();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(order.cartSuccessMsg));
		String actCartMsg = order.cartSuccessMsg.getText();
		Assert.assertEquals(actCartMsg, "Product successfully added to your shopping cart");
		
		String actCartItemName = order.lblCartItem.getText();
		Assert.assertEquals(actCartItemName, "Faded Short Sleeve T-shirts");
		
		String actCartItemColor = order.lblCartItemColor.getText();
		Assert.assertEquals(actCartItemColor,"Orange, M");
		
		String actQtylbl = order.lblQuantity.getText();
		Assert.assertEquals(actQtylbl,"Quantity");
		
		String actQty = order.qauntity.getText();
		Assert.assertEquals(actQty,"2");
		
		String actTotalLbl = order.lblTotal.getText();
		Assert.assertEquals(actTotalLbl,"Total");
		
		String actTotal = order.price.getText();
		Assert.assertEquals(actTotal,"$33.02");	
		order.proceedToCheckout();
		
		String actDescription = order.lblDescription.getText();
		Assert.assertEquals(actDescription,"Faded Short Sleeve T-shirts");
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		tearDown();
	}

}
