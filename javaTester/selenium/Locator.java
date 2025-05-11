package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;

public class Locator
{
    public static void main(String[] args)
    {

        System.out.println(getByLocator("Xpath=//a[@class='ico-register']"));
        System.out.println(getByLocator("Css=a.ico-register"));
        System.out.println(getByLocator("Css=input#FirstName]"));
    }

    public static By getByLocator(String locator)
    {
        String[] locatorArr =  locator.split("=");
        String prefixLocator = locatorArr[0].toLowerCase();
        if (prefixLocator.isEmpty() || prefixLocator == null)
        {
            throw new RuntimeException("Locator type cannot be null or empty.");
        }

        switch  (locator.split("=")[0].toLowerCase())
        {
            case "xpath":
                return By.xpath(locator.substring(6));
            case "css":
                return By.cssSelector(locator.substring(4));
            case "id":
                return By.id(locator.substring(3));
            case "class":
                return By.className(locator.substring(6));
            case "name":
                return By.name(locator.substring(5));
            default:
                throw new InvalidArgumentException("Locator type is not supported");

        }

    }
}
