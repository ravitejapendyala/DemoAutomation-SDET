package com.demo.tests;

import com.demo.base.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pages.Alerts;
import org.selenium.pages.Register;
import org.selenium.reports.ExtentLogger;
import org.selenium.utils.CustomException;
import org.testng.annotations.Test;

public class AutomationDemoTest extends BaseTest {
    //@Test
    public void RegistrationTest() throws CustomException {

        Register register = new Register(getDriver()).load();
        register.EnterFirstName();
        register.EnterLastName();
        register.EnterAddress();
        register.EnterEmail();
        register.SelectCountry();
        register.SelectGender();
        register.SelectLanguage();
        register.SelectyYear();
        register.SelectyMonth();
        register.SelectyDay();
        register.EnterTelephone();
        register.UploadPicture();
        register.EnterFirstPassword();
        register.EnterSecondPassword();
        register.ClickSubmit();
    }
    @Test
    public void AlertsTest() throws CustomException {

        Alerts alerts = new Alerts(getDriver()).load();
        alerts.ClickSimpleAlert();
        Alert confirmAlert = getDriver().switchTo().alert();
        //ExtentLogger.pass("<b> Simple Alert </b> is clicked", false);
        confirmAlert.accept();

        alerts.ClickConfirmAlert();
        confirmAlert = getDriver().switchTo().alert();
        //ExtentLogger.pass("<b> Confirm Alert </b> is clicked", false);
        confirmAlert.dismiss();

        alerts.ClickPromptAlert();
        confirmAlert = getDriver().switchTo().alert();
        confirmAlert.sendKeys("Automation Alert");
        //ExtentLogger.pass("<b> Prompt Alert </b> is clicked", false);
        confirmAlert.accept();
    }

}
