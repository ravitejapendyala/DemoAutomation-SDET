package org.selenium.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.enums.WaitStrategy;
import org.selenium.utils.CustomException;


public class Register extends BasePage {

	Faker faker;

	public Register(WebDriver driver) {
		super(driver);
		faker = new Faker();

	}

	private final By firstName = By.xpath("//input[@placeholder='First Name']");
	private final By lastName = By.xpath("//input[@placeholder='Last Name']");
	private final By address = By.xpath("//textarea[@ng-model='Adress']");
	private final By languageDropdown = By.xpath("//div[@id='msdd']");
	private final By englishOption = By.xpath("//a[contains(text(),'English')]");
	private final By countryDropdown = By.xpath("//span[@id='select2-country-container']/following-sibling::span");
	private final By IndiaOption = By.xpath("//*[contains(text(),'India')]");
	private final By yearDropdown = By.id("yearbox");
	private final By monthDropdown = By.xpath("//select[@ng-model='monthbox']");
	private final By dayDropdown = By.id("daybox");
	private final By chooseFileButton = By.id("imagesrc");
	private final By email = By.xpath("//input[@type='email']");
	private final By tel = By.xpath("//input[@type='tel']");
	private final By gender_male = By.xpath("//input[@value='Male']");
	private final By firstpassword = By.id("firstpassword");
	private final By secondpassword = By.id("secondpassword");
	private final By submitButton = By.id("submitbtn");

	public Register load() {
		load("Register.html");
		wait.until(ExpectedConditions.titleContains("Register"));
		return this;
	}

	public void EnterFirstName(){
		clearAndSendKeys(firstName, faker.name().firstName().replaceAll("[^a-zA-Z0-9\\s]]",""), WaitStrategy.PRESENCE, "First Name Field");
	}
	public void EnterLastName(){
		clearAndSendKeys(lastName, faker.name().lastName().replaceAll("[^a-zA-Z0-9\\s]]",""), WaitStrategy.PRESENCE, "LastName Name Field");
	}
	public void EnterAddress(){
		clearAndSendKeys(address, faker.address().fullAddress(), WaitStrategy.PRESENCE, "address Field");
	}
	public void SelectLanguage() throws CustomException {
		findElement(driver,languageDropdown,10,"Language Dropdown","click");
		findElement(driver,englishOption,10,"English Option","click");
		click(address,WaitStrategy.PRESENCE,"click");

	}
	public void SelectCountry() throws CustomException {
		findElement(driver,countryDropdown,10,"country Dropdown","click");
		findElement(driver,IndiaOption,10,"India Option","click");
		click(address,WaitStrategy.PRESENCE,"click");

	}
	public void SelectyYear() {
		Select yrdrdn = new Select(driver.findElement(yearDropdown));
		yrdrdn.selectByVisibleText("1990");

	}
	public void SelectyMonth() {
		Select monthdrdn = new Select(driver.findElement(monthDropdown));
		monthdrdn.selectByVisibleText("May");

	}
	public void SelectyDay() {
		Select daydrdn = new Select(driver.findElement(dayDropdown));
		daydrdn.selectByVisibleText("15");

	}
	public void UploadPicture() {
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\AakasaAir.jpg";
		sendKeys(chooseFileButton,path,WaitStrategy.PRESENCE,"Image");
	}
	public void EnterEmail(){
		clearAndSendKeys(email, faker.internet().emailAddress(), WaitStrategy.PRESENCE, "email field");
	}
	public void EnterTelephone(){
		clearAndSendKeys(tel, faker.phoneNumber().cellPhone(), WaitStrategy.PRESENCE, "Telephone Field");
	}
	public void EnterFirstPassword(){
		clearAndSendKeys(firstpassword, "123456", WaitStrategy.PRESENCE, "First Password");
	}
	public void EnterSecondPassword(){
		clearAndSendKeys(secondpassword, "123456", WaitStrategy.PRESENCE, "Second Password");
	}
	public void SelectGender() throws CustomException {
		findElement(driver,gender_male,10,"Male gender","click");
	}
	public void ClickSubmit() throws CustomException {
		findElement(driver,submitButton,10,"Submit button","click");
	}



}
