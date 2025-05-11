package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Random;

public class BaseTest
{
    private WebDriver driver;
    protected WebDriver getBrowserDriver(String urlValue, String browserName)
    {
        BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());
        switch (browserType)
        {
            case EDGE:
                driver = new EdgeDriver();
                break;

            case CHROME:
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser name is not valid");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(urlValue);
        return driver;

    }

    protected int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    protected void closeBrowserDriver()
    {
        if (driver!=null)
        {
            driver.quit();
        }
    }

}
