package irepository;

import model.Car;

import java.util.List;

public interface ICarRepository {
    public void insertCar(Car car);

    public void updateCar(Car car);

    public void deleteCar(String number);

    public Car getCarByNumber(String numberCar);

    public List<Car> getCarsByUser(int userId);
}
