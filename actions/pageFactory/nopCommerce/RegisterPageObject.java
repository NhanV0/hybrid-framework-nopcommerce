package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject
{
    WebDriver driver;
    // 1 - Dinh nghia element trong chinh class nay


    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement firstNameTextbox;
    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lastNameTextbox;
    @FindBy(xpath= "//input[@id='Email']")
    private WebElement emailTextbox;
    @FindBy(xpath= "//input[@id='Password']")
    private WebElement passwordTextbox;
    @FindBy(xpath= "//input[@id='ConfirmPassword']")
    private WebElement confirmPasswordTextbox;
    @FindBy(xpath= "//button[@id='register-button']")
    private WebElement registerButton;
    @FindBy(xpath= "//div[@class='result']")
    private WebElement registerSuccessMessage;
    @FindBy(xpath= "//a[@class='ico-logout']")
    private WebElement logoutLink;

    // 2 - Init cac elements len
    public RegisterPageObject(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 3 - Cac actions tuong tu nhu Page Object


}
