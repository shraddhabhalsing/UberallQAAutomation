package com.uberall.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

    //Below Parent class is inherited by other child classes

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream file;
	public static String verifyTitle;
	
	// Fetch values from Config Properties file

	public Base()
	{
		prop=new Properties();
		try {	
			file=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\uberall\\qa\\config\\Config.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			prop.load(file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	 }

	//Initialize ChromeDriver and launch url
	
	public void DriversetUp(){
	   	
		//System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverPath"));
	   WebDriverManager.chromedriver().setup();
		
	}
	
	
	public String LaunchBrowser(){
	   	try{
		driver = new ChromeDriver();
		driver.navigate().to(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().deleteAllCookies();
     	verifyTitle= driver.getTitle();
		}catch(Exception e)
		{
			System.out.println(e);
		}
			return verifyTitle;
		}
}
