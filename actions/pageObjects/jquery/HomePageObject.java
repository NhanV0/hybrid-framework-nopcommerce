package pageObjects.jquery;

import PageUIs.jquery.HomePageUI;
import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePageObject extends BasePage
{
    private WebDriver driver;

    public HomePageObject(WebDriver driver)
    {
        this.driver = driver;
    }

    public void OpenPageByNumber(String number)
    {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, number);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, number);
    }

    public boolean isPageActivatedByNumber(String number)
    {
        waitForElementAttributeContains(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, "class", "active", number);
        return getElementAttribute(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, "class", number).endsWith("active");
    }

    public boolean isPageActiveByNumber(String number)
    {
        waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER_ACTIVE, number);
        return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER_ACTIVE, number);
    }

    public void switchToAllPage()
    {
        List<WebElement> allPage = getListWebElement(driver, HomePageUI.PAGINATION_ALL_PAGE);
        for (WebElement page: allPage)
        {
            page.click();
            sleepInSecond(5);
        }
    }

    public void enterToColumnByName(String columnName, String searchKeyWord)
    {
        waitForElementVisible(driver, HomePageUI.COLUMN_TEXTBOX_BY_COLUMN_NAME, columnName);
        sendKeysToElement(driver, HomePageUI.COLUMN_TEXTBOX_BY_COLUMN_NAME, searchKeyWord, columnName);
        sendKeyboardToElement(driver, HomePageUI.COLUMN_TEXTBOX_BY_COLUMN_NAME, Keys.ENTER, columnName);
    }

    public void clickToDeleteIconByCountryName(String countryName)
    {
        waitForElementClickable(driver, HomePageUI.DELETE_ICON_BY_COUNTRY_NAME, countryName);
        clickToElement(driver, HomePageUI.DELETE_ICON_BY_COUNTRY_NAME, countryName);
    }

    public void clickToEditIconByCountryName(String countryName)
    {
        waitForElementClickable(driver, HomePageUI.EDIT_ICON_BY_COUNTRY_NAME, countryName);
        clickToElement(driver, HomePageUI.EDIT_ICON_BY_COUNTRY_NAME, countryName);
    }
}
