package pageObjects.nopCommerce.sideBar;

import PageUIs.nopCommerce.BasePageUI;
import PageUIs.nopCommerce.sideBar.CustomerInfoPageUI;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;

public class UserCustomerInfoPageObject extends UserSideBarPageObject
{
    private WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    public String getFirstNameTextboxValue()
    {
        waitForElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue()
    {
        waitForElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailAddressTextboxValue()
    {
        waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }

    public UserCustomerInfoPageObject clickToMyAccountLink()
    {
        waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getCustomerInfoPage(driver);
    }
}
