package service;

import model.Booking;

import java.util.List;

public interface IBookingService {
    public int startBooking(Booking booking);

    public void closeBooking(Booking booking);

    public Booking getBookingById(int id);

    public List<Booking> getAllBookings();

    public List<Booking> getActiveBookingsByUser(int userId);
}
