package service;

import java.util.List;

import model.AutoOwner;
import model.Subscription;

public interface IAutoOwnerService {
    public Boolean signIn(String login, String password);

    public int signUp(AutoOwner autoOwner);

    public void updateAutoOwner(AutoOwner owner);

    public void removeAutoOwner(int id);

    public void debitAccount(int id, int money);

    public void topUpAccount(int id, int money);

    public AutoOwner getAutoOwnerByID(int id);

    public AutoOwner getAutoOwnerBySignInfo(String login, String password);

    public List<AutoOwner> getAllAutoOwners();

    public void updateSubscription(int autoOwnerId, Subscription subscription);
}
