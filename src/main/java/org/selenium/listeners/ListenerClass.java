/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Selenium Java Test Framework & Best Practices - Masterclass (https://www.udemy.com/course/selenium-java-test-framework/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package org.selenium.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.selenium.reports.ExtentLogger;
import org.selenium.reports.ExtentReport;
import org.testng.*;

import java.io.IOException;
import java.util.Arrays;

import static org.selenium.constants.FrameworkConstants.*;

public class ListenerClass implements ITestListener, ISuiteListener {


	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReports();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}

	@Override
	public void onTestStart(ITestResult result) {


		ExtentReport.createTest(result.getMethod().getMethodName());
		// ExtentReport.createTest(result.getMethod().getDescription());
		// ExtentLogger.info("<b>" +
		// BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + "</b>");
/*		//ExtentLogger.info("<b>" + IconUtils.getOSIcon() + "  &  " + IconUtils.getBrowserIcon() + " --------- "
				+ BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + "</b>");*/

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//count_passedTCs = count_passedTCs + 1;
		String logText = "<b>" + result.getMethod().getMethodName() + " is passed.</b>";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentLogger.pass(markup_message);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//count_failedTCs = count_failedTCs + 1;
		ExtentLogger.fail(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
				+  " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
				+ "</details> \n";

		ExtentLogger.fail(message);

		String logText = "<b>" + result.getMethod().getMethodName() + " is failed.</b>";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentLogger.fail(markup_message, true);

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		//count_skippedTCs = count_skippedTCs + 1;

		ExtentLogger.skip(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
		// ExtentLogger.skip("<b><i>" + result.getThrowable().toString() + "</i></b>");
		String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		ExtentLogger.skip(markup_message, true);

	}



}
