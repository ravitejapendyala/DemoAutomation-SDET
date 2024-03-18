/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Selenium Java Test Framework & Best Practices - Masterclass (https://www.udemy.com/course/selenium-java-test-framework/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 * Tutor: Amuthan Sakthivel (https://www.testingminibytes.com/)
 */

/***************************************************/

package org.selenium.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.selenium.constants.FrameworkConstants;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.selenium.constants.FrameworkConstants.*;

public final class ExtentReport {

	private ExtentReport() {
	}

	private static ExtentReports extent;

	public static void initReports() throws IOException {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH-mm-ss");
			String path =  "SparkReport " + now.format(formatter);
			File file = new File("test-output/"+path+"/extentReport.html");
			ExtentSparkReporter spark = new ExtentSparkReporter(file);
			/*
			 * .viewConfigurer() .viewOrder() .as(new ViewName[] { ViewName.DASHBOARD,
			 * ViewName.TEST, //ViewName.TAG, ViewName.CATEGORY, ViewName.AUTHOR,
			 * ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG }) .apply();
			 */

			/*
			 * You can even update the view of the ExtentRerport - Whta do you want to you
			 * first, you can prioritize
			 */
			/*
			 * ExtentSparkReporter spark = new
			 * ExtentSparkReporter(REPORTS_SPARK_CUSTOMISED_HTML).viewConfigurer().viewOrder
			 * () .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY
			 * }).apply();
			 */
			extent.attachReporter(spark);

			// spark.config().setEncoding("utf-8");
			spark.config().setTheme(Theme.DARK);
			spark.loadXMLConfig("spark-config.xml");
			//spark.config().setDocumentTitle(FrameworkConstants.getProjectName() + " - ALL");
			//spark.config().setReportName(FrameworkConstants.getProjectName() + " - ALL");


		}
	}

	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createTest(String testCaseName) {
		// ExtentManager.setExtentTest(extent.createTest(testCaseName));
		ExtentManager.setExtentTest(extent.createTest(testCaseName));
	}






}
