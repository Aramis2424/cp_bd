package service;

import irepository.IAutoOwnerRepository;
import model.AutoOwner;

import exception.NotEnoughMoneyException;
import exception.UserAlreadyExistsException;
import model.Subscription;

import java.time.LocalDateTime;
import java.util.List;

public class AutoOwnerService implements IAutoOwnerService {
    private final IAutoOwnerRepository autoOwnerRep;

    public AutoOwnerService(IAutoOwnerRepository autoOwnerRep) {
        this.autoOwnerRep = autoOwnerRep;
    }

    @Override
    public Boolean signIn(String login, String password) {
        if (this.getAutoOwnerBySignInfo(login, password) != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    @Override
    public int signUp(AutoOwner autoOwner) {
        if (this.getAutoOwnerBySignInfo(autoOwner.getLogin(), autoOwner.getPassword()) != null)
            throw new UserAlreadyExistsException(String.format("User with login %s already exists",
                    autoOwner.getLogin()));
        int id = autoOwnerRep.insertAutoOwner(autoOwner);
        autoOwner.setId(id);
        return id;
    }

    @Override
    public void updateAutoOwner(AutoOwner owner) {
        autoOwnerRep.updateAutoOwner(owner);
    }

    @Override
    public void removeAutoOwner(int id) {
        autoOwnerRep.deleteAutoOwner(id);
    }

    @Override
    public void debitAccount(int id, int money) {
        AutoOwner autoOwner = this.getAutoOwnerByID(id);
        debitMoney(autoOwner, money);
        this.updateAutoOwner(autoOwner);
    }

    protected void debitMoney(AutoOwner autoOwner, int money) {
        int newAccount = autoOwner.getAccount() - money;
        if (newAccount < 0)
            throw new NotEnoughMoneyException(String.format("Current account: %d. Money to debit: %d",
                    autoOwner.getAccount(), money));
        autoOwner.setAccount(newAccount);
    }

    @Override
    public void topUpAccount(int id, int money) {
        AutoOwner autoOwner = this.getAutoOwnerByID(id);
        int newAccount = autoOwner.getAccount() + money;
        autoOwner.setAccount(newAccount);
        this.updateAutoOwner(autoOwner);
    }

    @Override
    public AutoOwner getAutoOwnerByID(int id) {
        return autoOwnerRep.getAutoOwnerByID(id);
    }

    @Override
    public AutoOwner getAutoOwnerBySignInfo(String login, String password) {
        return autoOwnerRep.getAutoOwnerBySignInfo(login, password);
    }

    @Override
    public List<AutoOwner> getAllAutoOwners() {
        return autoOwnerRep.getAllAutoOwners();
    }

    @Override
    public void updateSubscription(int autoOwnerId, Subscription subscription) {
        AutoOwner autoOwner = this.getAutoOwnerByID(autoOwnerId);

        debitMoney(autoOwner, subscription.getCost());
        autoOwner.setDateSubscriptionExpire(LocalDateTime.now().plus(subscription.getSubscriptionPeriod()));

        autoOwner.setSubscriptionFID(subscription.getId());
        this.updateAutoOwner(autoOwner);
    }
}
