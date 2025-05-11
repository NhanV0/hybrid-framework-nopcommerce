package pageObjects.nopCommerce.sideBar;

import org.openqa.selenium.WebDriver;

public class UserAddressPageObject extends UserSideBarPageObject
{

    WebDriver driver;
    public UserAddressPageObject(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }
}
