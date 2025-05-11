package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.sideBar.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.UserHomepageObject;
import pageObjects.nopCommerce.UserLoginPageObject;
import pageObjects.nopCommerce.UserRegisterPageObject;

import java.util.Random;

public class Level_03_PageObject extends  BasePage
{
    WebDriver driver;
    UserHomepageObject homePage;
    UserLoginPageObject loginPage;
    UserRegisterPageObject registerPage;
    UserCustomerInfoPageObject customerInfoPage;
    String firstName, lastName,emailAddress, companyName, password;

    @BeforeClass
    public void beforeClass() throws InterruptedException
    {

        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        firstName = "Nhan";
        lastName = "Vo";
        companyName = " ";
        emailAddress = "nhan" + generateFakeNumber() + "@skype.com";
        password = "Abc123@";
        openPageURL(driver,"http://localhost");
        homePage = new UserHomepageObject(driver);
    }

    @Test
    public void TC_01_Register()
    {
        homePage.clickToRegisterLink();
        registerPage = new UserRegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickToLogoutLink();
        // Back to Home page
        homePage = new UserHomepageObject(driver);

    }

    @Test
    public void TC_02_Login()
    {
        homePage.clickToLoginLink();
        // Navigate to Login Page
        loginPage = new UserLoginPageObject(driver);
        loginPage.loginToSystem(emailAddress, password);
        // Back to Home Page
        homePage = new UserHomepageObject(driver);

    }

    @Test
    public void TC_03_MyAccount()
    {
        homePage.clickToMyAccountLinkAtUserSite(driver);

        // Navigate to Customer Info Page
        customerInfoPage = new UserCustomerInfoPageObject(driver);
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailAddressTextboxValue(), emailAddress);
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
