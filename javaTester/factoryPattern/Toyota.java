package factoryPattern;

public class Toyota extends  Car
{
    @Override
    public void viewCar(String carName)
    {
        System.out.println("View" + carName);

    }

    @Override
    public void driveCar()
    {
        System.out.println("Drive");

    }
}
