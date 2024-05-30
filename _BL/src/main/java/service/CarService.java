package service;

import irepository.ICarRepository;
import model.Car;

import java.util.List;

public class CarService implements ICarService {
    private ICarRepository carRep;

    public CarService(ICarRepository carRep) {
        this.carRep = carRep;
    }

    @Override
    public void createCar(Car car) {
        carRep.insertCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carRep.updateCar(car);
    }

    @Override
    public void removeCar(String number) {
        carRep.deleteCar(number);
    }

    @Override
    public Car getCarByNumber(String number) {
        return carRep.getCarByNumber(number);
    }

    @Override
    public List<Car> getCarsByUser(int userId) {
        return carRep.getCarsByUser(userId);
    }
}
