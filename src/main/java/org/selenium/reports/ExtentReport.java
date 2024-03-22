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
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
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
	}

	public static void createTest(String testCaseName) {
		ExtentManager.setExtentTest(extent.createTest(testCaseName));
	}






}
