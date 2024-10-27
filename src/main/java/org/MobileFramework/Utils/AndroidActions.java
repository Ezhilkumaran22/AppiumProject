package org.MobileFramework.Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions
{
	static AndroidDriver driver;
	JavascriptExecutor js;
	public boolean scrollTillLast;
	WebDriverWait wait;
	
	public AndroidActions(AndroidDriver driver)
	{
		this.driver = driver;
	}
	
	public static String TakeScreenshot(String testcaseName) throws IOException 
	{	
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"\\screenshot\\"+testcaseName+".png";
		FileUtils.copyFile(src, new File(destinationPath));
		return destinationPath;
	}
	
	public void WaitForElementText(WebElement element, String prodText)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(element, "text", prodText));
	}
	
	public void packageAndActivitiesActions(String path) 
	{
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("mobile: startActivity", ImmutableMap.of(
				"intent", path));
	}
	
	public void scrollByText(String text)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))"));
	}
	
	public void scrollDown(String dir) 
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("mobile: scrollGesture",ImmutableMap.of("left",200, "top", 200, "width", 400,"height", 400, "direction",dir,"percent",1.0));
	}
	
	public void scrollTillLast(String dir)
	{	
		do {
			scrollTillLast = (boolean) js.executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top",
					100, "width", 200, "height", 200, "direction", dir, "percent", 1.0));
		} while (scrollTillLast);
	}
	
	public void scrollTillLastUsingElement(WebElement ele, String dir) 
	{
		js.executeScript("mobile: scrollGesture", ImmutableMap.of("elementId", 
				((RemoteWebElement) ele).getId(),
			    "direction", dir,
			    "percent", 0.20
			));
	}

	public void longPressAction(WebElement ele) 
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement)ele)), "duration", 2000);
	}
	
	public double getFormattedAmount(String amount) 
	{
		String stringAmount = amount.substring(1);
		double price = Double.parseDouble(stringAmount);
		return price;
	}
	
	public void swipeAction(WebElement firstPicture, String direction) 
	{
		js.executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement) firstPicture).getId(),
				"direction",direction, "percent", 0.20
				));
	}
	
	public void dragAndDropAction(WebElement source, int x, int y) throws InterruptedException 
	{
		js.executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) source).getId(),
			    "endX", x,
			    "endY", y
			));
	}
}
