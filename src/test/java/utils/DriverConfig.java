package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverConfig {
	
	WebDriver driver;
	
	public DriverConfig() throws IOException {
		
		String browsertype = DriverConfig.getProp("Browser");
		
		if(browsertype.equalsIgnoreCase("chrome")) 
			this.driver = new ChromeDriver();
		
		else if(browsertype.equalsIgnoreCase("firefox"))
			this.driver = new FirefoxDriver();
		
		else if(browsertype.equalsIgnoreCase("edge"))
			this.driver = new EdgeDriver();
		
	}
	
	public WebDriver getDriver() {
		return driver;	
	}
	
	public static String getProp(String key) throws IOException {
		Properties prop = new Properties();
		String file = System.getProperty("user.dir")+"//src//test//java"+"//utils//Property.properties";
		FileInputStream File = new FileInputStream(file);
		prop.load(File);
		return prop.getProperty(key);
	}

}
