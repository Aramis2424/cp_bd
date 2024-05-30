package service;

import model.AutoOwner;
import model.Subscription;

public interface ISubscriptionService {
    public int createSubscription(Subscription subscription);

    public void updateSubscription(Subscription subscription);

    public void removeSubscription(int id);

    public Subscription getSubscriptionById(int id);
}
