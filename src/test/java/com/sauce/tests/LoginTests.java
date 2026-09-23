package com.sauce.tests;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import com.sauce.listeners.ExtentTestNGListener;
import com.sauce.pages.LoginPage;


@Listeners(ExtentTestNGListener.class)
public class LoginTests {
	public WebDriver driver;
	LoginPage loginPage;
	protected Logger log = LogManager.getLogger(LoginTests.class);

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
		ChromeOptions options = new ChromeOptions();

		// Disable password manager and credential services
		options.addArguments("--disable-save-password-bubble");
		options.setExperimentalOption("prefs", Map.of(
		    "credentials_enable_service", false,
		    "profile.password_manager_enabled", false
		));
		driver = new ChromeDriver(options);
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
	@Test
	public void testValidLogin() throws Exception {
		log.info("testValidLogin test case started");
		loginPage.login("standard_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
		log.info("Login is successful");
		log.info("testValidLogin test case completed");
	}

	// 2. Logout after login
	// @Test(dependsOnMethods = "testValidLogin")
	@Test
	public void testLogout() throws Exception {
		log.info("testLogout test case started");
		loginPage.login("standard_user", "secret_sauce");
		log.info("Login is successful");
		loginPage.logout();
		Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
		log.info("Logout is successful");
		log.info("testLogout test case completed");
	}

	// 3. Invalid credentials
	@Test
	public void testInvalidLogin() throws Exception {
		log.info("testInvalidLogin test case started");
		loginPage.login("wrong_user", "wrong_pass");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
		log.info("Error message");
		log.info("testInvalidLogin test case completed");
	}

	// 4. Locked out user
	@Test
	public void testLockedOutUser() throws Exception {
		log.info("testLockedOutUser test case started");
		loginPage.login("locked_out_user", "secret_sauce");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out"));
		log.info("Error message");
		log.info("testLockedOutUser test case completed");
	}

	// 5. Empty fields
	@Test
	public void testEmptyFields() throws Exception {
		log.info("testEmptyFields test case started");
		loginPage.login("", "");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
		log.info("Error message");
		log.info("testEmptyFields test case completed");
	}
}
