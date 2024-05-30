package irepository;

import model.Booking;

import java.util.List;

public interface IBookingRepository {
    public int insertBooking(Booking booking);

    public void updateBooking(Booking booking);

    public Booking getBookingById(int bookingId);

    List<Booking> getAllBookings();

    List<Booking> getActiveBookingsByUser(int userId);
}
