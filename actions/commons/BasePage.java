package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // Chứa các common functions cho các class bên page object
    private long LONG_TIMEOUT = 30;
    public void openPageURL(WebDriver driver, String pageURL)
    {
        driver.get(pageURL);
    }

    public String getPageTitle(WebDriver driver)
    {
        return driver.getTitle();
    }

    public  String getCurrentURL(WebDriver driver)
    {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver)
    {
        return driver.getPageSource();
    }

    public  void backToPage(WebDriver driver)
    {
        driver.navigate().back();
    }

    public  void forwardToPage(WebDriver driver)
    {
        driver.navigate().forward();
    }

    public  void refreshCurrentPage(WebDriver driver)
    {
        driver.navigate().refresh();
    }

    private Alert waitUntilAlertPresence(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver)
    {
        waitUntilAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver)
    {
        waitUntilAlertPresence(driver).dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String valueToSend)
    {
        waitUntilAlertPresence(driver).sendKeys(valueToSend);
    }

    public String getAlertText(WebDriver driver)
    {
        return waitUntilAlertPresence(driver).getText();
    }

    public void switchToWindowByID(WebDriver driver, String parentID)
    {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title)
    {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID)
    {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    private WebElement getWebElement(WebDriver driver, String locator)
    {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getListWebElement(WebDriver driver, String locator)
    {
        return driver.findElements(getByXpath(locator));
    }

    private  By getByXpath(String locator)
    {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, String locator)
    {
        getWebElement(driver, locator).click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String valueToSend)
    {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(valueToSend);

    }

    public void selectItemDropdown(WebDriver driver, String locator, String textItem)
    {
        new Select(getWebElement(driver, locator)).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropDown(WebDriver driver, String locator)
    {
       return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator)
    {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
       WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
       explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(parentLocator))).click();
       sleepInSecond(2);
       List<WebElement> allItems = waitForListElementPresence(driver,childItemLocator);

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem))
            {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond)
    {
        try {
            Thread.sleep(timeInSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  String getElementText(WebDriver driver, String locator)
    {
        return getWebElement(driver, locator).getText();
    }

    public  String getElementAttribute(WebDriver driver, String locator, String attributeName)
    {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public  String getCssValue(WebDriver driver, String locator, String propertyName)
    {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexByRGB(String rgbValue)
    {
        return Color.fromString(rgbValue).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String locator)
    {
        return getListWebElement(driver, locator).size();
    }

    public void checkToCheckBoxRadio(WebDriver driver, String locator)
    {
        if (!getWebElement(driver, locator).isSelected())
        {
            getWebElement(driver, locator).click();
        }
    }
    public void uncheckToCheckBoxRadio(WebDriver driver, String locator)
    {
        if (getWebElement(driver, locator).isSelected())
        {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator)
    {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator)
    {
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator)
    {
        return getWebElement(driver, locator).isSelected();
    }

    public WebDriver switchToIFrame(WebDriver driver, String locator)
    {
        return driver.switchTo().frame(getWebElement(driver, locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver)
    {
        return driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator)
    {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void doubleToElement(WebDriver driver, String locator)
    {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator)
    {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator)
    {
        new Actions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key)
    {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    public String getDomain(WebDriver driver)
    {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.domain");
    }

    public String getInnerText(WebDriver driver)
    {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText");
    }

    public void navigateToURLByJS(WebDriver driver, String url)
    {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(WebDriver driver, String locator)
    {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver)
    {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName)
    {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator)
    {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }

    // Wait
    public WebElement waitForElementVisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public List <WebElement> waitForListElementVisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitForElementInvisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public WebElement waitForElementPresence(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitForListElementPresence(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitForElementSelected(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }


}
