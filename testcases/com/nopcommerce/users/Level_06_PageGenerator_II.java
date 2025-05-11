package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.UserCustomerInfoPageObject;

import java.util.Random;

public class Level_06_PageGenerator_II extends BaseTest
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

        homePage = PageGenerator.getHomePage(driver);
    }

    @Test
    public void TC_01_Register()
    {
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = registerPage.clickToLogoutLink();
    }

    @Test
    public void TC_02_Login()
    {
        // Navigate to Login Page
        loginPage = homePage.clickToLoginLink();
        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        homePage = loginPage.clickToLoginButton();
    }

    @Test
    public void TC_03_MyAccount()
    {
        customerInfoPage = homePage.clickToMyAccountLinkAtUserSite(driver);
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailAddressTextboxValue(), emailAddress);
    }


    @AfterClass
    public void afterClass()
    {
        closeBrowserDriver();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

}
