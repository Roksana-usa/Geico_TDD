package com.geico.reports;




import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentManager {
	
	
	
	static ExtentReports extentReports;

	public static ExtentReports initialReports() {
		if (extentReports == null) {
			extentReports = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent_report.html");
			spark.config().setReportName("Automation Test Report");
			spark.config().setDocumentTitle("Report - Automation Test");
			spark.config().setTheme(Theme.DARK);
			extentReports.attachReporter(spark);
			extentReports.setSystemInfo("Tester", System.getProperty("user.name"));
			extentReports.setSystemInfo("OS", System.getProperty("os.name"));
			extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
			extentReports.setSystemInfo("OS Arch", System.getProperty("os.arch"));
			extentReports.setSystemInfo("Environment", "QA");
		}
		return extentReports;
	}

}
