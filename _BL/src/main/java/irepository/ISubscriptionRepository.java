package irepository;

import model.Subscription;

public interface ISubscriptionRepository {
    public int insertSubscription(Subscription subscription);

    public void updateSubscription(Subscription subscription);

    public void deleteSubscription(int subscriptionId);

    public Subscription getSubscriptionByID(int subscriptionId);
}
