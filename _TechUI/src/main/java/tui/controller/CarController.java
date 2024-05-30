package tui.controller;

import model.Car;
import repository.CarRepository;
import service.CarService;
import tui.view.CarView;

import java.util.List;

public class CarController {
    CarView carView = new CarView();
    CarRepository carRepository = new CarRepository();
    CarService carService = new CarService(carRepository);
    List<Car> cars = null;
    public void execute(int userId) {
        refreshCars(userId);
        printCarMenu(userId);
    }

    public void refreshCars(int userId) {
        cars = carService.getCarsByUser(userId);
    }

    public void printCarMenu(int userId) {
        while (true) {
            carView.printCarMenu();
            int choice = carView.getNumber("Ваш выбор: ");
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> printAllCars(userId);
                case 2 -> addCar(userId);
                case 3 -> removeCar(userId);
            }
        }
    }

    private void addCar(int userId) {
        String number = carView.getString("Введите номер авто: ");
        String model = carView.getString("Введите модель авто: ");
        Car car = Car.createCarModel(number, model, userId);
        carService.createCar(car);
        refreshCars(userId);
        carView.printSuccessAddingCar();
    }

    private void printAllCars(int userId) {
        if (cars == null)
            carView.printNonCars();
        else
            carView.printAllCars(cars);
    }

    private void removeCar(int userId) {
        printAllCars(userId);
        if (cars != null) {
            int choice = carView.getNumber("Введите индекс авто, который хотите удалить: ");
            Car car = cars.get(choice - 1);
            carService.removeCar(car.getNumber());
            refreshCars(userId);
            carView.printSuccessRemovingCar();
        }
    }
}
