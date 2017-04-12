package com.BookMyShow;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BookMyShowAutomation {
	
	WebDriver driver;
	@BeforeTest
	public void  testBrowser() {
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://in.bookmyshow.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test(priority=1)
	public void signIn() {
		driver.findElement(By.linkText("SIGN IN")).click();
		driver.findElement(By.xpath(".//*[@id='signinPopup']/div/div[2]/div[1]/aside[1]/div[2]/a[2]/div/div")).click();
		System.out.println("executed");
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;
		System.out.println(parentWindowHandler);
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		
		
		driver.switchTo().window(subWindowHandler);
		System.out.println(subWindowHandler);
		driver.findElement(By.id("Email")).sendKeys("itichausali@gmail.com");
		driver.findElement(By.id("next")).click();
		WebElement password = driver.findElement(By.id("Passwd"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(password)).isDisplayed();
		driver.findElement(By.id("Passwd")).sendKeys("###messup");
		driver.findElement(By.id("signIn")).sendKeys(Keys.ENTER);
	//	driver.switchTo().window(parentWindowHandler);
		System.out.println(subWindowHandler);
		System.out.println(parentWindowHandler);
	
		

	
	}
	@Test(priority=2)
	public void testLocation() {
		WebElement element =	driver.findElement(By.xpath(".//*[@id='inp_RegionSearch_top']"));
		element.sendKeys("Ghaziabad");
		System.out.println("executed again");
		element.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(".//*[@id='navbar']/div[2]/div/ul[1]/li[2]/a")).click();

		
	}

}
