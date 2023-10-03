package com.geico.base;

import static com.geico.constants.IBrowserConstant.*;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.geico.constants.ConfigConstant;
import com.geico.pages.HomePage;
import com.geico.utils.ReadConfig;
import com.geico.utils.ReadFile;
import com.geico.common.CommonActions;
import com.geico.common.CommonActions.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig conf;
	public WebDriver driver;// new chromDriver();
	public HomePage homePage;
	protected WebElement element;
public Actions actions;
public Select select;
public ReadFile readFile;
public ExtentTest extentTest;
ExtentReports report;
	@BeforeClass
	public void beforeClassSetUp() {
		conf = new ReadConfig();
	}

	@Parameters(BROWSER)
	@BeforeMethod

	public void setUpDriver(@Optional(CHROME) String browserName) {
		driver = initializingBrowser(browserName);
		driver.manage().window().maximize();
		int pageLoadTime = Integer.parseInt(conf.getValue(ConfigConstant.pageLoadTime));
		int implicitWaitTime = Integer.parseInt(conf.getValue(ConfigConstant.impliciteWaitLoad));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
		driver.get(conf.getValue(ConfigConstant.url));
		initPages();
	}

	public WebDriver initializingBrowser(String browserName) {
		switch (browserName) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		case EDGE:
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver();
		case SAFARI:
			WebDriverManager.safaridriver().setup();
			return new SafariDriver();
		default:
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
		}
	}

	public void initPages() {
		homePage = new HomePage(driver);
	}

	
	//@AfterMethod
	public void afterEachTest(Method method, ITestResult result) {
		for(String group: result.getMethod().getGroups()) {
			extentTest.assignCategory(group);
		}
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test PASSED");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.addScreenCaptureFromPath(CommonActions.getSreenShot(method.getName(), driver));
			extentTest.log(Status.FAIL, "Test FAILED");
		}else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test SKIPPED");
		}
		tearUp();
	}
	@AfterMethod
	public void tearUp() {
		driver.quit();
	}
	
	@AfterSuite
	public void publishReport() {
		report.flush();
	}
}



