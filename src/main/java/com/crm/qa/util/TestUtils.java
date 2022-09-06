package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.crm.qa.base.TestBase;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class TestUtils extends TestBase{
	public static long Page_Load_TimeOut = 20;
	public static long Implicit_Wait = 10;
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\someswar\\Desktop\\CRMDEMOApplication\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\TestData\\FreeCrmContactsTestData.xlsx";
	//public static String LoginTESTDATA_SHEET_PATH ="C:\\Users\\someswar\\Desktop\\CRMDEMOApplication\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\TestData\\crmLoginTestData.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	
	public void SwitchToFrame() {
		driver.switchTo().frame("mainpanel");	
	}
	
	public static Object[][]  getTestData(String sheetName){
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			book= WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		//System.out.println(sheet.getLastRowNum());
		//sheet.getRow(0).getLastCellNum();
		for(int i = 0; i< sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				 data[i][j] =sheet.getRow(i + 1).getCell(j).toString();
				//System.out.println(data[i][j]);
			}
		}
		return data; 
	}
		
}
