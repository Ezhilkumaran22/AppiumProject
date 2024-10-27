package org.MobileFrameworkTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.BaseTestClass.MobileBaseTestClass;
import org.MobileFrameworkTest.PageObject.Android.CartPage;
import org.MobileFrameworkTest.PageObject.Android.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MobileEcommerceTest extends MobileBaseTestClass
{
	public ProductsPage productsPage;
	public CartPage cartPage;
	
	@BeforeMethod(alwaysRun = true)
	public void preSet() 
	{
		formPage.packageAndActivitiesActions("com.androidsample.generalstore/com.androidsample.generalstore.MainActivity");
	}
	
	@DataProvider(name = "FillFormData")
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String, String>> jsonData = getJsonData(System.getProperty("user.dir")+"\\src\\main\\java\\org\\MobileFrameworkTestData\\EcommerceTestData.json");
		
		return new Object[][] {
			{jsonData.get(0)},
		};
	}
	
	@Test(dataProvider = "FillFormData",groups = {"Smoke"})
	public void fillForm(HashMap<String, String> inputData) throws InterruptedException 
	{
		formPage.enterName(inputData.get("nae"));
		formPage.clickGenderOption(inputData.get("gender"));
		formPage.selectCountryOption(inputData.get("country"));
		productsPage = formPage.clickLetShopBtn();
	}
	
	@Test
	public void validateFillFormWithToastMessage(HashMap<String, String> inputData) throws InterruptedException 
	{	
		fillForm(inputData);
		
		boolean toastErrorMessage = formPage.getToastErrorMessage();
		Assert.assertTrue(toastErrorMessage, "Assert condition is false");
	}
	
	@Test
	public void sumOfTwoProductsAddedToCart(HashMap<String, String> inputData) throws InterruptedException 
	{
		//Login page
		formPage.enterName(inputData.get("name"));
		formPage.clickGenderOption(inputData.get("gender"));
		formPage.selectCountryOption(inputData.get("country"));
		productsPage = formPage.clickLetShopBtn();
		
		//Add 2 products to cart
		for (int i = 0; i < 2; i++) 
		{
			productsPage.addToCartItemByIndex(i);
		}
		cartPage = productsPage.goToCartPage();
		
		//Sum of 2 products validations
		double sumOfProduct = cartPage.getProductSum();
		String totalProductAmount = cartPage.getProductTotalAmount();
		double expectedTotalAmt = formPage.getFormattedAmount(totalProductAmount);
		Assert.assertEquals(sumOfProduct, expectedTotalAmt, "Both product calculated amount & Product total amount are not equal");
		
		cartPage.clickAcceptTermsAndConditionButton();
		cartPage.submitOrder();
	}

	@Test
	public void mobileGestures(HashMap<String, String> inputData) throws InterruptedException 
	{
		sumOfTwoProductsAddedToCart(inputData);
		
		//Mobile Gesture Actions
		cartPage.clickAcceptTermsAndConditionButton();
		cartPage.submitOrder();
	}
}
