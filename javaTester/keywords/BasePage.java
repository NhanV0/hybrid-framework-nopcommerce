package keywords;

public abstract class BasePage
{
    public BasePage(String browserName) {
    }

    public BasePage() {
    }
    // Dùng để thể hiện tính chất trừu tượng của OOP trong Java
    public abstract boolean isPageDisplayed();

    // Non-abstract method
    public void clickToElement()
    {
    }

    // Private: chỉ dùng trong nội bộ class
    private String fullName;
    // Default: có thể dùng trong nội bộ class hoặc class ngoài nhưng phải cùng package
    String City;
    // Protected: Phải cùng package và có kế thừa thì mới dùng đc
    protected String address;
    // Public: Không giới hạn
    public String phoneNumber;

    // If, else, switch - case
    // Loop: for, while, do-while

    public boolean isElementDisplayed()
    {
        try {
            // Action - Happy Case
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally {
         // bắt buộc phải chạy qua.
        }
        return false;
    }



}
