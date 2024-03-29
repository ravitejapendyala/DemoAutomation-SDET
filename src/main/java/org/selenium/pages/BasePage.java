

package org.selenium.pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.constants.FrameworkConstants;
import org.selenium.driver.DriverManager;
import org.selenium.enums.WaitStrategy;
import org.selenium.factories.ExplicitWaitFactory;
import org.selenium.reports.ExtentLogger;
import org.selenium.reports.ExtentManager;
import org.selenium.utils.ConfigLoader;
import org.selenium.utils.CustomException;
import org.selenium.utils.ScreenshotUtils;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.selenium.constants.FrameworkConstants.ICON_Navigate_Right;
import static org.selenium.constants.FrameworkConstants.WAIT;

public class BasePage {

	/* Class level -> Not Thread safe */
	/*
	 * No need to use ThreadLocal, because when we are creating the Object of Page,
	 * those objects are local to test methods.
	 */
	protected WebDriver driver;
	protected WebDriverWait wait;

	/*
	 * Many waits can also be used in case you want to different time wait for
	 * different web elements
	 */
//	protected WebDriverWait waitLong;
//	protected WebDriverWait waitShort;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
//		waitLong = new WebDriverWait(driver, 25);
//		waitShort = new WebDriverWait(driver, 5);

	}

	public void load(String endPoint) {
		// driver.get("https://askomdch.com/" + endPoint);
		driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
		ExtentLogger.info(ICON_Navigate_Right + "  Navigating to : <b>" + ConfigLoader.getInstance().getBaseUrl()
				+ endPoint + "</b>");
	}

	public void waitForOverlaysToDisappear(By overlay) {
		List<WebElement> overlays = driver.findElements(overlay);
		System.out.println("OVERLAY SIZE" + overlays.size());
		if (overlays.size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
			System.out.println("OVERLAY INVISIBLE NOW");
		} else {
			System.out.println("OVERLAY NOT FOUND");
		}
	}

	protected void click(By by, WaitStrategy waitStrategy, String elementName) {
		// DriverManager.getDriver().findElement(by).click();
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).click();
		ExtentLogger.pass("<b>" + elementName + "</b> is clicked", true);
		// log(PASS,elementName+" is clicked");
	}

	protected void sendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).sendKeys(value);
		ExtentLogger.pass("<b>" + value + "</b> is entered successfully in <b>" + elementName + "</b>", true);
		// log(PASS,value +" is entered successfully in "+elementName);
	}

	protected void clear(By by, WaitStrategy waitStrategy, String elementName) {
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).clear();
		ExtentLogger.info("Clearing the field  <b>" + elementName + "</b>");
		// log(PASS,value +" is entered successfully in "+elementName);
	}

	protected void clearAndSendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		element.clear();
		element.sendKeys(value);
		ExtentLogger.pass("<b>" + value + "</b> is entered successfully in <b>" + elementName + "</b>", true);
		// log(PASS,value +" is entered successfully in "+elementName);
	}
	protected void SendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		element.sendKeys(value);
		ExtentLogger.pass("<b>" + value + "</b> is entered successfully in <b>" + elementName + "</b>", true);
		// log(PASS,value +" is entered successfully in "+elementName);
	}

	/*
	 * protected String getElementName(By by) { return
	 * DriverManager.getDriver().findElement(by).getText(); }
	 */
	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	/*
	 * public WebElement getElement(By element) { return
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)); }
	 */

	protected void captureScreenshot() {
		ExtentManager.getExtentTest().info("Capturing Screenshot",
				MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
	}

	protected void waitForSomeTime() {
		Uninterruptibles.sleepUninterruptibly(WAIT, TimeUnit.SECONDS);
	}

	protected void waitForGivenTime(long time) {
		Uninterruptibles.sleepUninterruptibly(time, TimeUnit.SECONDS);
	}

	public static WebElement findElement(final WebDriver driver, final By locator, final int timeoutSeconds,String elementName, String click) throws CustomException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
				withTimeout(Duration.ofSeconds(timeoutSeconds / 5)).withMessage("Not able to locate element WEB_element " + locator).
				pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		WebElement element = null;
		for (int retries = 5; retries > 0; retries--) {
			try {
				if (click.equals("click")) {
					element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
					highLightElement(driver, element);
					element.click();
					ExtentLogger.pass("<b>" + elementName + "</b> is clicked", true);
				} else {
					element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
					highLightElement(driver, element);
				}
				break;
			} catch (Exception e) {
				throw new CustomException("Elements " + locator + " is not visible");
			}
		}


		return element;

	}


	//Method Overloading
	public static WebElement findElement(final WebDriver driver, final By locator, final int timeoutSeconds,String elementName) throws CustomException {

		return findElement(driver, locator, timeoutSeconds, "find");
	}

	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String style = element.getAttribute("style");

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, style);

	}

	public static List<WebElement> findElements(WebDriver driver, By locator, final int timeoutSeconds) throws CustomException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds)).withMessage("Not able to locate element " + locator).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				return driver.findElement(locator);
			}

		});
		if (element.isDisplayed()) {
			List<WebElement> elementsList = (List<WebElement>) driver.findElements(locator);
			return elementsList;
		}
		throw new CustomException("Elements " + locator + " is not visible");

	}


}
