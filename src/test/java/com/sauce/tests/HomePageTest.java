package com.sauce.tests;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import com.sauce.pages.LoginPage;
import com.sauce.pages.HomePage;

public class HomePageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
    	ChromeOptions options = new ChromeOptions();

    	// Disable password manager and credential services
    	options.addArguments("--disable-save-password-bubble");
    	options.setExperimentalOption("prefs", Map.of(
    	    "credentials_enable_service", false,
    	    "profile.password_manager_enabled", false
    	));
    	
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyProductsDisplayed() throws Exception {
        loginPage.login("standard_user", "secret_sauce");
        homePage = new HomePage(driver);

        int productCount = homePage.getAllProducts().size();
        Assert.assertTrue(productCount > 0, "No products found on inventory page!");
        System.out.println("✅ Products displayed: " + productCount);
    }

    @Test
    public void addProductToCartTest() throws Exception {
        loginPage.login("standard_user", "secret_sauce");
        homePage = new HomePage(driver);

        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.goToCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), "Cart page not opened!");
        System.out.println("✅ Product added to cart and navigated to cart page.");
    }
}
