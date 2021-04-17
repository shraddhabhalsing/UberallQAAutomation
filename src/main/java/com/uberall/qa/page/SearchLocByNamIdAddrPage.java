package com.uberall.qa.page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uberall.qa.base.Base;

public class SearchLocByNamIdAddrPage extends Base {
	// Find Webelements

	@FindBy(xpath="//span[contains(text(),'Locations')]")
	WebElement LocTab;

	@FindBy(xpath=("//*[@placeholder='Search by name, identifier, address or by labels using #label_name']"))
	WebElement searchBox;

	@FindBy(css="img[class='ubui_buttonLeftIcon___1z-mO']")
	WebElement searchImg;

	@FindBy(xpath="//div[@id='place-for-contract-status-select-dropdown']//b[@class='contract-filter-caret']")
	WebElement LocStatDrpdn;

	@FindBy(xpath="//ul[@class='contract-filter-status-list']//li[@class='contract-filter-status contract-filter-active']//span")
	WebElement selectActivFrmlst;

	@FindBy(xpath="//div[@class='contract-filter-apply']//child::span")
	WebElement clickApplyBtn;




	//Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
	}


	//Search Location by name , address  or Location Identifier


	public void searchText(String searchVal){	
		try
		{

			LocTab.click();
			if (searchBox.isDisplayed()&& searchBox.isEnabled())
			{

				WebDriverWait f=new WebDriverWait(driver, 60);

				f.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Search by name, identifier, address or by labels using #label_name']")));

				searchBox.sendKeys(searchVal);

				/*
				try {
					Thread.sleep(2000);

				} catch (InterruptedException e) {
					System.out.println(e);
				}*/
			}

		}catch(StaleElementReferenceException e2){      
			WebDriverWait wait1=new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Search by name, identifier, address or by labels using #label_name']")));
			searchBox.sendKeys(searchVal);}

		try
		{		
			WebDriverWait wait2=new WebDriverWait(driver, 80);
			wait2.until(ExpectedConditions.elementToBeClickable(searchImg));
			searchImg.click();

		}catch(StaleElementReferenceException e3)
		{ WebDriverWait wait3=new WebDriverWait(driver, 80);
		wait3.until(ExpectedConditions.elementToBeClickable(searchImg));
		searchImg.click();}
	}

	// Select Location status which are Active

	public void selectLoc(){	
		try
		{LocStatDrpdn.click();}
		catch(StaleElementReferenceException e)
		{LocStatDrpdn.click();}

		try
		{   WebDriverWait f=new WebDriverWait(driver, 40);
		f.until(ExpectedConditions.elementToBeClickable(selectActivFrmlst));
		selectActivFrmlst.click();}catch(StaleElementReferenceException e)
		{WebDriverWait f=new WebDriverWait(driver, 40);
		f.until(ExpectedConditions.elementToBeClickable(selectActivFrmlst));
		selectActivFrmlst.click();}

		try
		{   WebDriverWait f=new WebDriverWait(driver, 40);
		f.until(ExpectedConditions.elementToBeClickable(clickApplyBtn));
		clickApplyBtn.click();
		}catch(StaleElementReferenceException e){
			WebDriverWait f=new WebDriverWait(driver, 40);
			f.until(ExpectedConditions.elementToBeClickable(clickApplyBtn));
			clickApplyBtn.click();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		}
		
	}

	//Verify after applying filter with name,address or location identifier with Active status, verify entries are present in the table
	
	public boolean verifySearch()
	{
		Boolean val=false;
		List<WebElement>ls=driver.findElements(By.xpath("//table[@class='location-list-table location-list']/tbody//tr"));
		if (ls.size()>0)
			val=true;
		else
			val=false;
		return val;
	}
}
