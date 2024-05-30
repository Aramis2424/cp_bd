package service;

import model.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BookingChecker {
    private static final int minimumMinutesForBooking = 60;
    private final IBookingService service;
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(2);

    class Task implements Runnable {
        @Override
        public void run() {
            List<Booking> bookings = service.getAllBookings();
            check(bookings);
        }
    }

    public BookingChecker(IBookingService service) {
        this.service = service;
    }

    private void check(List<Booking> bookings) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (bookings == null)
            return;
        for (Booking booking : bookings) {
            if (booking.getActive() && booking.getFinishDate().isBefore(currentTime)) {
                service.closeBooking(booking);
            }
        }
    }

    public static int getDelay() {
        LocalDateTime currentTime = LocalDateTime.now();
        return minimumMinutesForBooking - currentTime.getMinute();
    }

    public void initScheduleChecking() {
        BookingChecker.Task task = new BookingChecker.Task();
        scheduler.scheduleAtFixedRate(task, getDelay(), 60, TimeUnit.MINUTES);
    }

    public void initImmediatelyChecking() {
        BookingChecker.Task task = new BookingChecker.Task();
        task.run();
    }

    public void stop() {
        scheduler.shutdown();
    }
}
