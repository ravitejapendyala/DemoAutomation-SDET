

package org.selenium.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.enums.WaitStrategy;
import org.selenium.reports.ExtentLogger;
import org.selenium.utils.BrowserUtils;
import org.selenium.utils.CustomException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DatePicker extends BasePage {

	Faker faker;
	BrowserUtils browserUtils;

	public DatePicker(WebDriver driver) {
		super(driver);
		faker = new Faker();
		browserUtils = new BrowserUtils();

	}

	private final By datePickerEnabled = By.xpath("//*[@id='datepicker2']");
	private final String datePickerDisabled = "datepicker1";




	public DatePicker load() {
		load("Datepicker.html");
		wait.until(ExpectedConditions.titleContains("Datepicker"));
		return this;
	}

	public void EnterDateForDatePickerEnabled() throws CustomException, InterruptedException {
		SendKeys(datePickerEnabled,"20/03/2024",WaitStrategy.PRESENCE,"Date Picker Enabled");
		waitForGivenTime(2);
		ExtentLogger.pass("Entered date into Date Picker Enabled",true);
	}
	public void EnterDateForDatePickerDisabled() throws CustomException, InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		LocalDate currentDate = LocalDate.now();

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Format the current date into the desired format
		String formattedDate = currentDate.format(formatter);
		js.executeScript("document.getElementById('"+datePickerDisabled+"').value='"+formattedDate+"'");
		waitForGivenTime(2);
		ExtentLogger.pass("Entered date into Date Picker Disabled",true);
	}









}
