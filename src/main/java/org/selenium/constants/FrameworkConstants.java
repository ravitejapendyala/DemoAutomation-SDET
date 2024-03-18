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

package org.selenium.constants;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FrameworkConstants {

	private static final String PROJECT_PATH = System.getProperty("user.dir");

	public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";
	/* style="text-align:center;" */
	public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/rajat-v-3b0685128/";
	

	public static final String YES = "yes";
	public static final String NO = "no";

	private static final int EXPLICIT_WAIT = 10;
	

	
	private static String extentReportFilePath = "";

	/** Zip file of Extent Reports */
	public static final String Zipped_ExtentReports_Folder_Name = "ExtentReports.zip";

	private static final String Project_Name = "Automation Test Suite Report - Master Selenium Framework";

	public static String getProjectPath() {
		return PROJECT_PATH;
	}

	public static String getProjectName() {
		return Project_Name;
	}

	public static String getZipped_ExtentReports_Folder_Name() {
		return Zipped_ExtentReports_Folder_Name;
	}

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
