package com.OrangeHrm.ExtentOrangeHrm;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.DriverConfig;

public class BaseTest {
	
	static WebDriver driver;
	
	static ExtentSparkReporter spark;
	static ExtentReports report;
	static ExtentTest test;
	
	//Setting up report template and systeminfos
	@BeforeSuite
	public void setUp() throws IOException {
		
		String file = System.getProperty("user.dir")+"//Reports//report"+getNow()+".html";
		
		spark = new ExtentSparkReporter(file);
		spark.config().setDocumentTitle("Regression Test");
		spark.config().setReportName("Orange HRM");
		spark.config().setTheme(Theme.STANDARD);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		
		report.setSystemInfo("Host Name", "Local Host");
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", DriverConfig.getProp("browser"));
		report.setSystemInfo("Tester's Name", "QA Team");
		
	}
	
	@BeforeClass
	public void openBrowser() throws IOException {
		
		String browsertype = DriverConfig.getProp("Browser");
		
		DriverConfig Driver = new DriverConfig();
		if(browsertype.equalsIgnoreCase("chrome")) 
			
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		else if(browsertype.equalsIgnoreCase("firefox"))
			
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		
		else if(browsertype.equalsIgnoreCase("edge"))
			
			System.setProperty("webdriver.gecko.driver", "msedgedriver.exe");
			
		
		
		driver = Driver.getDriver();
		driver.get(DriverConfig.getProp("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	
	}
	
	@AfterMethod
	public void logStatus(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " the test case failed due to following details", ExtentColor.RED));
			test.fail(result.getThrowable());
			
			Random rand = new Random();
			String file = System.getProperty("user.dir")+"//screenshot"+rand.nextInt()+".jpeg";
			
			
			try {
				
				getScreenshot(file);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			test.addScreenCaptureFromPath(file);
			
			
		}
		
		else if(result.getStatus() == ITestResult.SUCCESS) {
			
			test.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " the test case passed", ExtentColor.GREEN));
			
		}
		
		else if(result.getStatus() == ITestResult.SKIP) {
			
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName()+ " the test case skipped", ExtentColor.YELLOW));
			
		}
		
	}
	
	@AfterClass
	public void closeBrowser() {
		
		driver.quit();
		
	}
	
	@AfterSuite
	public void closeReport() {
		
		report.flush();
		
	}
	
	
	public static void getScreenshot(String filewithpath) throws IOException {
		
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		File destfile = new File(filewithpath);
		FileHandler.copy(srcfile, destfile);
		
	}
	
	   public static String getNow() {
		   String formattedDate = "";
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss",
	                Locale.ENGLISH);/* w  ww .  j av  a  2s.  co  m*/
	        formattedDate = sdf.format(new java.util.Date());
	        
	        return formattedDate;
	    }
	
	

}
