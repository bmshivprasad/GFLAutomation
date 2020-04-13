package gflwishes.base;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import gflwishes.utilities.Configurations;
import gflwishes.utilities.ExcelUtils;
import gflwishes.utilities.ExtentInitializer;
import gflwishes.utilities.ZipUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.internal.Utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EnhancedBaseClass extends ExtentInitializer implements Configurations {

    public WebDriver wishesDriver;
    public WebDriver fleetMapperDriver;
    public static Logger log4j = Logger.getLogger("EnhancedBaseClass");
    public SoftAssert sa;
    public ExcelUtils excelUtils = new ExcelUtils();
    public static String methodName = "";

    @BeforeSuite(alwaysRun = true)
    public void startReport(ITestContext testContext) {
        System.setProperty("logtime", new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
        PropertyConfigurator.configure("Log4j.properties");
        ExtentInitializer.initializeReport(testContext.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {

        sa = new SoftAssert();
        methodName = method.getName();

        if (methodName.contains("WS")) {
            wishesDriver = initiateDriver(wishesDriver);
            wishesDriver.get(BASE_URL);
        } else {
            fleetMapperDriver = initiateDriver(fleetMapperDriver);
            fleetMapperDriver.get(FM_URL);
        }

    }

    private WebDriver initiateDriver(WebDriver driver) {

        if (driver == null) {
            switch (BROWSER.toLowerCase()) {
                case "mozilla":
                case "firefox":
                case "mozilla firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                case "internet explorer":
                case "ie11":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(IMPLICIT_WAIT), TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) {

        String testName;
        String screenshotPath;

        ITestContext ex = testResult.getTestContext();

        try {
            testName = testResult.getName();
            if (testResult.getStatus() == ITestResult.FAILURE) {

                logger.log(Status.FAIL, MarkupHelper.createLabel(testName + " : FAILED", ExtentColor.RED));
                logger.log(Status.FAIL,
                        MarkupHelper.createLabel(testResult.getThrowable() + " : FAILED", ExtentColor.RED));
                if (methodName.contains("WS")) screenshotPath = getExtentScreenShot(wishesDriver, testName, false);
                else screenshotPath = getExtentScreenShot(fleetMapperDriver, testName, false);
                logger.fail("Test Case Failed Snapshot is attached below");
                logger.addScreenCaptureFromPath(screenshotPath);
                log4j.error(testName + " : Fail");
                Reporter.setCurrentTestResult(testResult);

                failure(testName);

                getShortException(ex.getFailedTests());

            } else if ((testResult.getStatus() == ITestResult.SUCCESS)) {
                logger.log(Status.PASS, MarkupHelper.createLabel(testName + " : PASSED", ExtentColor.GREEN));
                log4j.info(testName + " : Pass");
                System.out.println("TEST PASSED - " + testName + "\n");
            } else if ((testResult.getStatus() == ITestResult.SKIP)) {
                logger.log(Status.SKIP, MarkupHelper.createLabel(testName + " : SKIPPED", ExtentColor.ORANGE));
                log4j.info(testName + " : Skip");
            }

        } catch (Exception throwable) {
            System.err.println("Exception ::\n" + throwable);
        }
    }

    @AfterClass
    public void cleanUp() {
        log4j.info("<strong>+++++++++++++++++++++++++++++++++ Closing the " + BROWSER +
                " browser instance +++++++++++++++++++++++++++++++++</strong>");
        cleanupDriver(fleetMapperDriver);
        cleanupDriver(wishesDriver);
    }

    public void cleanupDriver(WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void endReport() {
        ExtentInitializer.flushReport();
        ZipUtils.zipFolder(PROJECT_DIR + File.separator + "ExtentReports" + File.separator +
                "Report_" + currentDateTime);
    }

    public static String getExtentScreenShot(WebDriver driver, String screenshot_name, boolean isStepFailure) {
        String destination = "";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            File directory = new File(PROJECT_DIR + File.separator + "ExtentReports" + File.separator +
                    "Report_" + currentDateTime + File.separator + "Screenshots");
            if (!directory.exists()) directory.mkdir();

            File finalDestination = new File(directory + File.separator + screenshot_name + currentDateTime + ".png");

            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            testWarningLog("Failed to capture screenshot: " + e.getMessage());
        }

        if (isStepFailure)
            testStepsLog("Step Failure<br>Please check attached screenshot : <br><br>" + getScreenshotLink(screenshot_name +
                    currentDateTime + ".png"));

        return destination;
    }

    public static String getCurrentTimeStampString() {

        Date date = new Date();

        SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmssSS");
        TimeZone timeZone = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone.getOffset(date.getTime()), "GMT"));
        sd.setCalendar(cal);
        return sd.format(date);
    }

    public static void testCaseLog(String log) {
        logger = extent.createTest(log);
        logger.assignAuthor("Chandrakant Chavda");
        log4j.info(log);
        log4j.info("+++++++++++++++++++++++++++++++++ Opening the " + BROWSER +
                " browser +++++++++++++++++++++++++++++++++");
    }

    public static void testStepsLog(String log) {
        logger.info(log);
        log4j.info(log);
    }

    public static void testFailLog(String log) {
        logger.fail(log);
        log4j.error(log);
    }

    public static void testWarningLog(String log) {
        logger.warning(MarkupHelper.createLabel(log, ExtentColor.AMBER));
        log4j.warn(log);
    }

    public void success(String log) {
        logger.pass(MarkupHelper.createLabel(log + " : PASS", ExtentColor.GREEN));
        log4j.info(log + " : PASS");
    }

    public void failure(String log) {
        sa.assertTrue(false, log);
        if (methodName.contains("WS")) getExtentScreenShot(wishesDriver, getCurrentTimeStampString(), true);
        else getExtentScreenShot(fleetMapperDriver, getCurrentTimeStampString(), true);
        logger.fail(MarkupHelper.createLabel(log + " : FAIL", ExtentColor.RED));
        log4j.error(log + " : FAIL");
    }

    public static String getScreenshotLink(String screenshot_name) {
        return "<img src='../Report_" + currentDateTime + "/Screenshots/" + screenshot_name + "' width='683' height='384'/>";
    }

    public static void getShortException(IResultMap tests) {

        for (ITestResult result : tests.getAllResults()) {

            Throwable exception = result.getThrowable();
            List<String> msgs = Reporter.getOutput(result);
            boolean hasReporterOutput = msgs.size() > 0;
            boolean hasThrowable = exception != null;
            if (hasThrowable) {
                boolean wantsMinimalOutput = result.getStatus() == ITestResult.SUCCESS;
                if (hasReporterOutput) {
                    testStepsLog((wantsMinimalOutput ? "Expected Exception" : "Failure Reason:"));
                }

                String str = Utils.shortStackTrace(exception, true);
                System.out.println(str);
                @SuppressWarnings("resource")
                Scanner scanner = new Scanner(str);
                String firstLine = scanner.nextLine();
                testWarningLog(firstLine);
            }
        }
    }

}