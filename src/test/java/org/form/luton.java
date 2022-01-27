package org.form;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class luton extends BaseClass{
	
	WebDriver driver;
	@BeforeClass
	public void Login() {
		browserLaunch("chrome");
		url("https://luton-uat.engage2serve.com/#/login");
	
	}
	
//	@BeforeMethod()
//	public void time() {
//		Date date = new Date();
//		System.out.println(date);
//	}
	
	
	@Test(priority=1)
	public void Up() {
		fillTextBoxByFW("xpath", "//input[contains(@type,'text')]", "luton-e2s@yopmail.com");
		fillTextBoxByFW("xpath", "//input[contains(@type,'password')]", "welcome@123");
		click(locators("xpath", "//button[contains(@type,'submit')]"));
	}
	
	@Test(priority=2)
	public void icon() throws InterruptedException {
		pageLoadWait();
		Thread.sleep(7000);
		click(locators("xpath", "//i[@class='fa fa-thumb-tack']"));
		click(locators("xpath", "//span[contains(text(),'Admissions')]"));
		click(locators("xpath", "//span[contains(text(),'Create Application ')]"));
	}
	@Test(priority=3)
	public void forms() throws InterruptedException, IOException {
		Thread.sleep(5000);
		fillTextBoxByFW("xpath", "//input[contains(@id,'textbox_1623226338848')]", "Lee25Palmer@yopmail.com");
		fillTextBoxByFW("xpath", "//input[contains(@id,'textbox_01100')]", "lee");
		fillTextBoxByFW("xpath", "//input[contains(@id,'textbox_01011')]", "palmer");
		click(locators("xpath", "(//span[contains(@id,'select_placeholder_x')])[1]"));
//		fillTextBoxByFW("xpath", "//*[@aria-activedescendant=\"ui-select-choices-row-1-2\"]", "Male");
		Thread.sleep(3000);
		screenshot("img.png");
		
		
	}
	
	
	
	
	
	
	
	

}
