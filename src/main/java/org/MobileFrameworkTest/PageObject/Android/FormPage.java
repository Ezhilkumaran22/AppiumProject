package org.MobileFrameworkTest.PageObject.Android;

import java.util.List;
import org.MobileFramework.Utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions
{
	AndroidDriver driver;
	//Parameterized constructor
	public FormPage(AndroidDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//Locators	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement radioMaleBtn;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement radioFemaleBtn;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letShopBtn;
	@AndroidFindBy(xpath = "//android.widget.Toast")
	private List<WebElement> errorMssage;
	
	//Action Methods
	public void enterName(String name) throws InterruptedException 
	{
		Thread.sleep(5000);
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	public void clickGenderOption(String gender) 
	{
		if(gender.equalsIgnoreCase("male")) 
		{
			radioMaleBtn.click();
		}
		else 
		{
			radioFemaleBtn.click();
		}
	}
	public void selectCountryOption(String country) 
	{
		countryDropdown.click();
		scrollByText(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
	}
	public ProductsPage clickLetShopBtn() throws InterruptedException 
	{
		letShopBtn.click();
		Thread.sleep(2000);
		return new ProductsPage(driver);	
	}
	public boolean getToastErrorMessage() 
	{
		return errorMssage.size() < 1;
	}
}
