package com.sauce.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    // Locators
    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");
    By errorMsg = By.cssSelector("h3[data-test='error']");
    By menuBtn = By.id("react-burger-menu-btn");
    By logoutLink = By.id("logout_sidebar_link");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) throws Exception {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        Thread.sleep(5000);
        driver.findElement(loginBtn).click();
        Thread.sleep(5000);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }

    public void logout() throws Exception {
    	Thread.sleep(5000);
        driver.findElement(menuBtn).click();
        driver.findElement(logoutLink).click();
    }
}
