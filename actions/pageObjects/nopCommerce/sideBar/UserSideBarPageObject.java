package pageObjects.nopCommerce.sideBar;

import PageUIs.nopCommerce.sideBar.SideBarPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;

public class UserSideBarPageObject extends BasePage
{
    private WebDriver driver;

    public UserSideBarPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserRewardPointPageObjet openRewardPointPage(WebDriver driver)
    {
        waitForElementClickable(driver, SideBarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, SideBarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getPageInstance(UserRewardPointPageObjet.class, driver);
    }

    public UserAddressPageObject openAddressPage(WebDriver driver)
    {
        waitForElementClickable(driver, SideBarPageUI.ADDRESS_LINK);
        clickToElement(driver, SideBarPageUI.ADDRESS_LINK);
        return PageGenerator.getPageInstance(UserAddressPageObject.class, driver);
    }

    public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver)
    {
        waitForElementClickable(driver, SideBarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, SideBarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getPageInstance(UserCustomerInfoPageObject.class, driver);
    }

    public UserOrderPageObject openOrderPage(WebDriver driver)
    {
        waitForElementVisible(driver, SideBarPageUI.ORDER_LINK);
        clickToElement(driver, SideBarPageUI.ORDER_LINK);
        return PageGenerator.getPageInstance(UserOrderPageObject.class, driver);
    }
}
