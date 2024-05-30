package service;

import model.AutoOwner;
import model.Booking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookingCheckerTest {
    @Mock
    private IBookingService mockService;

    @InjectMocks
    private BookingChecker checker;

    @Test
    void getDelay() {
        int expectedDelay = 60 - LocalDateTime.now().getMinute();
        int actualDelay = BookingChecker.getDelay();

        assertEquals(expectedDelay, actualDelay);
    }

    @Test
    void initImmediatelyChecking() {
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking(1, LocalDateTime.now().minusHours(10), LocalDateTime.now().minusHours(1),
                1, 2));
        bookingList.add(new Booking(1, LocalDateTime.now().minusHours(10), LocalDateTime.now().plusHours(1),
                1, 2));
        bookingList.add(new Booking(1, LocalDateTime.now().minusHours(10), LocalDateTime.now().minusHours(2),
                1, 2));

        Mockito.when(mockService.getAllBookings()).thenReturn(bookingList);

        checker.initImmediatelyChecking();

        Mockito.verify(mockService, Mockito.times(2))
                .closeBooking(Mockito.any(Booking.class));
        Mockito.verify(mockService).closeBooking(bookingList.get(0));
        Mockito.verify(mockService).closeBooking(bookingList.get(2));
        Mockito.verify(mockService, Mockito.never()).closeBooking(bookingList.get(1));
    }
}