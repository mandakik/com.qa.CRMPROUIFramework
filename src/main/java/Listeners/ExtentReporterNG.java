package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import static Listeners.WebEventListener.screenshotPath;

public class ExtentReporterNG implements IReporter {

    private ExtentReports extent;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(outputDirectory + File.separator + "Extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);


        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                try {
                    buildTestNodes(context.getPassedTests(), Status.PASS);
                    buildTestNodes(context.getFailedTests(), Status.FAIL);
                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        extent.flush();
        //extent.close();
    }

    private void buildTestNodes(IResultMap tests, Status status) throws IOException {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());


                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    test.log(Status.FAIL, result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());

                } else {
                    test.log(status, "Test " + status.toString().toLowerCase()
                            + "ed");
                }

                //extent.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
