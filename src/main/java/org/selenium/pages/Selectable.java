package org.selenium.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.enums.WaitStrategy;
import org.selenium.reports.ExtentLogger;
import org.selenium.utils.BrowserUtils;
import org.selenium.utils.CustomException;
import org.testng.Assert;

import java.util.List;


public class Selectable extends BasePage {

	Faker faker;
	BrowserUtils browserUtils;

	public Selectable(WebDriver driver) {
		super(driver);
		faker = new Faker();
		browserUtils = new BrowserUtils();

	}

	private final By default_selectable_tabs = By.xpath("//ul[@class='deaultFunc']/li");
	private final By OpenSeperateMultipleWindows_btn = By.xpath("//a[contains(text(),'Open Seperate Multiple Windows')]");



	public Selectable load() {
		load("Selectable.html");
		wait.until(ExpectedConditions.titleContains("Selectable"));
		return this;
	}

	public void selectTabsInDefault() throws CustomException, InterruptedException {
		List<WebElement> tabs = findElements(driver,default_selectable_tabs,10);
		for (WebElement ele: tabs) {
			ele.click();
			ExtentLogger.pass("Clicked on to "+ele.findElement(By.tagName("b")).getText()+"",true);
		}
	}









}
