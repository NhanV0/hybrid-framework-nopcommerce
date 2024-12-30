package com.nopcommerce.users;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_01_Register_DRY
{
    WebDriver driver;
    String emailAddress;

    @BeforeClass
    public void beforeClass() throws InterruptedException
    {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("/Users/nhanvo/Library/Application Support/Microsoft Edge/Default");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);
        driver.get("https://demo.nopcommerce.com/");
        // Execute JavaScript to open a new tab with the protected website
        String originalTab = driver.getWindowHandle();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.open('https://someprotectedwebsite', '_blank');");
        // Wait for 15 seconds (in milliseconds)
        Thread.sleep(15000);
        // Switch to the new tab
        String newTab = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newTab);
        driver.switchTo().window(originalTab);



    }
    @Test
    public void TC_01_Register_Empty_Data() throws InterruptedException
    {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
        // Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email()
    {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("123@456#%*");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");

    }

    @Test
    public void TC_03_Register_Success()
    {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        driver.findElement(By.cssSelector("a.ico-logout")).click();
    }

    @Test
    public void TC_04_Register_Existing_Email()
    {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars()
    {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password()
    {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("654321");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

}
