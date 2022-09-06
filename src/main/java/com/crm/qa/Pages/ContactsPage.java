package com.crm.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(xpath="//*[@id=\"vContactsForm\"]/table/tbody/tr[6]/td[1]/input")
	WebElement clickOnSomeshCheckox;
	
	@FindBy(id="first_name")
	WebElement frtname;
	
	@FindBy(id="surname")
	WebElement lstname;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//*[@id=\"contactForm\"]/table/tbody/tr[1]/td/input[2]")
	WebElement saveBtn;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectOneOfContacts() {
		clickOnSomeshCheckox.click();
	}
	
	public void createNewContact(String title,String fstname,String ltname,String comp) {
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		frtname.sendKeys(fstname);
		lstname.sendKeys(ltname);
		company.sendKeys(comp);
		saveBtn.click();
	}

	

}
