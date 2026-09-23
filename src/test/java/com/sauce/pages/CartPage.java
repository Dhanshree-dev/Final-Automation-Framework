package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;

    // Locators
    By cartIcon = By.id("shopping_cart_container");
    By cartItems = By.cssSelector(".cart_item");
    By removeButtons = By.cssSelector(".cart_button");
    By checkoutBtn = By.id("checkout");
    By continueShoppingBtn = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public void removeFirstItem() {
        driver.findElements(removeButtons).get(0).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }
}

