package testng;




import org.testng.Assert;
import org.testng.annotations.Test;

import com.geico.reports.Loggers;

public class TestNgAttributesTest {
	@Test(enabled=true,groups={"regression","smoke","auto"},dependsOnMethods ="test3",ignoreMissingDependencies =true )
	public void test1() {
		Loggers.log("This is for test1");
	}
	@Test(enabled=true,groups={"smoke","regression"},dependsOnMethods = "test4",alwaysRun = true)
	public void test2() {
		Loggers.log("This is for test2");	
	}
	@Test(enabled=true,groups="regression",alwaysRun = true)//will not run even alwaysrun attri
	public void test3() {
		Loggers.log("This is for test3");	
	}
	@Test(enabled=true, groups="smoke")//, dependsOnGroups = "regression")//will still not run for regression as originally it is smoke
	public void test4() {
		Loggers.log("This is for test4");	
		Assert.fail();
	}
	}
	
	
	
	
	
	


