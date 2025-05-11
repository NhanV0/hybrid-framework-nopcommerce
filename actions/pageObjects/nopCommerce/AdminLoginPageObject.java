package pageObjects.nopCommerce;

import PageUIs.nopCommerce.AdminLoginPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;


public class AdminLoginPageObject extends BasePage
{
    WebDriver driver;

    public AdminLoginPageObject(WebDriver driver)
    {
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress)
    {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX,emailAddress);
    }

    public void enterToPasswordTextbox(String password)
    {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX,password);
    }

    public AdminDashboardPageObject clickLoginButton(WebDriver driver)
    {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getAdminDashboardPage(driver);
    }
}
