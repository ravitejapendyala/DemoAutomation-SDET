package org.selenium.constants;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FrameworkConstants {

	private static final String PROJECT_PATH = System.getProperty("user.dir");

	public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";
	public static final String ICON_Navigate_Right = "<i class='fa fa-arrow-circle-right' ></i>";


	public static final String YES = "yes";
	private static final int EXPLICIT_WAIT = 10;
	public static final int WAIT = 5;
	private static String extentReportFilePath = "";

	public static String getExtentReportFilePath() {
		File file = null;
		if (extentReportFilePath.isEmpty()) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH-mm-ss");
			String path =  "SparkReport " + now.format(formatter);
			file = new File("test-output/"+path+"/extentReport.html");
		}
		return file.getPath().toString();
	}

	public static int getExplicitWait() {
		return EXPLICIT_WAIT;
	}

}
