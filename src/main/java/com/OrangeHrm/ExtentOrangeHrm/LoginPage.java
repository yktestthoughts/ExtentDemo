package com.OrangeHrm.ExtentOrangeHrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id = "txtUsername")
	private WebElement unmae;
	
	@FindBy(id = "txtPassword")
	private WebElement pwd;
	
	@FindBy(id = "btnLogin")
	private WebElement Lgn;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void userLogin(String testcase,String username, String password) {
		
		unmae.sendKeys(username);
		pwd.sendKeys(password);
		
		if(testcase.equalsIgnoreCase("Valid")) {
			Lgn.click();
		}
		
		else if(testcase.equalsIgnoreCase("Invalid")) {
			Lgn.click();
			unmae.clear();
			pwd.clear();
		}
		
	}

}
