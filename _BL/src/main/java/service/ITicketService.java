package service;

import model.Ticket;

public interface ITicketService {
    public int openTicket(Ticket ticket);

    public void closeTicket(Ticket ticket);

    public Ticket getTicketById(int id);
}
