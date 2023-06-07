package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	
	
	
	public static final int Implicit_WAIT_TIME=25;
	public static final int Page_Wait_Time=20;
	
	public static String generateEmailWithTimeStamp() 
	{
		Date date=new Date();
		
		String timestamp= date.toString().replace(" ","_").replace(":","_");
		return "amotoori"+timestamp+"@gmail.com";
	}
	
	public static  Object[][] getTestDataFromExcel(String sheetName) throws FileNotFoundException {
		
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialninjaqa\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook=null;
		try {
		FileInputStream fisExcel = new FileInputStream(excelFile);
		
		workbook= new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<cols;j++) {
				XSSFCell cell=row.getCell(j);
				CellType cellType=cell.getCellType();
				switch(cellType) {
				
				case STRING:
				data[i][j]=cell.getStringCellValue();
				break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
					case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				}
				
			}
			
		}
		return data;
	}
	
	public static String CapturingScreenShot(WebDriver driver,String testName) {
		
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destnationScreenShotPath=System.getProperty("user.dir")+"\\ScreenShot\\"+testName+".png";
		
		try {
			org.openqa.selenium.io.FileHandler.copy(srcScreenshot,new File(destnationScreenShotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return destnationScreenShotPath;
	}
}
