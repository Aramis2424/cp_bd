package tui.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AutoOwnerView extends View{

    public void printUserMenu() {
        System.out.println("\n--- Личный кабинет ---");
        System.out.println("1) Состояние счета");
        System.out.println("2) Пополнить счет");
        System.out.println("3) Транспортные средства");
        System.out.println("4) Подписки");
        System.out.println("0) Назад");
    }
    public void printSuccessSignIn() {
        System.out.println("Вход успешный");
    }

    public void printNonSuccessSignIn() {
        System.out.println("Неверные данные. Повторите ввод");
    }

    public void printSuccessSignUp() {
        System.out.println("Регистрация успешна");
    }

    public void printAccount(int account) {
        System.out.printf("Количество баллов на Вашем счете: %d\n", account);
    }

    public void printSuccessTopUpAccount() {
        System.out.println("Счет успешно пополнен");
    }
}
