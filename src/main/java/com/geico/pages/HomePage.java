package com.geico.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.geico.constants.Attribute;
import com.geico.constants.IFile;
import com.geico.reports.Loggers;
import com.geico.utils.ReadFile;

import static com.geico.common.CommonActions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import com.geico.constants.ConfigConstant.*;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "ssp-service-zip")
	WebElement zipCodeSearch;

	@FindBy(xpath = "(//span[@class='icon-boat-ins'])[1]")
	WebElement boatBox;

	@FindBy(xpath = "(//span[text()='Log In'])[1]")
	WebElement loginButton;
	//form[@id='manageForm']//button[@id='manageSubmit'] click loginbtn
	@FindBy(xpath = "//form[@id='manageForm']//button[@id='manageSubmit']")
	WebElement ClickLogInBtn;

	@FindBy(xpath = "//input[@class='zip-code-input']")
	WebElement clickMailInZipCode;

	@FindBy(xpath = "//input[@id='TextInputZipComponent-1']")
	WebElement geicoZipCode;

	@FindBy(xpath = "//a[text()='Sign up for an account']")
	WebElement ClickSignInBtn;

	@FindBy(xpath = "//input[@id='TextInputDateOfBirthComponent-1']")
	WebElement insertDateOfBirthBtn;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement continueBtn;

	@FindBy(xpath = "//input[@id='TextInputComponent-3']")
	WebElement policyInput;

	@FindBy(xpath = "//h1[text()='The Insurance Savings You Expect']")
	WebElement title;

	@FindBy(xpath = "//div[@class='headline-zip']//p[1]")
	WebElement subTitle;

	//@FindBy(xpath = "//a[normalize-space(text()) = 'Our Locations' and @class='hidden-xs dropdown']")
	//WebElement hoverOverOurLocations;


	@FindBy(xpath="//p[text()='The information you entered does not match our system. Please try again.']")
	WebElement verifyErrorMsg;
	
	@FindBy(xpath = "//section[contains(@class,'flex-auto')]")
	List<WebElement> headLineMenu;

	public void clearZipCode() {
		clear(clickMailInZipCode);
	}

	public void typeInSearchField(String input) {
		click(zipCodeSearch);
		input(zipCodeSearch, input);
	}

	public void clickBoatBoxTest() {
		click(boatBox);
	}

	public void clickLoginBtn() {
		click(loginButton);
		
	}

	public void clickSecondLoginBtn() {
		click(ClickLogInBtn);
	}

	public void clickSingInBtn() {
		click(ClickSignInBtn);
	}

	public void clickInsertDobBtn(String dob) {
		click(insertDateOfBirthBtn);
		input(insertDateOfBirthBtn, dob);
	}

	public void mailInZipCode(String zip) {

		input(clickMailInZipCode, zip);
	}

	public void clickContinueBtn() {
		click(continueBtn);
	}

	public void inputPolicy(String policy) {
		input(policyInput, policy);

	}

	public void titleText(String expected) {
		verifyInnerHTML(title, expected);

	}

	public void subTitleTest(String expected) {
		verifyElementText(subTitle, expected);
	}
	
	public void eorrorMsgVerify(String expected) {
		verifyElementText(verifyErrorMsg, expected);
	}

	

	public void hoverActionOurLocations() throws InterruptedException {
		Thread.sleep(5000);
		driver.navigate().to("https://www.mountsinai.org/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//WebElement Our= driver.findElement(By.xpath("(//a[@class='hidden-xs dropdown'])[1]"));
		//Thread.sleep(4000);
		Actions actions = new Actions(driver); // Actions class is instantiated in base class, actions object came form
												// there
		//actions.moveToElement(hoverOverOurLocations).perform();
		

	}

	public void verifyLength(String expected) {
		verifyAttribute(geicoZipCode, expected, Attribute.MAX_LENGTH);

	}

	public void ArrayListTest() {// WebElemnt actual result
		for (WebElement e : headLineMenu) {
			String list = e.getText();
			Loggers.log("The HeadLine Menu is:" + list);
		}
	}

	public void IterateArrayList() {// result from menu.txt
		ReadFile readFile = new ReadFile(IFile.MENU);
		List<String> menuList = new ArrayList<>();
		menuList = readFile.getList();
		for (String actualList : menuList) {
			Loggers.log("The ArrayList of Menu: " + actualList);

		}
	}
}
