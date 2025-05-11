package pageObjects.nopCommerce;

import PageUIs.nopCommerce.UserHomePageUI;
import PageUIs.nopCommerce.UserLoginPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;

public class UserLoginPageObject extends BasePage {
    private WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToSystem(String email, String password) {
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        clickToLoginButton();
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIl_TEXTBOX);
        sendKeysToElement(driver, UserLoginPageUI.EMAIl_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public UserLoginPageObject clickToLoginLink() {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGenerator.getLoginPage(driver);
    }
    public UserHomepageObject clickToLoginButton()
    {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getHomePage(driver);
    }
}
