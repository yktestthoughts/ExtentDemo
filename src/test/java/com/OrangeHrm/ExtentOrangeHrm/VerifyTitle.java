package com.OrangeHrm.ExtentOrangeHrm;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class VerifyTitle extends BaseTest{
	
	@Test
	public void verifyTitleTest() {
		
		test = report.createTest("Test case to verify the title");
		String expTitle = "OrangeHrm";
		String actTitle = driver.getTitle();
		test.info("actual title: "+ actTitle);
		assertEquals(actTitle, expTitle);
		
	}

}
