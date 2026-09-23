package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    WebDriver driver;

    // Locators
    By inventoryItems = By.className("inventory_item");
    By cartIcon = By.id("shopping_cart_container");
    By addToCartButtons = By.cssSelector(".btn_inventory");
    By productTitle = By.className("inventory_item_name");
    By productPrice = By.className("inventory_item_price");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Get all product names
    public List<WebElement> getAllProducts() {
        return driver.findElements(productTitle);
    }

    // ✅ Get all product prices
    public List<WebElement> getAllPrices() {
        return driver.findElements(productPrice);
    }

    // ✅ Add product to cart by name
    public void addProductToCart(String productName) {
        List<WebElement> products = driver.findElements(inventoryItems);
        for (WebElement product : products) {
            String name = product.findElement(productTitle).getText();
            if (name.equalsIgnoreCase(productName)) {
                product.findElement(addToCartButtons).click();
                break;
            }
        }
    }

    // ✅ Navigate to cart
    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}