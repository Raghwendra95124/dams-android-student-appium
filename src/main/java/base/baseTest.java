package base;

import com.aventstack.extentreports.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.ExtentManager;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;

public class baseTest {

    public AndroidDriver driver;

    protected ExtentReports extent;
    protected ExtentTest test;

    // ✅ REPORT START
    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    // ✅ DRIVER SETUP + TEST START
    @BeforeMethod
    public void setup(Method method) throws Exception {

        // 🔹 Start Extent Test
        test = extent.createTest(method.getName());

        // 🔹 Appium Options for Wi-Fi device
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");

        // ✅ YOUR WIFI DEVICE UDID (MOST IMPORTANT)
        options.setUdid("192.168.29.176:5555");

        // Device name kuch bhi de sakte ho
        options.setDeviceName("AndroidWifiDevice");

        options.setAppPackage("com.emedicoz.app");
        options.setAppActivity("com.emedicoz.app.login.activity.SplashActivity");

        options.setAutoGrantPermissions(true);
        options.setNewCommandTimeout(Duration.ofSeconds(300));

        // ✅ Appium Server URL
        URL url = URI.create("http://0.0.0.0:4723/").toURL();

        driver = new AndroidDriver(url, options);
    }

    // ✅ RESULT HANDLING + SCREENSHOT
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String path = takeScreenshot(result.getName());
            test.fail(result.getThrowable());
            if (path != null) {
                test.addScreenCaptureFromPath(path);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else {
            test.skip("Test Skipped");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    // ✅ REPORT FLUSH
    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    // ✅ SCREENSHOT METHOD
    public String takeScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + testName + ".png";
            File dest = new File(path);
            Files.copy(src.toPath(), dest.toPath());
            return path;
        } catch (Exception e) {
            return null;
        }
    }
}
