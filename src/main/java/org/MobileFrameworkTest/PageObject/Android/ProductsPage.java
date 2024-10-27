package org.MobileFrameworkTest.PageObject.Android;

import java.util.List;
import org.MobileFramework.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage extends AndroidActions
{
	AndroidDriver driver;
	
	public ProductsPage (AndroidDriver driver) 
	{
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> productName;
	
	public void addToCartItemByIndex(int index) 
	{
		addToCart.get(index).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException 
	{
		cart.click();
		Thread.sleep(3000);
		return new CartPage(driver);
	}
	
	public int getProductCount() 
	{
		return productName.size();
	}
	
	public String addToCartByProduct(int index, String product) 
	{
		String prod="";
		if(productName.get(index).getText().equalsIgnoreCase(product)) 
		{
			prod = productName.get(index).getText();
			addToCart.get(index).click();
		}
		return prod;
	}
}
