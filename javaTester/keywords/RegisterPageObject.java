package keywords;

import org.testng.Assert;

public class RegisterPageObject implements iBrowser{
    String browserName;
    static String browserVersion;
    static final String ENVIRONMENT = "DEV";
    @Override
    public void clickToElement() {
    }
    public static void selectToElement()
    {

    }

    public  static void main(String[] args)
    {
        RegisterPageObject page = new RegisterPageObject();
        System.out.println(RegisterPageObject.browserVersion);
        Assert.assertTrue(page instanceof RegisterPageObject);
    }
}
