package tui.view;

public class AdminView extends View{
    public void printAdminMenu() {
        System.out.println("\n--- Личный кабинет сотрудника ---");
        System.out.println("0) Выход");
        System.out.println("1) Добавить сотрудника");
        System.out.println("2) Удалить сотрудника");
    }

    public void printSuccessSignIn() {
        System.out.println("Вход успешно выполнен");
    }

    public void printNonSuccessSignIn() {
        System.out.println("Неверный логин или пароль");
    }
}
