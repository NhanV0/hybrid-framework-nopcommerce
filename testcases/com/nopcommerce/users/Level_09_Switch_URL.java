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

public class Level_09_Switch_URL extends BaseTest
{


    @Parameters({"urlUser","urlAdmin", "browser"})
    @BeforeClass
    public void beforeClass(String userUrl, String adminUrl, String browserName) throws InterruptedException
    {
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        driver = getBrowserDriver(this.userUrl, browserName);

        firstName = "Nhan";
        lastName = "Vo";
        companyName = " ";
        emailAddress = "nhan" + generateFakeNumber() + "@skype.com";
        password = "Abc123@";

        userHomePage = PageGenerator.getHomePage(driver);
        // Precondition
        userRegisterPage = userHomePage.clickToRegisterLink();
        userRegisterPage.enterToFirstNameTextbox(firstName);
        userRegisterPage.enterToLastNameTextbox(lastName);
        userRegisterPage.enterToEmailTextbox(emailAddress);
        userRegisterPage.enterToPasswordTextbox(password);
        userRegisterPage.enterToConfirmPasswordTextbox(password);
        userRegisterPage.clickToRegisterButton();
        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
        userCustomerInfoPage = userRegisterPage.clickToMyAccountLinkAtUserSite(driver);
    }

    @Test
    public void TC_01_CreateAnOrder() throws InterruptedException {
        // At End User site - Order 1 product - Order ID (Status is pending)
        // ...

        // Get Order ID

        userHomePage = userCustomerInfoPage.clickToLogoutLinkUserSite(driver);
        // Switch to Admin side - Change Order status to Complete/Paid
        adminLoginPage = userHomePage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToEmailTextbox("nhan@admin.com");
        adminLoginPage.enterToPasswordTextbox("Vanhuunho0123");
        adminDashboardPage = adminLoginPage.clickLoginButton(driver);
        Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));

        // Logout
        adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminSite(driver);

        // Switch to User side - Check Order status
        userHomePage = adminLoginPage.openUserSite(driver, userUrl);

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
    UserHomepageObject userHomePage;
    UserLoginPageObject userLoginPage;
    AdminLoginPageObject adminLoginPage;
    AdminDashboardPageObject adminDashboardPage;
    UserRegisterPageObject userRegisterPage;
    UserCustomerInfoPageObject userCustomerInfoPage;
    UserAddressPageObject addressPage;
    UserOrderPageObject orderPage;
    UserRewardPointPageObjet rewardPointPage;
    String firstName, lastName,emailAddress, companyName, password;
    String userUrl, adminUrl;

}
