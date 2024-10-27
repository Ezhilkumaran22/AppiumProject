package org.ListenersTest;

import java.io.IOException;
import org.ExtentNG.utils.ExtentsReportTestNG;
import org.MobileFramework.Utils.AndroidActions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenersTest implements ITestListener
{
	ExtentReports extent = ExtentsReportTestNG.getReporterObject();
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getMethod().getMethodName());
	}
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "Test passed");
	}
	@Override
	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());
		try {
			test.addScreenCaptureFromPath(AndroidActions.TakeScreenshot(result.getMethod().getMethodName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
}