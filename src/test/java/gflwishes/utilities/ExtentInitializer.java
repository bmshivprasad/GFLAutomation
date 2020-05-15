package gflwishes.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentInitializer implements Configurations {

    protected static ExtentReports extent;
    protected static ExtentTest logger;

    protected static void initializeReport(String suiteName) {

        File directory = new File(PROJECT_DIR + File.separator + "ExtentReports" + File.separator + "Report_" +
                currentDateTime);
        if (!directory.exists()) directory.mkdir();

        ExtentHtmlReporter htmlReporter;
        htmlReporter = new ExtentHtmlReporter(directory + File.separator + "Report_" +
                suiteName + "_" + System.currentTimeMillis() + ".html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));

        htmlReporter.config().setDocumentTitle("GFL");
        htmlReporter.config().setReportName("UAT Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }

    protected static void flushReport() {
        extent.flush();
    }

}
