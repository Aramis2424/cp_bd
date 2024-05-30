package repository;

import irepository.IBookingRepository;
import model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingRepositoryTest {
    private IBookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        bookingRepository = new BookingRepository();
    }

    @Test
    void insertBooking() {
        Booking booking = Booking.
                createBookingModel(LocalDateTime.now().plusHours(2), 1, 1);
        int id = bookingRepository.insertBooking(booking);

        Booking newBooking = bookingRepository.getBookingById(id);

        assertEquals(1, newBooking.getAutoOwnerFID());
        assertEquals(1, newBooking.getParkingFID());
    }

    @Test
    void updateBooking() {
        Booking booking = Booking.
                createBookingModel(LocalDateTime.now().plusHours(2), 1, 1);
        int id = bookingRepository.insertBooking(booking);

        Booking newBooking = bookingRepository.getBookingById(id);
        assertTrue(newBooking.getActive());
        newBooking.setActive(false);
        bookingRepository.updateBooking(newBooking);

        newBooking = bookingRepository.getBookingById(id);
        assertEquals(1, newBooking.getAutoOwnerFID());
        assertEquals(1, newBooking.getParkingFID());
        assertFalse(newBooking.getActive());
    }

    @Test
    void getBookingById() {
        Booking booking = Booking.
                createBookingModel(LocalDateTime.now().plusHours(2), 1, 1);
        int id = bookingRepository.insertBooking(booking);

        Booking newBooking = bookingRepository.getBookingById(id);

        assertEquals(1, newBooking.getAutoOwnerFID());
        assertEquals(1, newBooking.getParkingFID());
    }

    @Test
    void getAllBookings() {
        List<Booking> bookingList = new ArrayList<>(3);
        bookingList.add(Booking.
                createBookingModel(LocalDateTime.now().plusHours(2), 1, 1));
        bookingList.add(Booking.
                createBookingModel(LocalDateTime.now().plusHours(3), 1, 2));
        bookingList.add(Booking.
                createBookingModel(LocalDateTime.now().plusHours(1), 2, 3));
        for (Booking booking : bookingList)
            bookingRepository.insertBooking(booking);

        List<Booking> bookings = bookingRepository.getAllBookings();

        assertNotNull(bookings);
        assertTrue(bookings.size() >= 3);
    }

    @Test
    void getActiveBookingsByUser() {
        List<Booking> bookingList = new ArrayList<>(3);
        bookingList.add(Booking.
                createBookingModel(LocalDateTime.now().plusHours(2), 11, 1));
        bookingList.add(Booking.
                createBookingModel(LocalDateTime.now().plusHours(3), 22, 2));
        bookingList.add(Booking.
                createBookingModel(LocalDateTime.now().plusHours(1), 22, 2));

        for (Booking booking : bookingList)
            bookingRepository.insertBooking(booking);

        List<Booking> bookings = bookingRepository.getActiveBookingsByUser(2);

        assertNotNull(bookings);
        assertTrue(bookings.size() >= 2);
    }
}