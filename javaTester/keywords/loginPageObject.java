package keywords;

public class loginPageObject extends BasePage{
    String browserName;
    public loginPageObject(String browserName)
    {
        // Gọi qua constructor của lớp cha (BasePage)
        super(browserName);
        //...
    }
    @Override
    public boolean isPageDisplayed()
    {
        String browserName="Chrome";
        System.out.println(browserName);
        if (browserName.equals("Chrome"))
        {
            String fullName= "";
        }
        System.out.println(this.browserName);
        // this: lấy biến global
        return false;
    }

}
