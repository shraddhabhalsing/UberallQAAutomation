package com.uberall.qa.page;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.uberall.qa.base.Base;

public class EditLocationIdentifierPage extends Base {
	// Find Webelements


	@FindBy(xpath="//table[@class='location-list-table location-list']/tbody//")
	WebElement tablSelect;

	@FindBy(xpath="//input[@class='location-form-input' and  @name='identifier']")
	WebElement editLocIdent;

	@FindBy(xpath="//a[@class='btn btn-backend-default btn-profile-header save-button active']//img//following::span")
	WebElement savLocIdent;

	//Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
	}

	//Method to edit location identifier, return boolean value to compare original data is being changed or not

	public Boolean editLocationIden(){	
		
		Boolean checkVal = false;
		List<WebElement>ls=driver.findElements(By.xpath("//table[@class='location-list-table location-list']/tbody//tr"));
		System.out.println(ls.size());
		for (int i=0;i<=ls.size();i++)
		{
			List<WebElement>col=driver.findElements(By.xpath("//table[@class='location-list-table location-list']/tbody//tr["+i+"]//td[2]//div//div//a[contains(text(),'QA Test Store Location')]"));
			List<WebElement>data=driver.findElements(By.xpath("//table[@class='location-list-table location-list']/tbody//tr["+i+"]//td[3]"));
			
			for (int j=0;j<col.size();j++)
			{
				col.get(j).getText().toString();
				String addr=data.get(j).getText().toString();
				col.get(j).click();
				Random rnd=new Random();
				int n = rnd.nextInt(1000);
				String orgVal=editLocIdent.getAttribute("value");
				String changedVal= "Id "+"_"+ n +"_"+addr;
				   
				if (!orgVal.equals(changedVal))
			
					checkVal=true;
					else
					checkVal=false;	
								
				Actions act=new Actions(driver);
				act.moveToElement(editLocIdent).click().doubleClick().sendKeys(editLocIdent, Keys.CLEAR).build().perform();
				editLocIdent.sendKeys(changedVal);
				savLocIdent.click();
			    driver.navigate().back();
			    
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return checkVal;

	}





}
