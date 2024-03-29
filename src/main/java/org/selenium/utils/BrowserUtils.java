package org.selenium.utils;


import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;


import static org.selenium.driver.DriverManager.getDriver;

public class BrowserUtils {

    public void navigateTo (String navigationUrl){
        getDriver().get(navigationUrl);
    }
    public static void clickByElement (WebElement elementToClick){
        Waits.waitForClickability(elementToClick,30);
        elementToClick.click();
    }

    public void clickByElement(WebDriver driver, WebElement elementToClick) {
        Waits.waitForClickability(driver, elementToClick, 10);
        elementToClick.click();
    }

    public void performActionsClick(WebElement elementToClick) {
        Waits.waitForClickability(elementToClick, 30);
        Actions act = new Actions(getDriver());
        act.moveToElement(elementToClick).click().build().perform();
    }

    public void click(WebElement element) {
        Waits.waitForClickability(element,30);
        element.click();
    }

    public void type(WebElement element, String data) {
        Waits.waitForVisibility(element,30);
        element.clear();
        element.sendKeys(data);
    }

    public void actionsType(WebElement element, String data) {
        Actions act = new Actions(getDriver());
        act.moveToElement(element)
                .sendKeys(data).build().perform();
    }

    public void type(WebDriver driver, WebElement element, String data) {
        Waits.waitForVisibility(driver, element, 30);
        element.clear();
        element.sendKeys(data);
    }

    public String getText(WebElement element) {
        Waits.waitForVisibility(element, 30);

        return element.getText();
    }



    public boolean isElementVisible(WebElement element) {
        try {
            Waits.waitForVisibility(element, 20);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method accepts String expected title
     *
     * @param expectedTitle
     */
    public static void assertTitle(String expectedTitle, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        String actualTitle = getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    /**
     * This method accepts a List<WebElements> and returns List<String>
     *
     * @param webElementList
     */
    public static List<String> getElementsText(List<WebElement> webElementList) {
        //Create placeholder List<String>
        List<String> actualAsString = new ArrayList<>();
        for (WebElement each : webElementList) {
            actualAsString.add(each.getText());
        }
        return actualAsString;
    }

    /*
     * switches to new window by the exact title
     * returns to original window if windows with given title not found
     */
    public static void switchToWindow(String targetTitle) {
        String origin = getDriver().getWindowHandle();
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
            if (getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        getDriver().switchTo().window(origin);
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public void switchToFrame(WebElement element) {
        Waits.waitForVisibility(element, 120);
        getDriver().switchTo().frame(element);
    }
    public void switchToFrame(int index) {
        getDriver().switchTo().frame(index);
    }

    public void switchToDefaultContent() {

        getDriver().switchTo().defaultContent();
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public void waitUntilLoadingDisappears() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@class='block-ui-message ng-binding'][text()='Loading...']")));
    }

    public void waitUntilLoadingDisappears(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@class='block-ui-message ng-binding'][text()='Loading...']")));
    }

    /**
     * Verifies whether the element matching the provided locator is displayed on page
     * fails if the element matching the provided locator is not found or not displayed
     *
     * @param by
     */
    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue(getDriver().findElement(by).isDisplayed(), "Element not visible: " + by);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found: " + by);
        }
    }

    public String getElementText(WebElement element) {
        Waits.waitForVisibility(element, 10);
        return element.getText();
    }

    /**
     * Verifies whether the element is displayed on page
     * fails if the element is not found or not displayed
     *
     * @param element
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Element not visible: " + element);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found: " + element);
        }
    }

    /**
     * Waits for element to not be stale
     *
     * @param element
     */
    public void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    /**
     * Selects a random value from a dropdown list and returns the selected Web Element
     *
     * @param select
     * @return
     */
    public WebElement selectRandomTextFromDropdown(Select select) {
        Random random = new Random();
        List<WebElement> weblist = select.getOptions();
        int optionIndex = 1 + random.nextInt(weblist.size() - 1);
        select.selectByIndex(optionIndex);
        return select.getFirstSelectedOption();
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls down to an element using JavaScript
     *
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public void doubleClick(WebElement element) {
        new Actions(getDriver()).doubleClick(element).build().perform();
    }

    /**
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     *
     * @param element
     * @param attributeName
     * @param attributeValue
     */
    public void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    public static void scrollDownToWindow() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0,500)");
    }

    public static void scrollDownToWindow(int count) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        for (int i = 1; i <= count; i++) {
            Waits.waitFixedTime(2);
            jse.executeScript("window.scrollBy(0,500)");
        }
    }

    public static void scrollUpToWindow(int count) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        for (int i = 1; i <= count; i++) {
            Waits.waitFixedTime(2);
            jse.executeScript("window.scrollBy(0,-500)");
        }
    }

    /**
     * @param element
     * @param check
     */
    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }

    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            System.out.println("something happened in the sleep method");
        }
    }

    public void performMouseHover(WebElement element) {
        Actions act = new Actions(getDriver());
        act.moveToElement(element).build().perform();
    }

    public static String getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public static void waitBeforeExecution(int timeout) {
        Waits.waitForPageToLoad(timeout);
        BrowserUtils.sleep(3);
    }

    public static void assertTitleContains(String expectedTitle, int timeout) {
        Boolean titleContains;
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        String actualTitle = getDriver().getTitle();
        if (actualTitle.contains(expectedTitle)) {
            titleContains = true;
        } else {
            titleContains = false;
        }
        Assert.assertTrue(titleContains);
    }

    public boolean VerifyElementExists(String xpath) {
        //Waits.waitForVisibility(element,10);
        return (getDriver().findElements((By.xpath(xpath))).size() >= 1);
    }

    public void SwitchToNewTab() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }
    public static boolean isElementPresent(WebElement element){
        Waits.waitForPageToLoad(10);
        return element.isDisplayed();
    }

    public static void clickByElement (String xpath){
        WebElement element  = getDriver().findElement(By.xpath(xpath));
        Waits.waitForClickability(element,30);
        element.click();
    }
    public static  void ClickBack(){
        getDriver().navigate().back();
    }


    // New Methods from WebUtilities class
    public static void scrollElementIntoView(final WebDriver driver, WebElement element, boolean top) {
        if (element != null) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String topView = "";
            if (top) {
                topView = "true";
            }
            try {
                jse.executeScript("arguments[0].scrollIntoView(" + topView + ")", element);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * This method locates an element
     *
     * @param driver
     * @param locator
     * @param timeoutSeconds
     * @return
     * @throws CustomException
     */


    public static WebElement findElement(final WebDriver driver, final By locator, final int timeoutSeconds, String click) throws CustomException {

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
    public static WebElement findElement(final WebDriver driver, final By locator, final int timeoutSeconds) throws CustomException {

        return findElement(driver, locator, timeoutSeconds, "find");
    }


    /**
     * @param driver
     * @param locator
     * @param timeoutSeconds
     * @return
     * @throws CustomException
     */

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

    /**
     * @param driver
     * @param locator
     * @return
     */
    public static boolean isElementPresent(final WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            System.out.println("LOG : " + locator.toString() + " PRESENT");
            return true;
        } catch (Exception ex) {
            System.out.println("LOG : " + locator.toString() + " NOT PRESENT");
            //ex.printStackTrace();
            return false;
        }
    }


    public static void switchToChildWindow(WebDriver webDriver, int index) {
        webDriver.switchTo().window(new ArrayList<String>(webDriver.getWindowHandles()).get(index));
    }

    public static void scrollDown(WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,3750)", "");
    }


    public static boolean isElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.ignoring(NoSuchElementException.class);
        wait.pollingEvery(Duration.ofMillis(500));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("LOG : " + locator.toString() + "  VISIBLE");
        } catch (Exception e) {
            System.out.println("LOG : " + locator.toString() + " NOT VISIBLE");
            return false;

        }
        return true;
    }

    public static boolean isElementNotVisible(WebDriver driver, By locator) {
        // WebUtilities.ExplicitWait(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.ignoring(NoSuchElementException.class);
        wait.pollingEvery(Duration.ofMillis(500));

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            System.out.println("LOG : " + locator.toString() + "Element is Not Visible : Expected");
        } catch (Exception e) {
            System.out.println("LOG : " + locator.toString() + " Element is VISIBLE: Not Expected");
            return false;

        }
        return true;
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

    static String parent_tab;
    static WebDriver webDriver;

    public static void switchToChildWindow(WebDriver driver) {
        webDriver = driver;
        parent_tab = webDriver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> s1 = webDriver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String child_tab = i1.next();
            if (!parent_tab.equalsIgnoreCase(child_tab)) {
                webDriver.switchTo().window(child_tab);
            }
        }
    }

    public static List<WebElement> findElements(WebDriver webDriver, By locator) {
        List<WebElement> elements = webDriver.findElements(locator);
        return elements;
    }

    public static void switchToParentTab() {
        getDriver().close();
        parent_tab = getDriver().getWindowHandle();
        getDriver().switchTo().window(parent_tab);
    }

    public static void openNewEmtpyTab(WebDriver driver) {
        webDriver = driver;
        parent_tab = webDriver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public static String getCurrentWindow(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public static void switchToWindow(String window, WebDriver driver) {
        driver.switchTo().window(window);
    }

    public static void quitDriver(WebDriver driver) {
        webDriver.quit();
    }

    public void CloseAddIfExists() {
        if (!getDriver().findElements(By.xpath("//span[contains(text(),'CONTINUE SHOPPING')]")).isEmpty()) {
            try {
                clickWithJS(getDriver().findElement(By.xpath("")));
            } catch (Exception ex) {
                System.out.println("Into Exception clause ... Continue Shopping  add  doesn't exist");
            }

        } else {
            System.out.println("Continue Shopping  add  doesn't exist");
        }

    }

    public static void closeMultipleTabsExceptCurrentTab() throws InterruptedException {
        // Get all open tabs
        Set<String> allTabs = getDriver().getWindowHandles();

        // Get Current tab
        String currentTab = getDriver().getWindowHandle();

        Iterator<String> iterator = allTabs.iterator();

        while(iterator.hasNext()) {
            // Condition to check if the selected tab is not current tab
            String selectedTab = iterator.next();
            if(!selectedTab.equals(currentTab)) {
                // Switch to new tab
                getDriver().switchTo().window(selectedTab);

                // Print title of tabs to be closed
                System.out.println("Closing Tab = "+getDriver().getTitle());

                // Close the selected tab
                getDriver().close();

                // Time delay
                Waits.waitFixedTime(1);
            }
            else{
                getDriver().switchTo().window(selectedTab);
            }
        }
    }
    public static void closeMultipleTabsExceptCurrentTabNew() throws InterruptedException {
        Set<String> windowHandles = getDriver().getWindowHandles();

// Iterate over all window handles except for the parent tab
        for (String handle : windowHandles) {
            if (!handle.equals(getDriver().getWindowHandles().toArray()[0].toString())) {
                // Switch to the child tab
                getDriver().switchTo().window(handle);
                // Close the child tab
                getDriver().close();
            }
        }

// Switch back to the parent tab
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[0].toString());
    }


}