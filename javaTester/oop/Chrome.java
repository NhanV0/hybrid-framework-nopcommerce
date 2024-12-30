package oop;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Chrome extends Browser implements iBrowser{
    WebDriver driver;
    @Override
    public void openURl() {

    }

    @Override
    public void back() {

    }

    @Override
    public void forward() {

    }

    @Override
    public void refresh() {

    }

    public WebElement FindElement(By by){

        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(100))
                .ignoring(NoSuchElementException.class);
        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }
}
