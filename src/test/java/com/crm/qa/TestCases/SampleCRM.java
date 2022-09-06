package com.crm.qa.TestCases;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.util.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleCRM {
	public static WebDriver driver;
	String sheetName="contacts";
	Properties prop;
	
	
	@DataProvider(name="contacts")
	public Object[][] getCRMTestData(String sheetName) {
		//Object data[][]=TestUtils.getTestData(sheetName)
		Object data[][]=TestUtils.getTestData(sheetName);
		return data;
	}

	@Test(dataProvider="login")
	public void loginDetails(String title,String firstname,String lastname,String company) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://classic.freecrm.com/index.html");
		driver.manage().window().maximize();
	
		driver.findElement(By.name("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@class='btn btn-small']")).click();
		driver.switchTo().frame("mainpanel");
		driver.findElement(By.xpath("//td[contains(text(),'CRMPRO')]")).isDisplayed();
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"))).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'New Contact')]")).click();
		
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		driver.findElement(By.id("first_name")).sendKeys(firstname);
		driver.findElement(By.id("surname")).sendKeys(lastname);
		driver.findElement(By.name("client_lookup")).sendKeys(company);
		driver.findElement(By.xpath("//*[@id=\"contactForm\"]/table/tbody/tr[1]/td/input[2]")).click();
	}
		@AfterMethod
		public void tearUp() {
		driver.quit();
		}

}
