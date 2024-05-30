package service;

import exception.NotFreePlacesException;
import irepository.*;
import model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private IBookingRepository mockBookingRep;
    @Mock
    private IAutoOwnerRepository mockAutoOwnerRep;
    @Mock
    private ISubscriptionRepository mockSubscriptionRep;
    @Mock
    private IParkingRepository mockParkingRep;
    @Mock
    private ITariffRepository mockTariffRep;
    @Mock
    private IAutoOwnerService mockAutoOwnerService;
    @Mock
    private IParkingService mockParkingService;

    @InjectMocks
    BookingService bookingService;

    @Test
    void getFreePlaces() {
        Booking booking = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 62, 1);
        Parking parking = new Parking(62, "Dvoskiy 2/3", 50, 21, 42);
        Mockito.when(mockParkingRep.getParkingById(62)).thenReturn(parking);

        int actualFreePlacesCnt = bookingService.getFreePlaces(booking);

        assertEquals(21, actualFreePlacesCnt);
    }

    @Test
    void getGraceHours() {
        Booking booking = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 13, 30), 1, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        int expectedGraceHours = 2;
        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), expectedGraceHours);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        int actualGraceHours =  bookingService.getGraceHours(booking);

        assertEquals(expectedGraceHours, actualGraceHours);
    }

    @Test
    public void getHours_NonEntireHour() {
        Booking booking = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 1, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), 1);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        long expectedHours = 2;
        long actualHours = bookingService.getHours(booking);

        assertEquals(expectedHours, actualHours);
    }

    @Test
    public void getHours_EntireHour() {
        Booking booking = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 0), 1, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), 1);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        long expectedHours = 1;
        long actualHours = bookingService.getHours(booking);

        assertEquals(expectedHours, actualHours);
    }

    @Test
    void getCost() {
        Booking booking = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 3, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), 1);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        Parking parking = new Parking(3, "asd", 10, 5, 5);
        Mockito.when(mockParkingRep.getParkingById(3)).thenReturn(parking);

        Tariff tariff = new Tariff(5, "zxc", 50, 100);
        Mockito.when(mockTariffRep.getTariffById(5)).thenReturn(tariff);

        int expectedCost = (int) Math.ceil(2 * 50 * (1 - 0.25));
        int actualCost = bookingService.getCost(booking);
        assertEquals(expectedCost, actualCost);
    }

    @Test
    void startBooking_NotFreePlaces() {
        Booking booking = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 62, 1);
        Parking parking = new Parking(62, "Dvoskiy 2/3", 50, 0, 42);
        Mockito.when(mockParkingRep.getParkingById(62)).thenReturn(parking);

        assertThrows(NotFreePlacesException.class, () -> {bookingService.startBooking(booking);});
    }

    @Test
    void startBooking_FreePlaces() {
        Booking booking = new Booking(-1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 62, 1);

        Parking parking = new Parking(62, "Dvoskiy 2/3", 50, 10, 42);
        Mockito.when(mockParkingRep.getParkingById(62)).thenReturn(parking);
        Mockito.doNothing().when(mockParkingService).decFreePlaces(62);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), 1);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        Tariff tariff = new Tariff(42, "zxc", 50, 100);
        Mockito.when(mockTariffRep.getTariffById(42)).thenReturn(tariff);

        Mockito.when(mockBookingRep.insertBooking(booking)).thenReturn(1);

        bookingService.startBooking(booking);

        assertEquals(1, booking.getId());
    }

    @Test
    void closeBooking() {
        Booking booking = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 3, 1);
        Mockito.doNothing().when(mockBookingRep).updateBooking(Mockito.any());
        Mockito.doNothing().when(mockParkingService).incFreePlaces(3);

        bookingService.closeBooking(booking);

        assertEquals(Boolean.FALSE, booking.getActive());
    }

    @Test
    void getBookingById() {
        Booking expected = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 13, 30), 1, 1);
        Mockito.when(mockBookingRep.getBookingById(1)).thenReturn(expected);

        Booking actual = bookingService.getBookingById(1);
        assertEquals(expected, actual);
    }

    @Test
    void getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking(1, LocalDateTime.of(2024, 1, 1, 12, 10),
                LocalDateTime.of(2024, 1, 1, 13, 30), 1, 1);
        Booking booking2 = new Booking(2, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 13, 30), 1, 1);
        bookings.add(booking1);
        bookings.add(booking2);

        Mockito.when(mockBookingRep.getAllBookings()).thenReturn(bookings);

        List<Booking> retrievedBookings = bookingService.getAllBookings();
        assertEquals(bookings, retrievedBookings);
    }
}