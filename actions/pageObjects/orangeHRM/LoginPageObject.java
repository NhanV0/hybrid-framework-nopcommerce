package pageObjects.orangeHRM;

import PageUIs.orangeHRM.LoginPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage
{
    private WebDriver driver;

    public LoginPageObject(WebDriver driver)
    {
        this.driver = driver;
    }


    public void enterToUserNameTextbox(String usernameValue)
    {
        waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.USERNAME_TEXTBOX, usernameValue);

    }

    public void enterToPasswordTextbox(String passwordValue)
    {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);
    }

    public void clickToLoginButton()
    {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
