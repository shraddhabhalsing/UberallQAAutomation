package com.uberall.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.uberall.qa.base.Base;
import com.uberall.qa.page.EditLocationIdentifierPage;
import com.uberall.qa.page.LoginPage;
import com.uberall.qa.page.SearchLocByNamIdAddrPage;
import com.uberall.qa.util.CommonUtil;

public class EditLocationIdTest extends Base {
	EditLocationIdentifierPage EditLocationIdentifierPageObj = new EditLocationIdentifierPage();
	SearchLocByNamIdAddrPage SearchLocByNam_Id_AddrPageObj= new SearchLocByNamIdAddrPage() ;
	LoginPage loginPageObj=new LoginPage();
		
	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog1,exLog2,exLog3,exLog4;

	EditLocationIdTest(){
		super();
	}

	@BeforeTest
	public void basicSetUp()
	{
		DriversetUp();
		pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\com\\uberall\\qa\\reporting\\ExtentReportForEditLocId.html");
		exReport=new ExtentReports();
		exReport.attachReporter(pathHtml);

	}
	
	// Test case to launch URL
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
	
	// Test case to logging in to the Web app
	
	@Test(priority=2,dataProvider="passSheet")
	public void Login(String User, String Pass){
		loginPageObj.initializeWebElement();
		loginPageObj.login(User, Pass);
		String loginPageURL=driver.getCurrentUrl();
		exLog1=exReport.createTest("Logging in to the web app", "Login functionality Automation");
		if (loginPageURL.equals("https://sandbox.uberall.com/en/app/uberall/login"))
		{
			exLog1.log(Status.PASS,"User logged in Successfully ") ;
		}
		else

		{
			exLog1.log(Status.FAIL,"User failed to Login") ;

		}
	}

	//Test case to search locations by name, identifier, or address

	@Test(priority=3)
	@Parameters({"NamLocIdAddr"})
	public void SearchLocByNam_Id_Addr(String searchVal){
		SearchLocByNam_Id_AddrPageObj.initializeWebElement();
		SearchLocByNam_Id_AddrPageObj.searchText(searchVal);
		SearchLocByNam_Id_AddrPageObj.selectLoc();
		Boolean vSearch=SearchLocByNam_Id_AddrPageObj.verifySearch();

		exLog2=exReport.createTest("Searching locations by name, identifier, or address", "Search functionality Automation");
		if (vSearch)
		{
			exLog2.log(Status.PASS,"Search is Successfully ") ;
		}
		else

		{
			exLog2.log(Status.FAIL,"Unable to search") ;

		}
	
	}
	
	//Test case to verify user is able to edit the locations identifiers
	
	@Test(priority=4)
	
	public void EditLocationId()
	{  
		EditLocationIdentifierPageObj.initializeWebElement();
		Boolean valChanged=EditLocationIdentifierPageObj.editLocationIden();
	
		exLog3=exReport.createTest("Editing locations identifiers", "Edit functionality Automation");
		if (valChanged)
		{
			exLog3.log(Status.PASS,"Successfully edited Location Identifier") ;
		}
		else

		{
			exLog3.log(Status.FAIL,"Failed to edit Location Identifier") ;

		}
		
	}
	
		
	
	@AfterTest
	public void closeBrowser() {
		exReport.flush();
		driver.close();
}
	
}
