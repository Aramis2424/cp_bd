package service;

import irepository.ISubscriptionRepository;
import model.Subscription;

public class SubscriptionService implements ISubscriptionService {
    private ISubscriptionRepository subscriptionRep;

    public SubscriptionService(ISubscriptionRepository subscriptionRep) {
        this.subscriptionRep = subscriptionRep;
    }

    @Override
    public int createSubscription(Subscription subscription) {
        int id = subscriptionRep.insertSubscription(subscription);
        subscription.setId(id);
        return id;
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        subscriptionRep.updateSubscription(subscription);
    }

    @Override
    public void removeSubscription(int id) {
        subscriptionRep.deleteSubscription(id);
    }

    @Override
    public Subscription getSubscriptionById(int id) {
        return subscriptionRep.getSubscriptionByID(id);
    }
}
