package com.sauce.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    // Backpack locators
    By backpackAddBtn = By.id("add-to-cart-sauce-labs-backpack");
    By backpackRemoveBtn = By.id("remove-sauce-labs-backpack");

    // Bike Light locators
    By bikeLightAddBtn = By.id("add-to-cart-sauce-labs-bike-light");
    By bikeLightRemoveBtn = By.id("remove-sauce-labs-bike-light");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstItem() {
        driver.findElement(backpackAddBtn).click();
    }

    public void addSecondItem() {
        driver.findElement(bikeLightAddBtn).click();
    }

    public void removeFirstItem() {
        driver.findElement(backpackRemoveBtn).click();
    }

    public void removeSecondItem() {
        driver.findElement(bikeLightRemoveBtn).click();
    }
}
