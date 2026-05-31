package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class utilsExtentManage {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
            reporter.config().setReportName("Appium Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}
