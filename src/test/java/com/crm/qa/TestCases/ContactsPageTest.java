package com.crm.qa.TestCases;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.Pages.ContactsPage;
import com.crm.qa.Pages.HomePage;
import com.crm.qa.Pages.LoginPage;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtils;

public class ContactsPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtils testUtils;
	ContactsPage contactsPage;
	
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtils = new TestUtils();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtils.SwitchToFrame();
		contactsPage=homePage.clickOnContactsLink();
		
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel()," contactsLabel is missing on contacts page");
	}
	
	@Test(priority=2)
	public void selectingOneOfContact() {
		contactsPage.selectOneOfContacts();
	}
	
//	@DataProvider
//	public Object[][] getCRMTestData(String sheetName) {
//		Object data[][]=TestUtils.getTestData(sheetName);
//		return data;
//	}
	
	@DataProvider
	public String[][] getData() throws Exception{
		File excelFile = new File("C:\\Users\\someswar\\Desktop\\CRMDEMOApplication\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\TestData\\FreeCrmContactsTestData.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("Sheet1");
		int noOfRows = sheet.getPhysicalNumberOfRows();
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[noOfRows-1][noOfColumns];
		for(int i = 0; i<noOfRows-1;i++) {
			for(int j=0;j<noOfColumns;j++) {
				DataFormatter df =new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}
		book.close();
		fis.close();
		return data;
	}
	
	
//	@Test(priority=3,dataProvider="getData")
//	public void validateCreateNewContact(String title,String firstname,String lastname,String company) {
//		homePage.clickOnNewContactLink();
//		contactsPage.createNewContact(title, firstname, lastname, company);
//	}

	
		
	@AfterMethod
	public void tearUp() {
		driver.quit();
	}
	

}
