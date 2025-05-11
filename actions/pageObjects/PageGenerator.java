package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.UserAddressPageObject;
import pageObjects.nopCommerce.sideBar.UserCustomerInfoPageObject;

import java.lang.reflect.Constructor;

public class PageGenerator
{
    public static <T extends BasePage> T getPageInstance(Class<T> pageClass, WebDriver driver)
    {
        try
        {
            Constructor <T> constructor = pageClass.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (Exception e)
        {
            throw new RuntimeException("Cannot init page class: " + pageClass.getSimpleName(),e);
        }
    }
    // Viet cac doan khoi tao page object thanh cac method

    public static UserHomepageObject getHomePage(WebDriver driver)
    {
        return new UserHomepageObject(driver);
    }

    public static UserLoginPageObject getLoginPage(WebDriver driver)
    {
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPageObject getRegisterPage(WebDriver driver)
    {
        return new UserRegisterPageObject(driver);
    }

    public static UserCustomerInfoPageObject getCustomerInfoPage(WebDriver driver)
    {
        return new UserCustomerInfoPageObject(driver);
    }

    public static UserAddressPageObject getAddressPage(WebDriver driver)
    {
        return new UserAddressPageObject(driver);
    }


    public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver)
    {
        return new AdminDashboardPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver)
    {
        return new AdminLoginPageObject(driver);
    }

}
