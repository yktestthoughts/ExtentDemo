package com.OrangeHrm.ExtentOrangeHrm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
	
	static LoginPage lg;
	
	
	
	@Test(priority = 2)
	public void validLoginTest() {
		
		test = report.createTest("Test case to verify login with valid credentials");
		test.info("Username: Admin "+" Password: admin123");
		
		lg = new LoginPage(driver);
		lg.userLogin("Valid","Admin", "admin123");
	
		boolean logo = driver.findElement(By.cssSelector("#branding > a:nth-child(1) > img")).isDisplayed();
		
		assertTrue(logo);
		
		
		
	}
	

	@Test(dataProvider = "invalid data",priority = 1)
	public void invalidLoginTest(String uname, String pwd) {
		
		test = report.createTest("Test case to verify login with invalid credentials");
		test.info("Username: "+uname+" Password: "+pwd);
		
		lg = new LoginPage(driver);
		lg.userLogin("Invalid", uname, pwd);
		
		String actMessage = driver.findElement(By.id("spanMessage")).getText();
		String expMessage =  "Invalid credentials";
		
		assertEquals(actMessage, expMessage);
		
		
	}
	
	
	@DataProvider(name = "invalid data")
	public Object[][] getData(){
		return new Object[][] {{"admi","admin123"},{"Admin","admin"},{"adm","adm"}};
	}
}
