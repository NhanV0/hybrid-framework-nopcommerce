package com.jquery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;


import java.util.Random;

public class Level_12_Data_Table extends BaseTest
{


    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName)
    {
       driver = getBrowserDriver(urlValue, browserName);
       homePage = PageGenerator.getPageInstance(HomePageObject.class, driver);
    }

    @Test
    public void TC_01_Paging()
    {
//        // Click page number & verify
//        homePage.OpenPageByNumber("11");
//        Assert.assertTrue(homePage.isPageActiveByNumber("11"));
//        homePage.OpenPageByNumber("12");
//        Assert.assertTrue(homePage.isPageActiveByNumber("12"));
//        homePage.OpenPageByNumber("5");
//        Assert.assertTrue(homePage.isPageActiveByNumber("15"));
//
//        homePage.switchToAllPage();
    }

    @Test
    public void TC_02_Search()
    {
        // Input to search field by column name
//        homePage.enterToColumnByName("Country", "Albania");
//        homePage.sleepInSecond(2);
//        homePage.refreshCurrentPage(driver);

    }


    @Test
    public void TC_03_Action()
    {
        homePage.enterToColumnByName("Country", "Albania");
        homePage.sleepInSecond(2);
        // Click icon delete/edit
        homePage.clickToDeleteIconByCountryName("Albania");
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

    private WebDriver driver;
    private HomePageObject homePage;
}
