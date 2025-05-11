package factoryPattern;

public class User
{
    public static void main(String[] args)
    {
        Car carFactory;
        carFactory = getCar("Honda");
        carFactory.viewCar("Civic 2025");
        carFactory.driveCar();
    }

    public static Car getCar(String carType)
    {
        Car car = null;
        if (carType.equals("Honda"))
        {
            car = new Honda();
        }
        else if(carType.equals("Toyota"))
        {
            car = new Toyota();
        }
        else
        {
            throw new IllegalArgumentException("Car Type is not valid.");
        }
        return car;
    }

}
