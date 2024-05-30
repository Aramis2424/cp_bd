package tui.view;

import model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public class BookingView extends View {
    public void printBookingMenu() {
        System.out.println("\n--- Бронь ---");
        System.out.println("1) Список моих броней");
        System.out.println("2) Создать бронь");
        System.out.println("0) Назад");
    }

    public void printAllBookings(int i, Booking booking, String street) {
            System.out.printf("%d) Адрес парковки: %s\n\tДата начала: %s\n\tДата конца: %s\n",
                    i, street, booking.getStartDate().toString(), booking.getFinishDate().toString());
    }

    public void printNoBookingsMsg() {
        System.out.println("У Вас пока нет ни одной брони");
    }

    public int getBookingFinishDate(LocalDateTime[] times) {
        for (int i = 0; i < times.length; i++) {
            System.out.printf("%d) %s\n",
                    i + 1, times[i].toString());
        }
        return this.getNumber("Выберите дату окончания брони: ");
    }

    public int getBookingParking() {
        return this.getNumber("Выберите парковку: ");
    }

    public void printSuccessCreationBooking() {
        System.out.println("Бронь успешна создана");
    }
}
