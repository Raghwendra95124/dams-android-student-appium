package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(AndroidDriver driver, String testName) {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = "screenshots/" + testName + "_" + timeStamp + ".png";

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);

            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());

            return path;

        } catch (Exception e) {
            return "Screenshot Failed";
        }
    }
}
