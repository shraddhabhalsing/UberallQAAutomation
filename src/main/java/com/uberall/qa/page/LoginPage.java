package com.uberall.qa.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.uberall.qa.base.Base;

public class LoginPage extends Base {
			
	// Find Webelements

	@FindBy(xpath="//input[@id='email']")
	WebElement emailTextBox;

	@FindBy(xpath="//input[@id='password']")
	WebElement passwordTextBox;

	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;


		
   //Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
      }

	//Method to login into Web app and it returns 
	
	public void  login(String User,String Pass){	
		try
		{
			emailTextBox.sendKeys(User);
			passwordTextBox.sendKeys(Pass);
			loginBtn.click();
			
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	
	}
	
	


}
