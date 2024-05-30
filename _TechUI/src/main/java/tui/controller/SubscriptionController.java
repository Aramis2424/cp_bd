package tui.controller;

import exception.NotEnoughMoneyException;
import model.Subscription;
import repository.SubscriptionRepository;
import service.AutoOwnerService;
import service.SubscriptionService;
import tui.view.SubscriptionView;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionController {
    SubscriptionView subscriptionView = new SubscriptionView();
    private int userId;
    private AutoOwnerService autoOwnerService;
    private Subscription subscription;
    private SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    private SubscriptionService subscriptionService = new SubscriptionService(subscriptionRepository);

    public void execute(int userId, AutoOwnerService autoOwnerService) {
        this.userId = userId;
        this.autoOwnerService = autoOwnerService;
        refreshSubscription();
        printSubscriptionMenu();
    }

    private void printSubscriptionMenu() {
        while (true) {
            subscriptionView.printSubscriptionMenu();
            int choice = subscriptionView.getNumber("Ваш выбор: ");
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> printCurrentSubscription();
                case 2 -> printAllSubscriptions();
                case 3 -> changeSubscription();
            }
        }
    }

    private void changeSubscription() {
        printAllSubscriptions();
        int choice = subscriptionView.getNumber("Выберите подписку, которую хотите купить: ");
        try {
            autoOwnerService.updateSubscription(userId, subscriptionService.getSubscriptionById(choice));
        } catch (NotEnoughMoneyException e) {
            subscriptionView.printNonSuccessNewSubscription();
            return;
        }
        refreshSubscription();
        subscriptionView.printSuccessNewSubscription();
    }

    private void printAllSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>(3);
        subscriptions.add(subscriptionService.getSubscriptionById(1));
        subscriptions.add(subscriptionService.getSubscriptionById(2));
        subscriptions.add(subscriptionService.getSubscriptionById(3));
        int i = 1;
        for (Subscription s : subscriptions)
            subscriptionView.printAllSubscriptions(s, i++);
    }

    private void printCurrentSubscription() {
        subscriptionView.printCurrentSubscription(subscription,
                autoOwnerService.getAutoOwnerByID(userId).getDateSubscriptionExpire());
    }

    private void refreshSubscription() {
        subscription = subscriptionService
                .getSubscriptionById(autoOwnerService.getAutoOwnerByID(userId).getSubscriptionFID());
    }
}
