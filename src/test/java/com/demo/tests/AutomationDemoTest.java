package com.demo.tests;

import com.demo.base.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pages.*;
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
    //@Test
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
    //@Test
    public void WindowsTest() throws CustomException, InterruptedException {

        Windows windows = new Windows(getDriver()).load();
        windows.HandleNewTabWindows();
        windows.HandleNewWindow();
        windows.HandleMultipleNewWindows();

    }
    //@Test
    public void FramesTest() throws CustomException, InterruptedException {

        Frames frames = new Frames(getDriver()).load();
        frames.EnterDataInsideFrame();
        frames.EnterDataInsideNestedFrame();

    }
    //@Test
    public void DatePickerTest() throws CustomException, InterruptedException {

        DatePicker datePicker = new DatePicker(getDriver()).load();
        datePicker.EnterDateForDatePickerDisabled();
        datePicker.EnterDateForDatePickerEnabled();


    }
    @Test
    public void SelectableTest() throws CustomException, InterruptedException {

        Selectable selectable = new Selectable(getDriver()).load();
        selectable.selectTabsInDefault();
        selectable.selectTabsInSerialize();



    }

}
