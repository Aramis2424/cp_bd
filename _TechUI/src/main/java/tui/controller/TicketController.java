package tui.controller;

import exception.NotEnoughMoneyException;
import exception.NotFreePlacesException;
import model.Ticket;
import repository.*;
import service.AutoOwnerService;
import service.BookingService;
import service.ParkingService;
import service.TicketService;
import tui.view.TicketView;

import java.time.LocalDateTime;

public class TicketController {
    private TicketView ticketView = new TicketView();
    private TicketRepository ticketRepository = new TicketRepository();
    private ParkingRepository parkingRepository = new ParkingRepository();
    private BookingRepository bookingRepository = new BookingRepository();
    private AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
    private SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    private TariffRepository tariffRepository = new TariffRepository();
    private AutoOwnerService autoOwnerService = new AutoOwnerService(autoOwnerRepository);
    private ParkingService parkingService = new ParkingService(parkingRepository);
    private BookingService bookingService = new BookingService(bookingRepository, parkingRepository,
            autoOwnerRepository, subscriptionRepository, tariffRepository, autoOwnerService, parkingService);
    private TicketService ticketService = new TicketService(ticketRepository, autoOwnerRepository,
            subscriptionRepository, parkingRepository, tariffRepository, bookingRepository,
            bookingService, parkingService, autoOwnerService);
    private int userId;
    private Ticket ticket;
    int ticketId;
    public void execute(int userId) {
        this.userId = userId;
        printTicketMenu();
    }

    private void printTicketMenu() {
        while (true) {
            ticketView.printTicketMenu();
            int choice = ticketView.getNumber("Ваш выбор: ");
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> openTicket();
                case 2 -> closeTicket();
            }
        }
    }

    private void openTicket() {
        ticket = Ticket.createTicketModel(1, userId);
        try {
            ticketId = ticketService.openTicket(ticket);
        }
        catch (NotFreePlacesException e) {
            ticketView.printNonSuccessOpeningTicket();
            ticket = null;
            return;
        }
        ticket = ticketService.getTicketById(ticketId);
        ticketView.printSuccessOpeningTicket();
    }

    private void closeTicket() {
        ticket.setFinishDate(LocalDateTime.now());
        try {
            ticketService.closeTicket(ticket);
        } catch (NotEnoughMoneyException e) {
            ticketView.printNonSuccessClosingTicket();
            return;
        }
        ticketView.printSuccessClosingTicket();
    }
}
