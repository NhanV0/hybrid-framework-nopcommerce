package phuongHoaXuan;

public class XuanLan {
    // Private (Chỉ có nhà bà Xuân Lan đc uống)
    private String latte = "Latte";
    // Default: Trong class dùng được
    // Ngoài class nhưng phải cùng package
    // Con nhưng ngoài class ko dùng được
    String espresso = "Espresso";
    String capuchino = "Cappuchino";
    String macchiato = "macchiato";

    // Private (Chỉ có nhà bà Xuân Lan đc uống)
    private String getLatte(){
        return latte;
    }

    public static void main (String[] args)
    {
        XuanLan xuanLan = new XuanLan();
        System.out.println("Drink: " + xuanLan.getLatte());
    }


}
