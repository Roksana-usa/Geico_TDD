package geico.testPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.geico.base.BaseClass;
import com.geico.common.CommonActions;

public class HomePageTest extends BaseClass {
	@Test(enabled = true)
	public void checkOut() throws InterruptedException {
		homePage.titleText("The Insurance Savings You Expect");
		homePage.subTitleTest("See how much you could save! Let's get started by entering your ZIP Code:");
		homePage.typeInSearchField("auto");
		homePage.clickLoginBtn();
		homePage.clickSecondLoginBtn();
		homePage.clickSingInBtn();
		homePage.inputPolicy("1012457854");
		homePage.clickInsertDobBtn("01/01/1985");
		homePage.mailInZipCode("11432");
		homePage.clearZipCode();
		homePage.mailInZipCode("11432");
		homePage.verifyLength("5");
		homePage.clickContinueBtn();
	
		//homePage.hoverActionOurLocations();
	
	}

	@Test(enabled = false)
	public void ArrayTest() {
		// Thread.sleep(4000);
		driver.navigate().to("https://www.walmart.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		homePage.ArrayListTest();
		homePage.IterateArrayList();
	}

}