package PageUIs.jquery;

public class HomePageUI
{
    public static final String PAGINATION_PAGE_BY_NUMBER ="xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String PAGINATION_PAGE_BY_NUMBER_ACTIVE ="xpath=//li[@class='qgrd-pagination-page']/a[contains(@class,'active') and text()='%s']";
    public static final String PAGINATION_ALL_PAGE ="Css=li.qgrd-pagination-page";
    public static final String COLUMN_TEXTBOX_BY_COLUMN_NAME ="Xpath=//div[text()='%s']/parent::div//following-sibling::input";
    public static final String DELETE_ICON_BY_COUNTRY_NAME ="Xpath=//td[text()='%s']/preceding-sibling::td/button[contains(@class,'remove')]";
    public static final String EDIT_ICON_BY_COUNTRY_NAME ="Xpath=//td[text()='Albania']/preceding-sibling::td/button[contains(@class,'edit')]";

    public static final String UPLOAD_FILE_TYPE ="Css=input[type='file']";

}
