package pages;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "reports/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("Appium Automation Report");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}
