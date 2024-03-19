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


public class Frames extends BasePage {

	Faker faker;
	BrowserUtils browserUtils;

	public Frames(WebDriver driver) {
		super(driver);
		faker = new Faker();
		browserUtils = new BrowserUtils();

	}

	private final By singleframe = By.id("singleframe");
	private final By nestedframe = By.className("iframe-container");
	private final By IframeWithInAFrame_btn = By.xpath("//a[contains(text(),'Iframe with in an Iframe')]");
	//Iframe with in an Iframe



	public Frames load() {
		load("Frames.html");
		wait.until(ExpectedConditions.titleContains("Frames"));
		return this;
	}

	public void EnterDataInsideFrame() throws CustomException, InterruptedException {
		browserUtils.switchToFrame(driver.findElement(singleframe));
		clearAndSendKeys(By.tagName("input"),"Data inside frame",WaitStrategy.PRESENCE,"Text box");
		ExtentLogger.pass("Switched to iframe & entered data",true);
		browserUtils.switchToDefaultContent();
	}
	public void EnterDataInsideNestedFrame() throws CustomException, InterruptedException {
		click(IframeWithInAFrame_btn,WaitStrategy.CLICKABLE,"Frame With In a frame button");
		browserUtils.switchToFrame(driver.findElement(By.xpath("//iframe[contains(@src,'MultipleFrame') and not(@id='singleframe')]")));
		browserUtils.switchToFrame(driver.findElement(By.xpath("//iframe[contains(@src,'SingleFrame') and not(@id='singleframe')]")));
		clearAndSendKeys(By.tagName("input"),"Data inside nested frame",WaitStrategy.PRESENCE,"Text box");
		ExtentLogger.pass("Switched to nested iframe & entered data",true);
	}









}
