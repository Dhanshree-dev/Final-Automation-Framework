package com.sauce.listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sauce.tests.LoginTests;
import com.sauce.utils.ExtentReportUtil;
import com.sauce.utils.ScreenshotUtil;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent = ExtentReportUtil.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((LoginTests) currentClass).driver;

        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
        test.get().fail(result.getThrowable())
              .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
