package com.sauce.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

//import com.sauce.listeners.ExtentTestNGListener;
import com.sauce.pages.LoginPage;
import com.sauce.utils.DataProviders;

//@Listeners(ExtentTestNGListener.class)
public class LoginTests1 {
	public WebDriver driver;
	LoginPage loginPage;
	protected Logger log = LogManager.getLogger(LoginTests1.class);

	@BeforeClass
	public void setup() {
     log.info("Test automation Set up is ready");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		log.info("CLosing all open browser sessions");
	}

	@BeforeMethod
	public void browserOpen() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		log.info("Browser Opened up and maximized");
		driver.get("https://www.saucedemo.com/");
		log.info("Navigate to saucedemo website");
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void browserClose() {
		driver.close();
		log.info("Browser Closed");
	}
	
	// 1. Positive Login
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void testLoginWithExcel(String username, String password, String expectedResult) throws Exception {
		loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		if (expectedResult.equalsIgnoreCase("success")) {
			Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
		} else {
			Assert.assertTrue(loginPage.getErrorMessage().length() > 0);
		}
	}

}