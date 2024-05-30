package repository;

import irepository.ITicketRepository;
import model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    private ITicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        ticketRepository = new TicketRepository();
    }

    @Test
    void insertTicket() {
        Ticket ticket = Ticket.createTicketModel(1, 1);
        int id = ticketRepository.insertTicket(ticket);

        Ticket newBooking = ticketRepository.getTicketById(id);

        assertEquals(1, newBooking.getAutoOwnerFID());
        assertEquals(1, newBooking.getParkingFID());
    }

    @Test
    void updateTicket() {
        Ticket booking = Ticket.
                createTicketModel(1, 1);
        int id = ticketRepository.insertTicket(booking);

        Ticket newBooking = ticketRepository.getTicketById(id);
        assertTrue(newBooking.getActive());
        newBooking.setActive(false);
        ticketRepository.updateTicket(newBooking);

        newBooking = ticketRepository.getTicketById(id);
        assertEquals(1, newBooking.getAutoOwnerFID());
        assertEquals(1, newBooking.getParkingFID());
        assertFalse(newBooking.getActive());
    }

    @Test
    void getTicketById() {
        Ticket booking = Ticket.
                createTicketModel(1, 1);
        int id = ticketRepository.insertTicket(booking);

        Ticket newBooking = ticketRepository.getTicketById(id);

        assertEquals(1, newBooking.getAutoOwnerFID());
        assertEquals(1, newBooking.getParkingFID());
    }
}