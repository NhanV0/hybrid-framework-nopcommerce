package pageObjects.nopCommerce;

import PageUIs.nopCommerce.BasePageUI;
import PageUIs.nopCommerce.UserHomePageUI;
import PageUIs.nopCommerce.RegisterPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;

public class UserRegisterPageObject extends BasePage
{
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName)
    {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName)
    {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String email)
    {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(String password)
    {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String confirmPassword)
    {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public void clickToRegisterButton()
    {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);;
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getRegisterSuccessMessage()
    {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }
    public UserRegisterPageObject clickToRegisterLink()
    {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);
    }


    public UserHomepageObject clickToLogoutLink()
    {
        waitForElementClickable(driver, BasePageUI.USER_LOGOUT_LINK);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK);
        return PageGenerator.getHomePage(driver);
    }
}
