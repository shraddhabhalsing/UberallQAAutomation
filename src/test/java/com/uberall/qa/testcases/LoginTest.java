package com.uberall.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.uberall.qa.base.Base;
import com.uberall.qa.page.LoginPage;
import com.uberall.qa.util.CommonUtil;

public class LoginTest extends Base {

	LoginPage loginPageObj=new LoginPage();
	

	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog1,exLog2,exLog3,exLog4;

	LoginTest(){
		super();
	}
		
	@BeforeTest
	public void basicSetUp()
	{
		DriversetUp();
		pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\com\\uberall\\qa\\reporting\\ExtentReportForLogin.html");
		exReport=new ExtentReports();
		exReport.attachReporter(pathHtml);

	}

	// Test case to Launch URL

	@Test(priority=1)
	public void launchURL(){
		String pageTitle=LaunchBrowser();
		exLog=exReport.createTest("Open URL", "Login functionality Automation");
	
		if (pageTitle.equals("Login - Optimize your online presence"))

		{
			exLog.log(Status.PASS,"URL is launched successfully") ;
		}

		else

		{
			exLog.log(Status.FAIL,"Failed to launch URL") ;
		}
	}


	@DataProvider
	public Object[][] passSheet()
	{
		Object[][] val=CommonUtil.readDataFromExcel("InputData");
		return val;
	}


	//Test case to verify user can Logging in to the web app

	@Test(priority=2,dataProvider="passSheet")
	public void Login(String userName, String password){
		loginPageObj.initializeWebElement();
		loginPageObj.login(userName, password);
		WebElement verifyLogo=driver.findElement(By.xpath("//a[@href='/en/app/uberall']"));
		String veLogo=verifyLogo.getAttribute("href");
		exLog1=exReport.createTest("Logging in to the web app", "Login functionality Automation");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		if (veLogo.equals("https://sandbox.uberall.com/en/app/uberall"))
		{
			exLog1.log(Status.PASS,"User logged in Successfully ") ;
		}
		else

		{
			exLog1.log(Status.FAIL,"User failed to Login") ;

		}
	}

	//Close browser

	@AfterTest
	public void closeBrowser() {
		exReport.flush();
		driver.close();



	}
}
