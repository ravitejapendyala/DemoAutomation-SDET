package com.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.demo.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationDemoTest extends BaseTest  {


    @Test
    public void RegistrationTest(){
        Driver.getDriver().get("https://demo.automationtesting.in/Register.html");
        Faker faker = new Faker();

        // Locate and interact with the elements on the registration form
        WebElement firstName = Driver.getDriver().findElement(By.xpath("//input[@placeholder='First Name']"));
        firstName.sendKeys("John");

        WebElement lastName = Driver.getDriver().findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastName.sendKeys("Doe");

        WebElement address = Driver.getDriver().findElement(By.xpath("//textarea[@ng-model='Adress']"));
        address.sendKeys("123 Street, City, Country");

        // Select Language as English
// Locate and interact with the elements on the registration form
        WebElement languageDropdown = Driver.getDriver().findElement(By.xpath("//div[@id='msdd']"));

        // Click on the language dropdown to open the options
        languageDropdown.click();

        // Locate and click on the desired language option (e.g., English)
        WebElement englishOption = Driver.getDriver().findElement(By.xpath("//a[contains(text(),'English')]"));
        englishOption.click();
        address.click();

        // Select Country
        WebElement countryDropdown = Driver.getDriver().findElement(By.xpath("//span[@id='select2-country-container']/following-sibling::span"));
        //Select countryDropdown = new Select(Driver.getDriver().findElement(By.id("select2-country-container")));
        countryDropdown.click();
        WebElement IndiaOption = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'India')]"));
        IndiaOption.click();
        address.click();
        // Select Date of Birth
        Select yearDropdown = new Select(Driver.getDriver().findElement(By.id("yearbox")));
        yearDropdown.selectByVisibleText("1990");

        Select monthDropdown = new Select(Driver.getDriver().findElement(By.xpath("//select[@ng-model='monthbox']")));
        monthDropdown.selectByVisibleText("May");

        Select dayDropdown = new Select(Driver.getDriver().findElement(By.id("daybox")));
        dayDropdown.selectByVisibleText("15");

        // Upload Photo
        WebElement chooseFileButton = Driver.getDriver().findElement(By.id("imagesrc"));
        chooseFileButton.sendKeys("C:\\IntactixQATestUtility\\apache-jmeter-5.5\\bin\\AakasaAir.jpg");
        WebElement email = Driver.getDriver().findElement(By.xpath("//input[@type='email']"));
        WebElement tel = Driver.getDriver().findElement(By.xpath("//input[@type='tel']"));
        WebElement gender = Driver.getDriver().findElement(By.xpath("//input[@value='Male']"));
        WebElement firstpassword = Driver.getDriver().findElement(By.id("firstpassword"));
        WebElement secondpassword = Driver.getDriver().findElement(By.id("secondpassword"));
        gender.click();
        String emailId = faker.internet().emailAddress();
        tel.sendKeys("9977885544");
        firstpassword.sendKeys(emailId);
        secondpassword.sendKeys(emailId);
        // After filling out the form, you can submit it
        WebElement submitButton = Driver.getDriver().findElement(By.id("submitbtn"));


        email.sendKeys(emailId);
        submitButton.click();
    }

}
