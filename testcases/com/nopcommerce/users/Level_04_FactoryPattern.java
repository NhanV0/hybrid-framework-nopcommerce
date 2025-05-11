package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.UserCustomerInfoPageObject;

import java.util.Random;

public class Level_04_FactoryPattern extends BaseTest
{
    WebDriver driver;
    UserHomepageObject homePage;
    UserLoginPageObject loginPage;
    UserRegisterPageObject registerPage;
    UserCustomerInfoPageObject customerInfoPage;
    String firstName, lastName,emailAddress, companyName, password;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) throws InterruptedException
    {
        driver = getBrowserDriver(urlValue, browserName);
        firstName = "Nhan";
        lastName = "Vo";
        companyName = " ";
        emailAddress = "nhan" + generateFakeNumber() + "@skype.com";
        password = "Abc123@";
        homePage = (UserHomepageObject) PageManager.getPage(driver,"HomePage");
    }

    @Test
    public void TC_01_Register()
    {
        homePage.clickToRegisterLink();
        registerPage = (UserRegisterPageObject) PageManager.getPage(driver,"RegisterPage");
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickToLogoutLink();
        // Back to Home page
        homePage = (UserHomepageObject) PageManager.getPage(driver,"HomePage");

    }

    @Test
    public void TC_02_Login()
    {
        homePage.clickToLoginLink();
        // Navigate to Login Page
        loginPage = (UserLoginPageObject) PageManager.getPage(driver,"LoginPage");
        loginPage.loginToSystem(emailAddress, password);
        // Back to Home Page
        homePage = (UserHomepageObject) PageManager.getPage(driver,"HomePage");

    }

    @Test
    public void TC_03_MyAccount()
    {
        homePage.clickToMyAccountLinkAtUserSite(driver);

        // Navigate to Customer Info Page
        customerInfoPage = (UserCustomerInfoPageObject) PageManager.getPage(driver,"CustomerInfoPage");
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
