package commons;

import PageUIs.jquery.HomePageUI;
import PageUIs.nopCommerce.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.UserAddressPageObject;
import pageObjects.nopCommerce.sideBar.UserCustomerInfoPageObject;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static pageObjects.PageGenerator.*;

public class BasePage
{
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

    private String castLocator(String locator, String ... value)
    {
        return String.format(locator, (Object[]) value);
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
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getListWebElement(WebDriver driver, String locator)
    {
        return driver.findElements(getByLocator(locator));
    }

    private  By getByXpath(String locator)
    {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, String locator)
    {
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... dynamicValues)
    {
        getWebElement(driver, castLocator(locator, dynamicValues)).click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String valueToSend)
    {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(valueToSend);
    }

    public void sendKeysToElement(WebDriver driver, String locator, String valueToSend, String... dynamicValue)
    {
        getWebElement(driver, castLocator(locator, dynamicValue)).clear();
        getWebElement(driver, castLocator(locator, dynamicValue)).sendKeys(valueToSend);
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
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentLocator))).click();
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

    public  String getElementAttribute(WebDriver driver, String locator, String attributeName, String... dynamicValue)
    {
        return getWebElement(driver, castLocator(locator, dynamicValue)).getAttribute(attributeName);
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

    public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValue)
    {
        return getWebElement(driver, castLocator(locator, dynamicValue)).isDisplayed();
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

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String ...dynamicValue)
    {
        new Actions(driver).sendKeys(getWebElement(driver, castLocator(locator, dynamicValue)), key).perform();
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

    public boolean isImageLoaded(WebDriver driver, String locator)
    {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }

    // Wait
    public boolean waitForElementAttributeToBe(WebDriver driver, String locator, String attributeName, String attributeValue)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeToBe(getByLocator(locator), attributeName, attributeValue));
    }

    public boolean waitForElementAttributeToBe(WebDriver driver, String locator, String attributeName, String attributeValue, String... dynamicValue)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeToBe(getByLocator(castLocator(locator, dynamicValue)), attributeName, attributeValue));
    }

    public boolean waitForElementAttributeContains(WebDriver driver, String locator, String attributeName, String attributeValue)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeContains(getByLocator(locator), attributeName, attributeValue));
    }

    public boolean waitForElementAttributeContains(WebDriver driver, String locator, String attributeName, String attributeValue, String... dynamicValue)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeContains(getByLocator(castLocator(locator, dynamicValue)), attributeName, attributeValue));
    }

    public WebElement waitForElementVisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitForElementVisible(WebDriver driver, String locator, String... dynamicValue)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castLocator(locator, dynamicValue))));
    }

    public List <WebElement> waitForListElementVisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean waitForElementInvisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator, String... dynamicValues)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(castLocator(locator, dynamicValues))));
    }

    public WebElement waitForElementPresence(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public List<WebElement> waitForListElementPresence(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean waitForElementSelected(WebDriver driver, String locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public UserCustomerInfoPageObject clickToMyAccountLinkAtUserSite(WebDriver driver)
    {
        waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_LINK);
        return getCustomerInfoPage(driver);
    }

    public UserHomepageObject clickToLogoutLinkUserSite(WebDriver driver)
    {
        waitForElementClickable(driver, BasePageUI.USER_LOGOUT_LINK);;
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK);
        return getHomePage(driver);
    }


    public AdminLoginPageObject openAdminSite(WebDriver driver,String adminUrl)
    {
        openPageURL(driver, adminUrl);
        return getAdminLoginPage(driver);
    }

    public boolean isPageLoadedSuccess(WebDriver driver)
    {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public AdminLoginPageObject clickToLogoutLinkAtAdminSite(WebDriver driver)
    {
        waitForElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK);
        return PageGenerator.getPageInstance(AdminLoginPageObject.class, driver);
    }


    public UserHomepageObject openUserSite(WebDriver driver, String userUrl)
    {
        openPageURL(driver, userUrl);
        return PageGenerator.getPageInstance(UserHomepageObject.class, driver);
    }

    public By getByLocator(String locator)
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

    public void uploadMultipleFiles(WebDriver driver, String... fileNames)
    {
        String filePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
        StringBuilder fullFileNameBuilder = new StringBuilder();

        for (String file : fileNames)
        {
            fullFileNameBuilder.append(filePath).append(file).append("\n");
        }

        String fullFileName = fullFileNameBuilder.toString().trim();
        getWebElement(driver, HomePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
    }


}
