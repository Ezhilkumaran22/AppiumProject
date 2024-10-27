package org.MobileFrameworkTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.BaseTestClass.MobileBaseTestClass;
import org.MobileFrameworkTest.PageObject.Android.CartPage;
import org.MobileFrameworkTest.PageObject.Android.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddToCartTest extends MobileBaseTestClass
{
	public ProductsPage productsPage;
	public CartPage cartPage;
	
	@DataProvider(name = "AddToCartTestData")
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String, String>> jsonData = getJsonData(System.getProperty("user.dir")+"\\src\\main\\java\\org\\MobileFrameworkTestData\\EcommerceTestData.json");
		return new Object[][] {
			{jsonData.get(0)},
			{jsonData.get(1)},
			{jsonData.get(2)}
		};
	}
	@Test (dataProvider = "AddToCartTestData")
	public void AddProductToCart(HashMap<String, String> inputData) throws InterruptedException 
	{
		formPage.enterName(inputData.get("name"));
		formPage.clickGenderOption(inputData.get("gender"));
		formPage.selectCountryOption(inputData.get("country"));
		productsPage = formPage.clickLetShopBtn();
		
		String productName = "Jordan 6 Rings";
		String addedProduct ="";
		productsPage.scrollByText(productName);
		for (int i = 0; i < productsPage.getProductCount(); i++) 
		{
			addedProduct = productsPage.addToCartByProduct(i, productName);	
		}
		Assert.assertEquals(addedProduct, productName);
		cartPage = productsPage.goToCartPage();
	}
}
