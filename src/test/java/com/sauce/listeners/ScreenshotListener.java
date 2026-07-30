package com.sauce.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.sauce.tests.LoginTests;
import com.sauce.utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((LoginTests) currentClass).driver;

        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
        System.out.println("📸 Screenshot saved at: " + screenshotPath);
    }
}

