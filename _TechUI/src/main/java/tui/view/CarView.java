package tui.view;

import model.Car;

import java.util.List;

public class CarView extends View{
    public void printCarMenu() {
        System.out.println("\n--- Транспортные средства ---");
        System.out.println("1) Список авто");
        System.out.println("2) Добавить авто");
        System.out.println("3) Удалить авто");
        System.out.println("0) Назад");
    }

    public void printAllCars(List<Car> cars) {
        int i = 1;
        for (Car car : cars)
            System.out.printf("%d) %s - %s\n",
                    i++, car.getModel(), car.getNumber());
    }

    public void printNonCars() {
        System.out.println("У Вас не зарегистрирован ни один автомобиль");
    }

    public void printSuccessAddingCar() {
        System.out.println("Авто успешно добавлено");
    }

    public void printSuccessRemovingCar() {
        System.out.println("Авто успешно удалено");
    }
}
