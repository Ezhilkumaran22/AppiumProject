package org.BaseTestClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.MobileFrameworkTest.PageObject.Android.FormPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class MobileBaseTestClass 
{
	public AndroidDriver driver;
	public AppiumDriverLocalService activeServer;
	public boolean scrollTillLast;
	public JavascriptExecutor js;
	public FormPage formPage;
	
	@BeforeClass(alwaysRun = true)
	public void configurationAppium() throws URISyntaxException, FileNotFoundException, IOException 
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\Configuration\\resources\\ConigFile.properties")));
		String ipAdd = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String deviceName = prop.getProperty("androidDeviceName");
				
		activeServer = new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				.withAppiumJS(new File("C:\\Users\\ezhil\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(Integer.parseInt(port)).build();
		activeServer.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		//options.setChromedriverExecutable("C:\\\\Users\\\\ezhil\\\\eclipse-workspace\\\\MobileTestingProject\\\\src\\\\test\\\\java\\\\org\\\\utils\\\\chromedriver.exe");
		options.setApp("C:\\Users\\ezhil\\eclipse-workspace\\MobileTestingProject\\src\\test\\java\\org\\utils\\General-Store.apk");
		driver = new AndroidDriver(new URI(ipAdd).toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonPath) throws IOException 
	{
		//JSon file --> JSon string convert
		String jsonStringContent = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);

		//JSon file --> JSon string convert
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonStringContent, new TypeReference<List<HashMap<String, String>>>() {	
		});
		return data;
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws InterruptedException 
	{
		driver.quit();
		activeServer.stop();
	}
}