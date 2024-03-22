

package org.selenium.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.enums.WaitStrategy;
import org.selenium.utils.CustomException;


public class Alerts extends BasePage {

	Faker faker;

	public Alerts(WebDriver driver) {
		super(driver);
		faker = new Faker();

	}

	private final By SimpleAlert = By.xpath("//button[contains(text(),'click the button to display an  alert box')]");
	private final By ConfirmAlert = By.xpath("//button[contains(text(),'click the button to display a confirm box')]");
	private final By PromptAlert = By.xpath("//button[contains(text(),'click the button to demonstrate the prompt box')]");
	private final By ConfirmAlert_btn = By.xpath("//a[text()='Alert with OK & Cancel ']");
	private final By PromptAlert_btn = By.xpath("//a[text()='Alert with Textbox ']");


	public Alerts load() {
		load("Alerts.html");
		wait.until(ExpectedConditions.titleContains("Alerts"));
		return this;
	}

	public void ClickSimpleAlert() throws CustomException {

		driver.findElement(SimpleAlert).click();

		waitForGivenTime(2);

	}public void ClickConfirmAlert() throws CustomException {

		click(ConfirmAlert_btn,WaitStrategy.PRESENCE,"Confirm Button");

		waitForGivenTime(2);
		driver.findElement(ConfirmAlert).click();

		waitForGivenTime(2);
	}public void ClickPromptAlert() throws CustomException {

		click(PromptAlert_btn,WaitStrategy.PRESENCE,"Prompt Button");

		waitForGivenTime(2);
		driver.findElement(PromptAlert).click();

		waitForGivenTime(2);
	}






}
