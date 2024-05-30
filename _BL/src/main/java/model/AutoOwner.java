package model;

import java.time.LocalDateTime;
import exception.InvalidArgumentException;

public class AutoOwner {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int account;
    private int subscriptionFID;
    private LocalDateTime dateSubscriptionExpire;

    public AutoOwner() {
    }

    public AutoOwner(int id, String firstName, String lastName, String login, String password,
                     int account, int subscriptionFID, LocalDateTime dateSubscriptionExpire) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.account = account;
        this.subscriptionFID = subscriptionFID;
        this.dateSubscriptionExpire = dateSubscriptionExpire;
    }

    public static AutoOwner createAutoOwnerModel(String firstName, String lastName, String login, String password,
                                                 int account, int subscriptionFID,
                                                 LocalDateTime dateSubscriptionExpire) {
        validateArguments(firstName, lastName, login, password, account, subscriptionFID);
        return new AutoOwner(-1, firstName, lastName, login, password, account,
                subscriptionFID, dateSubscriptionExpire);
    }

    public static void validateArguments(String firstName, String lastName, String login, String password,
                                       int account, int subscriptionFID) {
        if (firstName.isBlank())
            throw new InvalidArgumentException("Blank first name");
        if (lastName.isBlank())
            throw new InvalidArgumentException("Blank last name");
        if (login.isBlank())
            throw new InvalidArgumentException("Blank login");
        if (password.isBlank())
            throw new InvalidArgumentException("Blank password");
        if (account < 0)
            throw new InvalidArgumentException("Negative account");
        if (subscriptionFID < 0)
            throw new InvalidArgumentException("Negative subscription id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getSubscriptionFID() {
        return subscriptionFID;
    }

    public void setSubscriptionFID(int subscriptionFID) {
        this.subscriptionFID = subscriptionFID;
    }

    public LocalDateTime getDateSubscriptionExpire() {
        return dateSubscriptionExpire;
    }

    public void setDateSubscriptionExpire(LocalDateTime dateSubscriptionExpire) {
        this.dateSubscriptionExpire = dateSubscriptionExpire;
    }
}
