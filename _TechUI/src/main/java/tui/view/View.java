package tui.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    public int getNumber(String msg) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(msg);
            try {
                number = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введено не число. Попробуйте еще раз.");
                System.out.print(msg);
                scanner.next();
            }
        }

        return number;
    }

    public String getString(String msg) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(msg);
        String str = scanner.nextLine();

        return str;
    }
}
