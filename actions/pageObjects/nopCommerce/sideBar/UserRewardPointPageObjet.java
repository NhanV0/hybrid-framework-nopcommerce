package pageObjects.nopCommerce.sideBar;

import org.openqa.selenium.WebDriver;

public class UserRewardPointPageObjet extends UserSideBarPageObject
{
    WebDriver driver;
    public UserRewardPointPageObjet(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

}
