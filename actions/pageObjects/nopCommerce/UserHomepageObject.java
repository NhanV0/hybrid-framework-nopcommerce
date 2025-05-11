package pageObjects.nopCommerce;

import PageUIs.nopCommerce.UserHomePageUI;
import PageUIs.nopCommerce.UserLoginPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;

public class UserHomepageObject extends  BasePage
{
    private WebDriver driver;
    // Constructor la ham khoi tao, uu tien chay dau tien
    public UserHomepageObject(WebDriver driver)
    {
        this.driver = driver;
    }

    public UserHomepageObject clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    public UserRegisterPageObject clickToRegisterLink()
    {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);
    }

    public UserLoginPageObject clickToLoginLink()
    {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGenerator.getLoginPage(driver);
    }


}
