package org.ExtentNG.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentsReportTestNG {
	public static ExtentReports extent;

	public static ExtentReports getReporterObject() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\reports\\index.html");
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tested by", "Ezhilkumaran");
		return extent;
	}
}
