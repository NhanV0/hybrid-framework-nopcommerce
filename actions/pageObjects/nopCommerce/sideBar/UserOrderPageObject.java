package pageObjects.nopCommerce.sideBar;

import org.openqa.selenium.WebDriver;

public class UserOrderPageObject extends UserSideBarPageObject
{
    WebDriver driver;
    public UserOrderPageObject(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }


}
