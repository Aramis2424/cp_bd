package tui.controller;

import model.Booking;
import model.Parking;
import repository.*;
import service.AutoOwnerService;
import service.BookingChecker;
import service.BookingService;
import service.ParkingService;
import tui.view.BookingView;

import javax.swing.event.ListDataListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingController {
    private BookingView bookingView = new BookingView();
    private ParkingRepository parkingRepository = new ParkingRepository();
    private BookingRepository bookingRepository = new BookingRepository();
    private AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
    private SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    private TariffRepository tariffRepository = new TariffRepository();
    private AutoOwnerService autoOwnerService = new AutoOwnerService(autoOwnerRepository);
    private ParkingService parkingService = new ParkingService(parkingRepository);

    private BookingService bookingService = new BookingService(bookingRepository, parkingRepository,
            autoOwnerRepository, subscriptionRepository, tariffRepository, autoOwnerService, parkingService);

    private ParkingController parkingController = new ParkingController();

    public void execute(int userId) {
        printBookingMenu(userId);
    }

    public void printBookingMenu(int userId) {
        while (true) {
            bookingView.printBookingMenu();
            int choice = bookingView.getNumber("Ваш выбор: ");
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> printAllBookings(userId);
                case 2 -> createBooking(userId);
            }
        }
    }

    public void printAllBookings(int userId) {
        List<Booking> bookings = new ArrayList<>();
        bookings = bookingService.getActiveBookingsByUser(userId);
        if (bookings == null)
            bookingView.printNoBookingsMsg();
        else {
            int i = 1;
            for (Booking booking : bookings)
                bookingView.printAllBookings(i++, booking, parkingRepository
                        .getParkingById(booking.getParkingFID()).getAddress());
        }
    }

    public void createBooking(int userId) {
        int parkingId = getBookingParking();
        LocalDateTime time = getBookingFinishDate();
        Booking booking = Booking.createBookingModel(time, parkingId, userId);
        bookingService.startBooking(booking);
        bookingView.printSuccessCreationBooking();
    }

    public LocalDateTime getBookingFinishDate() {
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime[] times = new LocalDateTime[3];
        times[0] = LocalDateTime.of(current.getYear(), current.getMonth(),
                current.getDayOfMonth(), (current.getHour() + 1) % 24, 0);
        for (int i = 1; i < times.length; i++)
            times[i] = times[i-1].plusHours(1);
        int choice = bookingView.getBookingFinishDate(times);
        return times[choice-1];
    }

    public int getBookingParking() {
        Map<Integer, Integer> parkingsIds = parkingController.makeChoiceParkings();
        int choice = bookingView.getBookingParking();
        return parkingsIds.get(choice);
    }
}
