package com.sauce.tests;


import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import com.sauce.pages.LoginPage;
import com.sauce.pages.InventoryPage;
import com.sauce.pages.CartPage;

public class CartTests {
    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        // Login first
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }  
    
   /* @BeforeMethod
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
		//log.info("Browser Opened up and maximized");
		driver.get("https://www.saucedemo.com/");
		//log.info("Navigate to saucedemo website");
		loginPage = new LoginPage(driver);
	}  */

	@AfterMethod
	public void browserClose() {
		driver.close();
		//log.info("Browser Closed");
	}

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // 1. Add single item to cart
    @Test
    public void testAddSingleItem() {
        inventoryPage.addFirstItem();
        cartPage.openCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 1);
    }

    // 2. Add multiple items to cart
    @Test
    public void testAddMultipleItems() {
        inventoryPage.addFirstItem();
        inventoryPage.addSecondItem();
        cartPage.openCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 2);
    }

    // 3. Remove item from cart
    @Test
    public void testRemoveItem() {
        inventoryPage.addFirstItem();
        cartPage.openCart();
        cartPage.removeFirstItem();
        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }

    // 4. Continue shopping from cart
    @Test
    public void testContinueShopping() {
        inventoryPage.addFirstItem();
        cartPage.openCart();
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    // 5. Checkout button navigation
    @Test
    public void testCheckoutNavigation() {
        inventoryPage.addFirstItem();
        cartPage.openCart();
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }

    // 6. Empty cart validation
    @Test
    public void testEmptyCart() {
        cartPage.openCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }
}

