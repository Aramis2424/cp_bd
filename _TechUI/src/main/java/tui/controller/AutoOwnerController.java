package tui.controller;

import model.AutoOwner;
import repository.AutoOwnerRepository;
import service.AutoOwnerService;
import tui.view.AutoOwnerView;

import java.time.LocalDateTime;

public class AutoOwnerController {
    private AutoOwnerView autoOwnerView = new AutoOwnerView();
    private AutoOwner autoOwner;
    private AutoOwnerRepository autoOwnerRepository = new AutoOwnerRepository();
    private AutoOwnerService autoOwnerService = new AutoOwnerService(autoOwnerRepository);

    private CarController carController = new CarController();
    private SubscriptionController subscriptionController = new SubscriptionController();

    public AutoOwner getAutoOwner() {
        return autoOwner;
    }

    public void execute() {
        refreshUser();
        printUserMenu();
    }

    public void printUserMenu() {
        while (true) {
            autoOwnerView.printUserMenu();
            int choice = autoOwnerView.getNumber("Ваш выбор: ");
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> printAccount();
                case 2 -> topUpAccount();
                case 3 -> carController.execute(autoOwner.getId());
                case 4 -> {
                    subscriptionController.execute(autoOwner.getId(), autoOwnerService);
                    refreshUser();
                }
            }
        }
    }

    private void refreshUser() {
        autoOwner = autoOwnerService.getAutoOwnerByID(autoOwner.getId());
    }

    private void topUpAccount() {
        printAccount();
        int money = autoOwnerView.getNumber("Введите сумму пополнения: ");
        autoOwnerService.topUpAccount(autoOwner.getId(), money);
        refreshUser();
        autoOwnerView.printSuccessTopUpAccount();
    }

    public void printAccount() {
        int account = autoOwner.getAccount();
        autoOwnerView.printAccount(account);
    }

    public boolean signIn() {
        String login = autoOwnerView.getString("Введите ваш логин: ");
        String password = autoOwnerView.getString("Введите ваш пароль: ");
        boolean res = autoOwnerService.signIn(login, password);
        if (res)
            autoOwnerView.printSuccessSignIn();
        else
            autoOwnerView.printNonSuccessSignIn();
        autoOwner = autoOwnerService.getAutoOwnerBySignInfo(login, password);
        return res;
    }

    public void signUp() {
        String name = autoOwnerView.getString("Введите ваше имя: ");
        String surname = autoOwnerView.getString("Введите вашу фамилию: ");
        String login = autoOwnerView.getString("Введите ваш логин: ");
        String password = autoOwnerView.getString("Введите ваш пароль: ");
        autoOwner = AutoOwner.createAutoOwnerModel(name, surname, login, password,
                0, 1, LocalDateTime.of(2999, 1, 1, 0, 0));
        autoOwnerService.signUp(autoOwner);
        autoOwnerView.printSuccessSignUp();
    }
}
