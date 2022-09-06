package com.crm.qa.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.Pages.ContactsPage;
import com.crm.qa.Pages.DealsPage;
import com.crm.qa.Pages.HomePage;
import com.crm.qa.Pages.LoginPage;
import com.crm.qa.Pages.TaskPage;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtils;

public class HomePageTest extends TestBase {
	public LoginPage loginPage;
	public HomePage homePage;
	public TestUtils testUtil;
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil=new TestUtils();
		loginPage = new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void validateHomePageTitleTest() {
		String htitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(htitle, "CRMPRO","Titles not matched");
	}
	
	@Test(priority=2)
	public void validateUserNameLabel() {
		testUtil.SwitchToFrame();
		homePage.validateUserNameLabel();
	}
	
	@Test(priority=3)
	public ContactsPage ClickOnContactsLink() {
		//testUtils.SwitchToFrame(); either we can mention here or we 
		//we can mention above piece of code in CotactsPagetest (in before method) 
		homePage.clickOnContactsLink();
		return new ContactsPage();
	}
	
	@Test(priority=4)
	public DealsPage ClickOnDealsLink() {
		homePage.clickOnDealsLink();
		return new DealsPage();
	}
	
	@Test(priority=5)
	public TaskPage ClickOnTaskLink() {
		homePage.clickOnTasksLink();
		return new TaskPage();
	}
	
	@AfterTest
	public void tearUp() {
		driver.quit();
	}
	

}
