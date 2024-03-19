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

package org.selenium.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.enums.WaitStrategy;
import org.selenium.reports.ExtentLogger;
import org.selenium.utils.BrowserUtils;
import org.selenium.utils.CustomException;
import org.testng.Assert;


public class Windows extends BasePage {

	Faker faker;
	BrowserUtils browserUtils;

	public Windows(WebDriver driver) {
		super(driver);
		faker = new Faker();
		browserUtils = new BrowserUtils();

	}

	private final By OpenNewSeperateWindows_btn = By.xpath("//a[contains(text(),'Open New Seperate Windows')]");
	private final By OpenSeperateMultipleWindows_btn = By.xpath("//a[contains(text(),'Open Seperate Multiple Windows')]");
	private final By ConfirmAlert = By.xpath("//button[contains(text(),'click the button to display a confirm box')]");
	private final By PromptAlert = By.xpath("//button[contains(text(),'click the button to demonstrate the prompt box')]");
	private final By ConfirmAlert_btn = By.xpath("//a[text()='Alert with OK & Cancel ']");
	private final By PromptAlert_btn = By.xpath("//a[text()='Alert with Textbox ']");


	public Windows load() {
		load("Windows.html");
		wait.until(ExpectedConditions.titleContains("windows"));
		return this;
	}

	public void HandleNewTabWindows() throws CustomException, InterruptedException {

		//driver.findElement(By.xpath("(//button[contains(text(),'click')])[1]")).click();
		click(By.xpath("(//button[contains(text(),'click')])[1]"),WaitStrategy.PRESENCE,"Click Button");
		//findElement(driver,By.xpath("(//button[contains(text(),'click')])[1]"),10,"click");
		browserUtils.SwitchToNewTab();
		Assert.assertTrue(driver.getTitle().contains("Selenium"));
		ExtentLogger.pass("Switched to newly opened tab",true);
		browserUtils.switchToChildWindow(driver,0);
		BrowserUtils.closeMultipleTabsExceptCurrentTabNew();
	}

	public void HandleNewWindow() throws CustomException, InterruptedException {

		//driver.findElement(By.xpath("(//button[contains(text(),'click')])[1]")).click();

		click(OpenNewSeperateWindows_btn,WaitStrategy.PRESENCE,"Open New Separate eWindows button");
		waitForGivenTime(2);
		click(By.xpath("(//button[contains(text(),'click')])[2]"),WaitStrategy.PRESENCE,"Click Button");
		//findElement(driver,By.xpath("(//button[contains(text(),'click')])[1]"),10,"click");
		browserUtils.switchToWindow("Selenium");
		Assert.assertTrue(driver.getTitle().contains("Selenium"));
		ExtentLogger.pass("Switched to newly opened window",true);
		browserUtils.switchToChildWindow(driver,0);
		BrowserUtils.closeMultipleTabsExceptCurrentTabNew();
	}
	public void HandleMultipleNewWindows() throws CustomException, InterruptedException {

		//driver.findElement(By.xpath("(//button[contains(text(),'click')])[1]")).click();

		click(OpenSeperateMultipleWindows_btn,WaitStrategy.PRESENCE,"Open Separate Multiple Windows button");
		waitForGivenTime(2);
		click(By.xpath("(//button[contains(text(),'click')])[3]"),WaitStrategy.PRESENCE,"Click Button");
		//findElement(driver,By.xpath("(//button[contains(text(),'click')])[1]"),10,"click");
		browserUtils.switchToChildWindow(driver,1);
		Assert.assertTrue(driver.getTitle().contains("Index"));
		ExtentLogger.pass("Switched to newly opened tab Index",true);
		browserUtils.switchToChildWindow(driver,2);
		Assert.assertTrue(driver.getTitle().contains("Selenium"));
		ExtentLogger.pass("Switched to newly opened tab Selenium",true);
		browserUtils.switchToChildWindow(driver,0);
		BrowserUtils.closeMultipleTabsExceptCurrentTabNew();
	}







}
