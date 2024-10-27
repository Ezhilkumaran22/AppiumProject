package org.demo.report;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreportDemo 
{
	ExtentReports extent;
	@BeforeTest
	public void config() 
	{
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\index.html");
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tested by", "Ezhilkumaran");
	}
	
	@Test
	public void initialDemo() 
	{
		extent.createTest("Initial Test Demo");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		driver.close();
		extent.flush();
	}
}
