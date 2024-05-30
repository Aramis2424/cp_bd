package service;

import java.time.Duration;
import java.util.List;

import exception.NotFreePlacesException;
import irepository.*;

import model.*;

public class TicketService implements ITicketService {

    private ITicketRepository ticketRep;
    private IAutoOwnerRepository autoOwnerRep;
    private ISubscriptionRepository subscriptionRep;
    private IParkingRepository parkingRep;
    private ITariffRepository tariffRep;
    private IBookingRepository bookingRep;
    private IBookingService bookingService;
    private IParkingService parkingService;
    private IAutoOwnerService autoOwnerService;

    protected int getFreePlaces(Ticket ticket) {
        return parkingRep.getParkingById(ticket.getParkingFID()).getFreeParkingPlaces();
    }

    public TicketService(ITicketRepository ticketRep, IAutoOwnerRepository autoOwnerRep,
                         ISubscriptionRepository subscriptionRep, IParkingRepository parkingRep,
                         ITariffRepository tariffRep, IBookingRepository bookingRep, IBookingService bookingService,
                         IParkingService parkingService, IAutoOwnerService autoOwnerService) {
        this.ticketRep = ticketRep;
        this.autoOwnerRep = autoOwnerRep;
        this.subscriptionRep = subscriptionRep;
        this.parkingRep = parkingRep;
        this.tariffRep = tariffRep;
        this.bookingRep = bookingRep;
        this.bookingService = bookingService;
        this.parkingService = parkingService;
        this.autoOwnerService = autoOwnerService;
    }

    protected Booking getExistingBookingForParking(int userId, int parkingId) {
        List<Booking> bookings = bookingRep.getActiveBookingsByUser(userId);
        if (bookings == null)
            return null;
        for (Booking booking : bookings)
            if (booking.getParkingFID() == parkingId)
                return booking;
        return null;
    }

    @Override
    public int openTicket(Ticket ticket) {
        if (this.getFreePlaces(ticket) == 0) {
            Booking currentBooking = this.getExistingBookingForParking(
                    ticket.getAutoOwnerFID(), ticket.getParkingFID());
            if (currentBooking == null)
                throw new NotFreePlacesException();
            else
                bookingService.closeBooking(currentBooking);
        }
        parkingService.decFreePlaces(ticket.getParkingFID());
        int id = ticketRep.insertTicket(ticket);
        ticket.setId(id);
        return id;
    }

    protected int getGraceHours(Ticket ticket) {
        AutoOwner autoOwner = autoOwnerRep.getAutoOwnerByID(ticket.getAutoOwnerFID());
        Subscription subscription = subscriptionRep.getSubscriptionByID(autoOwner.getSubscriptionFID());
        return subscription.getGraceHours();
    }

    protected long getHours(Ticket ticket) {
        Duration duration = Duration.between(ticket.getStartDate(), ticket.getFinishDate());

        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0)
            hours++;
        hours -= getGraceHours(ticket);
        if (hours < 0)
            hours = 0;

        return hours;
    }

    protected int getCost(Ticket ticket) {
        long hours = getHours(ticket);

        Parking parking = parkingRep.getParkingById(ticket.getParkingFID());
        Tariff tariff = tariffRep.getTariffById(parking.getTariffFID());
        AutoOwner autoOwner = autoOwnerRep.getAutoOwnerByID(ticket.getAutoOwnerFID());
        Subscription subscription = subscriptionRep.getSubscriptionByID(autoOwner.getSubscriptionFID());

        return (int) Math.ceil(hours * tariff.getCostPerHour() * (1 - subscription.getDiscount()));
    }

    @Override
    public void closeTicket(Ticket ticket) {
        int cost = getCost(ticket);

        AutoOwner autoOwner = autoOwnerRep.getAutoOwnerByID(ticket.getAutoOwnerFID());
        autoOwnerService.debitAccount(autoOwner.getId(), cost);

        parkingService.incFreePlaces(ticket.getParkingFID());
        ticket.setActive(false);
        ticketRep.updateTicket(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketRep.getTicketById(id);
    }
}
