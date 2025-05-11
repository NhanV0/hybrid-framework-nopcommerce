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
import pageObjects.nopCommerce.sideBar.UserAddressPageObject;
import pageObjects.nopCommerce.sideBar.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.sideBar.UserOrderPageObject;
import pageObjects.nopCommerce.sideBar.UserRewardPointPageObjet;

import java.util.Random;

public class Level_07_SwitchPage extends BaseTest
{


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

    @Test
    public void  TC_04_SwitchPage()
    {
        // Customer -> Address
        //
        addressPage = customerInfoPage.openAddressPage(driver);
        System.out.println(addressPage.toString());
        // Address -> Reward Point
        //
        rewardPointPage = addressPage.openRewardPointPage(driver);
        System.out.println(rewardPointPage.toString());
        // Reward Point -> Order
        //
        orderPage = rewardPointPage.openOrderPage(driver);
        System.out.println(orderPage.toString());
        // Order -> Customer
        //
        customerInfoPage = orderPage.openCustomerInfoPage(driver);
        System.out.println(customerInfoPage.toString());
        // Customer -> Reward Point
        //
        rewardPointPage = customerInfoPage.openRewardPointPage(driver);
        System.out.println(rewardPointPage.toString());

        rewardPointPage.openOrderPage(driver);


        // Order -> Address
        orderPage.openAddressPage(driver);

        //Address > Order
        addressPage.openOrderPage(driver);

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

    WebDriver driver;
    UserHomepageObject homePage;
    UserLoginPageObject loginPage;
    UserRegisterPageObject registerPage;
    UserCustomerInfoPageObject customerInfoPage;
    UserAddressPageObject addressPage;
    UserOrderPageObject orderPage;
    UserRewardPointPageObjet rewardPointPage;
    String firstName, lastName,emailAddress, companyName, password;

}
