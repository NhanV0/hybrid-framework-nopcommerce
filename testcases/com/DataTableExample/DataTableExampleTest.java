package com.DataTableExample;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.DataTableExample.HomePageObject;
import java.util.List;
import java.util.Random;

public class DataTableExampleTest extends BaseTest
{


    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName)
    {
        driver = getBrowserDriver(urlValue, browserName);
        homePage = PageGenerator.getPageInstance(HomePageObject.class, driver);
    }

    @Test
    public void TC_01_VerifyTableSearch() throws InterruptedException
    {
        homePage.enterTextToTableSearch("london");
        List<String> allOffices = homePage.getOfficeColumnValues();
        for(String value: allOffices)
        {
            Assert.assertEquals(value.toLowerCase(), "london", "Mismatch found in Office column value: " + value);
        }
        Assert.assertEquals(allOffices.size(), homePage.getTotalResultCount());

    }


    @AfterClass
    public void afterClass()
    {

        closeBrowserDriver();
    }

    public int generateFakeNumber()
    {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    private WebDriver driver;
    private HomePageObject homePage;
}

