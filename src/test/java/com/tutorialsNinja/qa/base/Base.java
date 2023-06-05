package com.tutorialsNinja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public  Base()  {
		 
		prop=new Properties();
		File propfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\tutorialsNinja\\qa\\config\\Config.propertyfile");
		
		dataProp =new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialninjaqa\\testdata\\testdata.properties");
		
		try {
		FileInputStream fis2=new FileInputStream(dataPropFile);
		dataProp.load(fis2);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		try {
			
		FileInputStream fis=new FileInputStream(propfile);
		prop.load(fis);
	} catch(Throwable e) {
		e.printStackTrace();
	}
		}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName){
		
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions op=new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			//WebDriver driver=new ChromeDriver(op);
			driver=new ChromeDriver(op);
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.Page_Wait_Time));
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	
}
