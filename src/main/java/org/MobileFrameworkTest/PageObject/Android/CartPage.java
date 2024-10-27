package org.MobileFrameworkTest.PageObject.Android;

import java.util.List;
import org.MobileFramework.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions
{
	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) 
	{
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productprices;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement productsTotalAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
		
	@AndroidFindBy(id="android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
	
	public double getProductSum() 
	{
		int productCount = productprices.size();
		double sumproduct = 0;
		for (int i = 0; i < productCount; i++)
		{
			String stringProduct = productprices.get(i).getText();
			double actualPrice = getFormattedAmount(stringProduct);
			sumproduct = sumproduct + actualPrice;
			System.out.println(sumproduct);
		}
		return sumproduct;
	}
	
	public String getProductTotalAmount() 
	{
		return productsTotalAmount.getText();
	}
	
	public void clickAcceptTermsAndConditionButton() throws InterruptedException 
	{
		Thread.sleep(2000);
		longPressAction(terms);
		acceptButton.click();
	}
	
	public void submitOrder() 
	{
		checkBox.click();
		proceedButton.click();
	}
}
