package com.geico.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.geico.constants.Attribute;
import com.geico.reports.Loggers;
import com.google.common.io.Files;

public class CommonActions {

	public static void input(WebElement element, String inputValue) {
		try {
			element.sendKeys(inputValue);
			Loggers.log(element + "---> Input value: " + inputValue);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.log(element + " ---> Not Found \n" + e.getMessage());
			Assert.fail();
		}
	}

	public void selectDropdown(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			Loggers.log(value + " : This value has been passed into this element ---> " + element);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.log(element + " : This element Not Found");
			Assert.fail();
		}
	}

	public static void hoverOverOnly(WebDriver driver, WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			Loggers.log("Hovering on ---> " + element);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.log(element + " ---> Not Found \n" + e.getMessage());
			Assert.fail();
		}
	}

	public static void hoverOverTo(WebDriver driver, WebElement src_element, WebElement target_element) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(src_element).click(target_element).build().perform();
			Loggers.log("Hovering on ---> " + src_element);
			Loggers.log("Clicking on ---> " + target_element);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.log(src_element + " || " + target_element + " ---> Not Found \n" + e.getMessage());
			Assert.fail();
		}
	}

	public static void click(WebElement element) {
		try {
			element.click();
			Loggers.log(element + "---> Clicked ");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.log(element + " ---> Not Found \n" + e.getMessage());
			Assert.fail();
		}
	}

	public static void verifyElementText(WebElement element, String expected) {
		String actual = element.getText();
		Loggers.log(element + " ---> Actula text : " + actual + "Expected text :" + expected);
		Assert.assertEquals(actual, expected);
	}

	public static void verifyText(String text1, String text2) {
		Loggers.log(text1 + " ---> Comparing with : ---> " + text2);
		Assert.assertEquals(text1, text2);
	}

	public static void verifyInnerHTML(WebElement element, String expected) {
		String actual = element.getAttribute("innerHTML");
		Loggers.log(element + " ---> Actula text : " + actual + ". Expected text : " + expected);
		Assert.assertEquals(actual, expected);
	}

	public static void clear(WebElement element) {
		try {
			element.clear();
			Loggers.log(element + " ---> cleared");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Loggers.log(element + " ---> Not Found \n" + e.getMessage());
			Assert.fail();
		}
	}

	public static void verifyTitle(WebDriver driver, String expectedTitle) {
		Loggers.log("Actual Title is : " + driver.getTitle() + "---> And Expected Title is :" + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}

	public static void verifyAttribute(WebElement element, String expected, Attribute attribute) {
		String actual = element.getAttribute(attribute.toString());	
		Loggers.log(element + " ---> Actual text : " + actual + ". Expected text : " + expected);
		Assert.assertEquals(actual, expected);
	}

	public static String getSreenShot(String testName, WebDriver driver) {
		TakesScreenshot ss = (TakesScreenshot) driver;
		String path = System.getProperty("user.dir") + "/test-output/screenShots";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy_hh.mm.ss");
		String formattedDate = dateFormat.format(date);

		File targetFile = new File(path + "/error_" + testName + "_" + formattedDate + ".png");
		try {
			File srcFile = ss.getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, targetFile);
			Loggers.log("Screenshot has been successfully capture at: \n" + targetFile.getAbsolutePath());
		} catch (WebDriverException | IOException e) {
			e.printStackTrace();
			Loggers.log("Screenshot cannot capture");
		}
		return targetFile.getAbsolutePath();
	}

}
