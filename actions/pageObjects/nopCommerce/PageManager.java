package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.sideBar.UserCustomerInfoPageObject;

public class PageManager
{
    // Ap dung Factory pattern de quan ly viec khoi tao cac page object
    public static Object getPage(WebDriver driver, String pageName) {
        switch (pageName) {
            case "HomePage":
                return new UserHomepageObject(driver);
            case "LoginPage":
                return new UserLoginPageObject(driver);
            case "RegisterPage":
                return new UserRegisterPageObject(driver);
            case "CustomerInfoPage":
                return new UserCustomerInfoPageObject(driver);
            default:
                return new IllegalArgumentException("Page Name is incorrect");
        }
    }
}
