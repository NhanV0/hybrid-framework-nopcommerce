package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPageObject extends BasePage
{
    WebDriver diver;

    public AdminDashboardPageObject(WebDriver diver) {
        this.diver = diver;
    }
}
