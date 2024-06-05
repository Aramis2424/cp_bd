SubscriptionRepository sr = new SubscriptionRepository();
SubscriptionService sser = new SubscriptionService(sr);
Subscription s1 = Subscription.createSubscriptionModel("Standard", 0, 0, Period.of(1000, 0,0 ), 0);
Subscription s2 = Subscription.createSubscriptionModel("Gold", 1000, 0.2, Period.of(1, 0,0 ), 1);
Subscription s3 = Subscription.createSubscriptionModel("Premium", 5000, 0.3, Period.of(0, 6,0 ), 2);
sser.createSubscription(s1);
sser.createSubscription(s2);
sser.createSubscription(s3);