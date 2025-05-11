package com.orangehrm.employee;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.LoginPageObject;

public class Level_04_Multiple_Browser extends BaseTest
{

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) throws InterruptedException
    {
        driver = getBrowserDriver(urlValue, browserName);
        username = "Admin";
        password = "admin123";
        loginPage = new LoginPageObject(driver);
    }

    @Test
    public void TC_01_Login()
    {
        loginPage.enterToUserNameTextbox(username);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();
        dashboardPage = new DashboardPageObject(driver);
    }

    @Test
    public void TC_02_New_Employee()
    {

    }

    @Test
    public void TC_03_Personal_Detail()
    {

    }


    @AfterClass
    public void afterClass()
    {
        closeBrowserDriver();
    }

    private String username, password;

}
