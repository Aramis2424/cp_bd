package service;

import java.time.LocalDateTime;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.AutoOwner;

public class SubscriptionChecker {
    private static final int minimumHoursForGettingSubscription = 24;
    private final IAutoOwnerService service;
    private final ISubscriptionService subscriptionService;
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(2);

    class Task implements Runnable {
        @Override
        public void run() {
            List<AutoOwner> autoOwners = service.getAllAutoOwners();
            check(autoOwners);
        }
    }

    public SubscriptionChecker(IAutoOwnerService service, ISubscriptionService subscriptionService) {
        this.service = service;
        this.subscriptionService = subscriptionService;
    }

    private void check(List<AutoOwner> autoOwners) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (autoOwners == null)
            return;
        for (AutoOwner autoOwner : autoOwners) {
            if (autoOwner.getDateSubscriptionExpire().isBefore(currentTime)) {
                service.updateSubscription(autoOwner.getId(), subscriptionService.getSubscriptionById(1));
            }
        }
    }

    public static int getDelay() {
        LocalDateTime currentTime = LocalDateTime.now();
        return minimumHoursForGettingSubscription - currentTime.getHour();
    }

    public void initScheduleChecking() {
        Task task = new Task();
        scheduler.scheduleAtFixedRate(task, getDelay(), 1, TimeUnit.DAYS);
    }

    public void initImmediatelyChecking() {
        Task task = new Task();
        task.run();
    }

    public void stop() {
        scheduler.shutdown();
    }
}
