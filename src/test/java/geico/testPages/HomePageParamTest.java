package geico.testPages;

import java.time.Duration;

import static com.geico.constants.IParam.*;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.geico.base.BaseClass;

public class HomePageParamTest extends BaseClass {
	

	@Test(enabled = true)
@Parameters({TITLE, SUB_TITLE, SEARCH_FIELD})
	public void checkOut(String title, String subTitle, String searchField) {
		
		homePage.titleText(title);
		homePage.subTitleTest(subTitle);
		homePage.typeInSearchField(searchField);
		
}
}