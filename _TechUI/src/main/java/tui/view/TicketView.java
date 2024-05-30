package tui.view;

public class TicketView extends View{
    public void printTicketMenu() {
        System.out.println("\n--- Талон ---");
        System.out.println("1) Открыть талон");
        System.out.println("2) Закрыть талон");
        System.out.println("0) Назад");
    }

    public void printSuccessOpeningTicket() {
        System.out.println("Въезд разрешен");
    }

    public void printNonSuccessOpeningTicket() {
        System.out.println("Нет свободных мест");
    }

    public void printSuccessClosingTicket() {
        System.out.println("Парковка успешно оплачена");
    }

    public void printNonSuccessClosingTicket() {
        System.out.println("Недостаточно средств для оплаты");
    }
}
