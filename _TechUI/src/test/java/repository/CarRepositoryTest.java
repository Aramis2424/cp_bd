package repository;

import irepository.ICarRepository;
import model.AutoOwner;
import model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    private ICarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void insertCar() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        Car car = Car.createCarModel("A111AA01", "BMW", id);
        carRepository.insertCar(car);

        Car newCar = carRepository.getCarByNumber("A111AA01");

        assertEquals("BMW", newCar.getModel());
    }

    @Test
    void updateCar() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        Car car = Car.createCarModel("A111AA02", "BMW", 1);
        carRepository.insertCar(car);

        Car newCar = carRepository.getCarByNumber("A111AA02");
        newCar.setModel("Hundai");
        carRepository.updateCar(newCar);

        newCar = carRepository.getCarByNumber("A111AA02");

        assertEquals("Hundai", newCar.getModel());
    }

    @Test
    void deleteCar() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        Car car = Car.createCarModel("A111AA03", "BMW", id);
        carRepository.insertCar(car);

        carRepository.deleteCar("A111AA03");
        Car deletedCar = carRepository.getCarByNumber("A111AA03");

        assertNull(deletedCar);
    }

    @Test
    void getCarByNumber() {
        Car car = Car.createCarModel("A111AA04", "BMW", 1);
        carRepository.insertCar(car);

        Car newCar = carRepository.getCarByNumber("A111AA04");

        assertEquals("BMW", newCar.getModel());
    }

    @Test
    void getCarsByUser() {
        AutoOwner autoOwner1 = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        AutoOwner autoOwner2 = AutoOwner.createAutoOwnerModel("Zzz", "Yyy",
                "zy@com", "123", 4999, 2, LocalDateTime.now());
        AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
        int id1 = autoOwnerRepository.insertAutoOwner(autoOwner1);
        int id2 = autoOwnerRepository.insertAutoOwner(autoOwner2);

        List<Car> carList = new ArrayList<>();
        carList.add(Car.createCarModel("B111BB01", "9", id2));
        carList.add(Car.createCarModel("B111BB02", "6", id2));
        carList.add(Car.createCarModel("B111BB03", "14", id2));
        carList.add(Car.createCarModel("A222BB01", "BMW", id1));

        for (Car car : carList)
            carRepository.insertCar(car);
        List<Car> cars = carRepository.getCarsByUser(id2);

        assertEquals("Zzz", autoOwnerRepository.getAutoOwnerByID(id2).getFirstName());
        assertEquals("9", cars.get(0).getModel());
        assertEquals("6", cars.get(1).getModel());
        assertEquals("14", cars.get(2).getModel());
    }
}