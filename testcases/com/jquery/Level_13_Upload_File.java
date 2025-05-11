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

public class Level_13_Upload_File extends BaseTest
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
