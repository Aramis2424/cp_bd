package service;

import java.util.List;

import model.Car;

public interface ICarService {
    public void createCar(Car car);

    public void updateCar(Car car);

    public void removeCar(String number);

    public Car getCarByNumber(String number);

    public List<Car> getCarsByUser(int userId);
}
