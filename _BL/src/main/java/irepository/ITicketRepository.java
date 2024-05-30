package irepository;

import model.Ticket;

public interface ITicketRepository {
    int insertTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    Ticket getTicketById(int id);
}
