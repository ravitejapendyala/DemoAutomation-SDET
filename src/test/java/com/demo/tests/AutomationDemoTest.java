package com.demo.tests;

import com.demo.base.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AutomationDemoTest extends BaseTest {

    //ExtentTest test;
    @Test
    public void RegistrationTest(){
        getDriver().get("https://demo.automationtesting.in/Register.html");
        Faker faker = new Faker();

        // Locate and interact with the elements on the registration form
        WebElement firstName = getDriver().findElement(By.xpath("//input[@placeholder='First Name']"));
        firstName.sendKeys("John");
        //logger.info("Entered Username");
        WebElement lastName = getDriver().findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastName.sendKeys("Doe");
        //logger.info("Entered Password");

        WebElement address = getDriver().findElement(By.xpath("//textarea[@ng-model='Adress']"));
        address.sendKeys("123 Street, City, Country");

        // Select Language as English
// Locate and interact with the elements on the registration form
        WebElement languageDropdown = getDriver().findElement(By.xpath("//div[@id='msdd']"));

        // Click on the language dropdown to open the options
        languageDropdown.click();

        // Locate and click on the desired language option (e.g., English)
        WebElement englishOption = getDriver().findElement(By.xpath("//a[contains(text(),'English')]"));
        englishOption.click();
        address.click();

        // Select Country
        WebElement countryDropdown = getDriver().findElement(By.xpath("//span[@id='select2-country-container']/following-sibling::span"));
        //Select countryDropdown = new Select(getDriver().findElement(By.id("select2-country-container")));
        countryDropdown.click();
        WebElement IndiaOption = getDriver().findElement(By.xpath("//*[contains(text(),'India')]"));
        IndiaOption.click();
        address.click();
        // Select Date of Birth
        Select yearDropdown = new Select(getDriver().findElement(By.id("yearbox")));
        yearDropdown.selectByVisibleText("1990");

        Select monthDropdown = new Select(getDriver().findElement(By.xpath("//select[@ng-model='monthbox']")));
        monthDropdown.selectByVisibleText("May");

        Select dayDropdown = new Select(getDriver().findElement(By.id("daybox")));
        dayDropdown.selectByVisibleText("15");

        // Upload Photo
        WebElement chooseFileButton = getDriver().findElement(By.id("imagesrc"));
        String path = System.getProperty("user.dir")+"\\src\\test\\resources\\AakasaAir.jpg";
        chooseFileButton.sendKeys(path);
        WebElement email = getDriver().findElement(By.xpath("//input[@type='email']"));
        WebElement tel = getDriver().findElement(By.xpath("//input[@type='tel']"));
        WebElement gender = getDriver().findElement(By.xpath("//input[@value='Male']"));
        WebElement firstpassword = getDriver().findElement(By.id("firstpassword"));
        WebElement secondpassword = getDriver().findElement(By.id("secondpassword"));
        gender.click();
        String emailId = faker.internet().emailAddress();
        tel.sendKeys("9977885544");
        firstpassword.sendKeys(emailId);
        secondpassword.sendKeys(emailId);
        // After filling out the form, you can submit it
        WebElement submitButton = getDriver().findElement(By.id("submitbtn"));


        email.sendKeys(emailId);
        submitButton.click();
    }
    @Test
    public void RegistrationTest2(){
        getDriver().get("https://demo.automationtesting.in/Register.html");
        Faker faker = new Faker();

        // Locate and interact with the elements on the registration form
        WebElement firstName = getDriver().findElement(By.xpath("//input[@placeholder='First Name']"));
        firstName.sendKeys("John");
        //logger.info("Entered Username");
        WebElement lastName = getDriver().findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastName.sendKeys("Doe");
        //logger.info("Entered Password");

        WebElement address = getDriver().findElement(By.xpath("//textarea[@ng-model='Adress']"));
        address.sendKeys("123 Street, City, Country");

        // Select Language as English
// Locate and interact with the elements on the registration form
        WebElement languageDropdown = getDriver().findElement(By.xpath("//div[@id='msdd']"));

        // Click on the language dropdown to open the options
        languageDropdown.click();

        // Locate and click on the desired language option (e.g., English)
        WebElement englishOption = getDriver().findElement(By.xpath("//a[contains(text(),'English1')]"));
        englishOption.click();
        address.click();

        // Select Country
        WebElement countryDropdown = getDriver().findElement(By.xpath("//span[@id='select2-country-container']/following-sibling::span"));
        //Select countryDropdown = new Select(getDriver().findElement(By.id("select2-country-container")));
        countryDropdown.click();
        WebElement IndiaOption = getDriver().findElement(By.xpath("//*[contains(text(),'India')]"));
        IndiaOption.click();
        address.click();
        // Select Date of Birth
        Select yearDropdown = new Select(getDriver().findElement(By.id("yearbox")));
        yearDropdown.selectByVisibleText("1990");

        Select monthDropdown = new Select(getDriver().findElement(By.xpath("//select[@ng-model='monthbox']")));
        monthDropdown.selectByVisibleText("May");

        Select dayDropdown = new Select(getDriver().findElement(By.id("daybox")));
        dayDropdown.selectByVisibleText("15");

        // Upload Photo
        WebElement chooseFileButton = getDriver().findElement(By.id("imagesrc"));
        String path = System.getProperty("user.dir")+"\\src\\test\\resources\\AakasaAir.jpg";
        chooseFileButton.sendKeys(path);
        WebElement email = getDriver().findElement(By.xpath("//input[@type='email']"));
        WebElement tel = getDriver().findElement(By.xpath("//input[@type='tel']"));
        WebElement gender = getDriver().findElement(By.xpath("//input[@value='Male']"));
        WebElement firstpassword = getDriver().findElement(By.id("firstpassword"));
        WebElement secondpassword = getDriver().findElement(By.id("secondpassword"));
        gender.click();
        String emailId = faker.internet().emailAddress();
        tel.sendKeys("9977885544");
        firstpassword.sendKeys(emailId);
        secondpassword.sendKeys(emailId);
        // After filling out the form, you can submit it
        WebElement submitButton = getDriver().findElement(By.id("submitbtn"));


        email.sendKeys(emailId);
        submitButton.click();
    }

}
