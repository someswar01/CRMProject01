package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtils;
import com.crm.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("deprecation")
public class TestBase {
	    public static WebDriver driver;
	    public static Properties prop;
	    public static WebEventListener eventListner; 
	    public static EventFiringWebDriver e_driver; 
	    
		public TestBase(){
			prop=new Properties();
			try {
				FileInputStream ip = new FileInputStream("C:\\Users\\someswar\\Desktop\\CRMDEMOApplication\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.Properties");
				try {
					prop.load(ip);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
			
				

			public static void initialization() {
				String browserName = prop.getProperty("browser");
				
				if (browserName.equals("chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				}else {
					System.out.println("This program is design Based on chrome Browser Only ");
					
				}
				
				e_driver = new EventFiringWebDriver(driver);
				eventListner = new WebEventListener();
				e_driver.register(eventListner);
						driver=e_driver;
					
					driver.manage().window().maximize();
					driver.manage().deleteAllCookies();
					driver.manage().timeouts().pageLoadTimeout(TestUtils.Page_Load_TimeOut,TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(TestUtils.Implicit_Wait, TimeUnit.SECONDS);
				
					driver.get(prop.getProperty("url"));
					
					
			}
		
		}
