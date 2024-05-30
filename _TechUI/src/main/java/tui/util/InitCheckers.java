package tui.util;

import repository.*;
import service.*;

public class InitCheckers {
    private static final ParkingRepository parkingRepository = new ParkingRepository();
    private static final BookingRepository bookingRepository = new BookingRepository();
    private static final AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
    private static final SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    private static final TariffRepository tariffRepository = new TariffRepository();
    private static final AutoOwnerService autoOwnerService = new AutoOwnerService(autoOwnerRepository);
    private static final ParkingService parkingService = new ParkingService(parkingRepository);
    private static final SubscriptionService subscriptionService = new SubscriptionService(subscriptionRepository);

    private static final BookingService bookingService = new BookingService(bookingRepository, parkingRepository,
            autoOwnerRepository, subscriptionRepository, tariffRepository, autoOwnerService, parkingService);

    private static final BookingChecker bookingChecker = new BookingChecker(bookingService);
    private static final SubscriptionChecker subscriptionChecker =
            new SubscriptionChecker(autoOwnerService, subscriptionService);

    public static void init() {
        bookingChecker.initImmediatelyChecking();
        bookingChecker.initScheduleChecking();

        subscriptionChecker.initImmediatelyChecking();
        subscriptionChecker.initScheduleChecking();
    }

    public static void stop() {
        bookingChecker.stop();
        subscriptionChecker.stop();
    }
}
