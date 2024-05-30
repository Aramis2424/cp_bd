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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    private ITicketRepository mockTickerRep;
    @Mock
    private IAutoOwnerRepository mockAutoOwnerRep;
    @Mock
    private ISubscriptionRepository mockSubscriptionRep;
    @Mock
    private IParkingRepository mockParkingRep;
    @Mock
    private ITariffRepository mockTariffRep;
    @Mock
    private IBookingRepository mockBookingRep;
    @Mock
    private IAutoOwnerService mockAutoOwnerService;
    @Mock
    private IParkingService mockParkingService;

    @InjectMocks
    TicketService ticketService;

    @Test
    void getFreePlaces() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 62, 1);
        Parking parking = new Parking(62, "Dvoskiy 2/3", 50, 21, 42);
        Mockito.when(mockParkingRep.getParkingById(62)).thenReturn(parking);

        int actualFreePlacesCnt = ticketService.getFreePlaces(ticket);

        assertEquals(21, actualFreePlacesCnt);
    }

    @Test
    public void openTicket_NotFreePlaces() {
        Ticket newTicket = new Ticket(-1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 13, 30), 3, 1);

        Parking parking = new Parking(3, "asd", 10, 0, 5);
        Mockito.when(mockParkingRep.getParkingById(3)).thenReturn(parking);
        Mockito.when(mockBookingRep.getActiveBookingsByUser(1)).thenReturn(null);

        assertThrows(NotFreePlacesException.class, () -> {ticketService.openTicket(newTicket);});
    }
    @Test
    public void openTicket_FreePlaces() {
        Ticket newTicket = new Ticket(-1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 13, 30), 3, 1);
        Parking parking = new Parking(3, "asd", 10, 5, 5);
        Mockito.when(mockParkingRep.getParkingById(3)).thenReturn(parking);
        Mockito.when(mockTickerRep.insertTicket(newTicket)).thenReturn(1);
        Mockito.doNothing().when(mockParkingService).decFreePlaces(3);

        ticketService.openTicket(newTicket);

        assertEquals(1, newTicket.getId());
    }

    @Test
    public void getGraceHours() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 13, 30), 1, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        int expectedGraceHours = 2;
        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), expectedGraceHours);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        int actualGraceHours =  ticketService.getGraceHours(ticket);

        assertEquals(expectedGraceHours, actualGraceHours);
    }

    @Test
    public void getHours_NonEntireHour() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 1, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), 1);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        long expectedHours = 2;
        long actualHours = ticketService.getHours(ticket);

        assertEquals(expectedHours, actualHours);
    }

    @Test
    public void getHours_EntireHour() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 0), 1, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), 1);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        long expectedHours = 1;
        long actualHours = ticketService.getHours(ticket);

        assertEquals(expectedHours, actualHours);
    }

    @Test
    public void getCost() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2024, 1, 1, 12, 0),
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
        int actualCost = ticketService.getCost(ticket);
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void closeTicket() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 14, 30), 3, 1);

        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);

        Subscription subscription = new Subscription(2, "Gold", 20000,
                0.25, Period.ofDays(30), 1);
        Mockito.when(mockSubscriptionRep.getSubscriptionByID(2)).thenReturn(subscription);

        Parking parking = new Parking(3, "asd", 10, 5, 5);
        Mockito.when(mockParkingRep.getParkingById(3)).thenReturn(parking);
        Mockito.doNothing().when(mockParkingService).incFreePlaces(3);

        Tariff tariff = new Tariff(5, "zxc", 50, 100);
        Mockito.when(mockTariffRep.getTariffById(5)).thenReturn(tariff);

        ticketService.closeTicket(ticket);

        Mockito.verify(mockAutoOwnerService, Mockito.times(1)).debitAccount(1, 75);
        assertEquals(false, ticket.getActive());
    }

    @Test
    public void getTicketById() {
        Ticket expected = new Ticket(1, LocalDateTime.of(2024, 1, 1, 12, 0),
                LocalDateTime.of(2024, 1, 1, 13, 30), 1, 1);
        Mockito.when(mockTickerRep.getTicketById(1)).thenReturn(expected);

        Ticket actual = ticketService.getTicketById(1);
        assertEquals(expected, actual);
    }
}