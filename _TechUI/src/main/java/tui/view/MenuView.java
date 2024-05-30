package tui.view;

public class MenuView extends View {
    public void printStartMenu() {
        System.out.println("=== Вход / Регистрация ===");
        System.out.println("1) Вход");
        System.out.println("2) Регистрация");
        System.out.println("3) Вход для сотрудника");
        System.out.println("0) Выход");
    }

    public void printBaseMenu() {
        System.out.println("\n=== Меню ===");
        System.out.println("0) Выход");
        System.out.println("1) Парковки");
        System.out.println("2) Бронь");
        System.out.println("3) Талон");
        System.out.println("4) Личный кабинет");
    }
}
