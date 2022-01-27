package org.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
static WebDriver driver;
	
	//Browser
	public static void browserLaunch(String value) {
	if(value.equals("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions co =  new ChromeOptions();
		co.addArguments("--disable-notifications");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();

	
	}else if(value.equals("firefox")) {
	
		WebDriverManager.firefoxdriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-notifications");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

		
	}else if(value.equals("edge")) {
		WebDriverManager.edgedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-notification");
		driver = new EdgeDriver();
		driver.manage().window().maximize();

	}else {
		System.out.println("Invalid Browser");
	}
	
	}
	
	//Fluentwait locators
	public static void fillTextBoxByFW(String locName,String loc,String value) {

		FluentWait<WebDriver> FW = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);

		if (locName.equalsIgnoreCase("id")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc))).sendKeys(value);
		}else if(locName.equalsIgnoreCase("name")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc))).sendKeys(value);
		}else if(locName.equalsIgnoreCase("class")) {
		FW.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).sendKeys(value);
		}else if(locName.equalsIgnoreCase("xpath")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).sendKeys(value);
		}
		else if (locName.equalsIgnoreCase("className")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.className(loc))).sendKeys(value);
		}
		else {
			System.out.println("Invalid Locators");
		}
		
	}

	public static WebElement locators(String value , String value1) {
		WebElement findElement=null;
		if (value.equalsIgnoreCase("Id")) {
			
	 findElement = driver.findElement(By.id(value1));
		return findElement;
			
		}else if (value.equalsIgnoreCase("Class")) {
			 findElement = driver.findElement(By.className(value1));
			return findElement;
			
		}else if (value.equalsIgnoreCase("Name")) {
			 findElement = driver.findElement(By.name(value1));
			return findElement;
			
		}else if (value.equalsIgnoreCase("xpath")) {
			 findElement = driver.findElement(By.xpath(value1));
			return findElement;
			
		}else {
			System.out.println("Invalid locators");
		}
		return findElement;
		
		
	
	}
	
	//Url
	public static void url(String url) {
		
		driver.get(url);
	}
	
	//maximize
	public static void max() {
		driver.manage().window().maximize();
	}
	
	
	//Implicity wait
	public static void pageLoadWait() {
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}
	
	
	//send keys
	public static void enterText(WebElement findElement , String Value) {
		findElement.sendKeys(Value);
	}
	
	//click
	public static WebElement click(WebElement a) {
		a.click();
		return a;

	}
	//GetAttribute
	public static String Attribute(WebElement findElement) {
		String text1 = findElement.getAttribute("value");
		return text1;
		
	}  
	//GetAttribute
	public static String AttributeText(WebElement findElement) {
		String text2 = findElement.getText();
		return text2;
		
	}  
	
	
	//scrolldown
	public static void scrolldown(int value) {
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy", value);
	}
	
	//Sreenshot
	public static void screenshot(String picture) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("C:/Users/bernath.v/Pictures/"+picture+"");
		FileUtils.copyDirectory(src, dest);
		
	}
	
	public static String excelRead(String filename, String sheetname, int rownum, int cellnum) throws IOException {

		FileInputStream fileInputStream = new FileInputStream(
				"C:\\Users\\RAJ\\eclipse-workspace\\MavenProjectForFrameExcel\\src\\test\\resources\\Excel\\" + filename
						+ ".xlsx");
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		int cellType = cell.getCellType();
		String value = null;
		if (cellType == 1) {
			value = cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			value = new SimpleDateFormat().format(cell.getDateCellValue());
		} else {
			value = String.valueOf((long) cell.getNumericCellValue());
		}
		return value;
	}
	
	
	
	
	
	
	
	

}
