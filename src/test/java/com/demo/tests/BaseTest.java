package com.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import com.demo.utilities.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


public class BaseTest {

    ExtentReports extent;
    ExtentTest test;
    protected WebDriver driver;

    @AfterMethod
    public void tearDownScenario(){

        if (driver != null) {
            driver.quit();
        }
    }


    @BeforeMethod
    public  void before(){

        driver = Driver.getDriver();
        extent = new ExtentReports();


    }

}
