package practice;

import javax.swing.text.Position;

public class EmployeeDRY
{
    private String employeeName;
    private String employeePosition;
    private String employeeType;
    private String employeeSalary;
    private String employeeRate;

    public EmployeeDRY(String employeeName, String employeePosition, String employeeType, String employeeSalary, String employeeRate) {
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.employeeType = employeeType;
        this.employeeSalary = employeeSalary;
        this.employeeRate = employeeRate;
    }

    public void showEmployeeDetail()
    {
        System.out.println("Name = " + this.employeeName);
        System.out.println("Position = " + this.employeePosition);
        System.out.println("Type = " + this.employeeType);
        if(this.employeeType.equals("Fulltime"))
        {
            System.out.println("Salary =" + this.employeeSalary);
        }
        else
        {
            System.out.println("Rate = " + this.employeeRate);
        }
    }

    public static void main(String[] args)
    {
        EmployeeDRY kenedyN = new EmployeeDRY("Kenny", "QC", "Fulltime", "5000", "");
        kenedyN.showEmployeeDetail();
    }
}
