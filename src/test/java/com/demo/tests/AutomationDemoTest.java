package com.demo.tests;

import com.demo.base.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pages.Register;
import org.selenium.utils.CustomException;
import org.testng.annotations.Test;

public class AutomationDemoTest extends BaseTest {
    @Test
    public void RegistrationTest3() throws CustomException {

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

}
