package pageObjects.DataTableExample;

import PageUIs.DataTableExample.HomePageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage
{
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterTextToTableSearch(String text)
    {
        waitForElementVisible(driver, HomePageUI.SEARCH_TABLE_TEXTBOX);
        sendKeysToElement(driver, HomePageUI.SEARCH_TABLE_TEXTBOX, text);
    }

    public List<String> getOfficeColumnValues() throws InterruptedException
    {
        List<String> allOffices = new ArrayList<>();
        while (true)
        {
            List<WebElement> officeCells = getListWebElement(driver, HomePageUI.OFFICE_COLUMN_VALUE);
            for (WebElement cell : officeCells) {
                allOffices.add(cell.getText());
            }

            WebElement nextButton = driver.findElement(getByLocator(HomePageUI.PAGING_BUTTON_NEXT));

            String classAttr = nextButton.getAttribute("class");
            if (classAttr != null && classAttr.contains("disabled"))
            {
                break;
            }

            nextButton.click();
            Thread.sleep(1000);
        }
        return allOffices;
    }

    public int getTotalResultCount()
    {
        String paginationText = getElementText(driver, HomePageUI.PAGING_RECORD_COUNT); // ← cần sửa lại locator
        String[] parts = paginationText.split(" ");
        return Integer.parseInt(parts[5]);
    }
}