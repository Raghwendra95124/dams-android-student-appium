package pages;

import base.baseTest;
import com.aventstack.extentreports.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();
        AndroidDriver driver = ((baseTest) testClass).driver;

        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());

        test.get().fail("Test Failed ❌");
        test.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped ⚠️");
    }

}
