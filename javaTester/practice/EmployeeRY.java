package practice;

public class EmployeeRY
{
    public void showFullTimeEmployeeDetail()
    {
        System.out.println("Name = Nhan Vo");
        System.out.println("Position = Developer");
        System.out.println("Type = Full time");
        System.out.println("Salary = 2000");
    }

    public void showPartTimeEmployeeDetail()
    {
        System.out.println("Name = Annie Truong");
        System.out.println("Position = QA");
        System.out.println("Type = Part time");
        System.out.println("Salary = 20");
    }

    public static void main(String[] args)
    {
        EmployeeRY firstEmployee = new EmployeeRY();
        firstEmployee.showFullTimeEmployeeDetail();

        EmployeeRY secondEmployee = new EmployeeRY();
        secondEmployee.showPartTimeEmployeeDetail();
    }
}
