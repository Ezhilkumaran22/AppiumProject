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

public class HybridApptest extends MobileBaseTestClass
{
	public ProductsPage productsPage;
	public CartPage cartPage;
	
	@DataProvider(name = "HybridTestData")
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String, String>> jsonData = getJsonData(System.getProperty("user.dir")+"\\src\\main\\java\\org\\MobileFrameworkTestData\\EcommerceTestData.json");
		
		return new Object[][] {
			{jsonData.get(0)},
			{jsonData.get(1)},
			{jsonData.get(2)}
		};
	}
	
	@Test(dataProvider = "HybridTestData")
	public void hybrid_Test_01(HashMap<String, String> inputData) throws InterruptedException 
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
		
		//Sum of 2 products validations
		double sumOfProduct = cartPage.getProductSum();
		String totalProductAmount = cartPage.getProductTotalAmount();
		double expectedTotalAmt = formPage.getFormattedAmount(totalProductAmount);
		Assert.assertEquals(sumOfProduct, expectedTotalAmt, "Both product calculated amount & Product total amount are not equal");
		
		cartPage.clickAcceptTermsAndConditionButton();
		cartPage.submitOrder();
		
//		Set<String> contextHandles = driver.getContextHandles();	
//		for (String contexts : contextHandles) 
//		{
//			System.out.println(contexts);
//		}
//		driver.context("WEBVIEW_com.androidsample.generalstore");
//		
//		//driver.findElement(By.name("q")).sendKeys("Ezhil Test");
//		driver.findElement(By.name("q")).sendKeys("https://www.facebook.com");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		driver.findElement(By.xpath("//*[contains(text(),'Facebook")).click();
//		Thread.sleep(6000);
//		
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
//		driver.context("NATIVE_APP");
	}
}
