package repository;

import irepository.ISubscriptionRepository;
import model.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionRepositoryTest {
    private ISubscriptionRepository subscriptionRepository;

    @BeforeEach
    void setUp() {
        subscriptionRepository = new SubscriptionRepository();
    }

    @Test
    void insertSubscription() {
        Subscription subscription = Subscription
                .createSubscriptionModel("Testing", 100, 0.9, Period.of(1, 0, 0), 5);
        int id = subscriptionRepository.insertSubscription(subscription);

        Subscription newEmployee = subscriptionRepository.getSubscriptionByID(id);

        assertEquals("Testing", newEmployee.getTitle());
    }

    @Test
    void updateSubscription() {
        Subscription subscription = Subscription
                .createSubscriptionModel("Testing", 100, 0.9, Period.of(1, 0, 0), 5);
        int id = subscriptionRepository.insertSubscription(subscription);

        Subscription newSubscription = subscriptionRepository.getSubscriptionByID(id);
        newSubscription.setCost(999);
        subscriptionRepository.updateSubscription(newSubscription);

        newSubscription = subscriptionRepository.getSubscriptionByID(id);
        assertEquals("Testing", newSubscription.getTitle());

        assertEquals(999, newSubscription.getCost());
    }

    @Test
    void deleteSubscription() {
        Subscription subscription = Subscription
                .createSubscriptionModel("Testing", 100, 0.9, Period.of(1, 0, 0), 5);
        int id = subscriptionRepository.insertSubscription(subscription);

        subscriptionRepository.deleteSubscription(id);

        Subscription newSubscription = subscriptionRepository.getSubscriptionByID(id);
        assertNull(newSubscription);
    }

    @Test
    void getSubscriptionByID() {
        Subscription subscription = Subscription
                .createSubscriptionModel("Testing", 100, 0.9, Period.of(1, 0, 0), 5);
        int id = subscriptionRepository.insertSubscription(subscription);

        Subscription newSubscription = subscriptionRepository.getSubscriptionByID(id);

        assertEquals("Testing", newSubscription.getTitle());
    }
}