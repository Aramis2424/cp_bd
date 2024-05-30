package service;

import irepository.*;
import model.*;
import exception.NotFreePlacesException;

import java.time.Duration;
import java.util.List;

public class BookingService implements IBookingService {
    private IBookingRepository bookingRep;
    private IParkingRepository parkingRep;
    private IAutoOwnerRepository autoOwnerRep;
    private ISubscriptionRepository subscriptionRep;
    private ITariffRepository tariffRep;
    private IParkingService parkingService;
    private IAutoOwnerService autoOwnerService;

    public BookingService(IBookingRepository bookingRep, IParkingRepository parkingRep,
                          IAutoOwnerRepository autoOwnerRep, ISubscriptionRepository subscriptionRep,
                          ITariffRepository tariffRep, IAutoOwnerService autoOwnerService,
                          IParkingService parkingService) {
        this.bookingRep = bookingRep;
        this.parkingRep = parkingRep;
        this.autoOwnerRep = autoOwnerRep;
        this.subscriptionRep = subscriptionRep;
        this.tariffRep = tariffRep;
        this.autoOwnerService = autoOwnerService;
        this.parkingService = parkingService;
    }

    protected int getFreePlaces(Booking booking) {
        return parkingRep.getParkingById(booking.getParkingFID()).getFreeParkingPlaces();
    }

    protected int getGraceHours(Booking booking) {
        AutoOwner autoOwner = autoOwnerRep.getAutoOwnerByID(booking.getAutoOwnerFID());
        Subscription subscription = subscriptionRep.getSubscriptionByID(autoOwner.getSubscriptionFID());
        return subscription.getGraceHours();
    }

    protected long getHours(Booking booking) {
        Duration duration = Duration.between(booking.getStartDate(), booking.getFinishDate());

        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0)
            hours++;
        hours -= getGraceHours(booking);

        return hours;
    }

    protected int getCost(Booking booking) {
        long hours = getHours(booking);

        Parking parking = parkingRep.getParkingById(booking.getParkingFID());
        Tariff tariff = tariffRep.getTariffById(parking.getTariffFID());
        AutoOwner autoOwner = autoOwnerRep.getAutoOwnerByID(booking.getAutoOwnerFID());
        Subscription subscription = subscriptionRep.getSubscriptionByID(autoOwner.getSubscriptionFID());

        return (int) Math.ceil(hours * tariff.getCostPerHour() * (1 - subscription.getDiscount()));
    }

    @Override
    public int startBooking(Booking booking) {
        if (getFreePlaces(booking) == 0)
            throw new NotFreePlacesException();

        int cost = getCost(booking);
        AutoOwner autoOwner = autoOwnerRep.getAutoOwnerByID(booking.getAutoOwnerFID());
        autoOwnerService.debitAccount(autoOwner.getId(), cost);

        parkingService.decFreePlaces(booking.getParkingFID());
        int id = bookingRep.insertBooking(booking);
        booking.setId(id);
        return id;
    }

    @Override
    public void closeBooking(Booking booking) {
        parkingService.incFreePlaces(booking.getParkingFID());
        booking.setActive(Boolean.FALSE);
        bookingRep.updateBooking(booking);
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRep.getBookingById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRep.getAllBookings();
    }

    @Override
    public List<Booking> getActiveBookingsByUser(int userId) {
        return bookingRep.getActiveBookingsByUser(userId);
    }
}
