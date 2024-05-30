package entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="auto_owners")
public class AutoOwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_ownerID")
    private int autoOwnerID;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login")
    private String login;
    @Column(name = "password_")
    private String password;
    @Column(name = "account")
    private int account;
    @Column(name = "subscriptionFID")
    private int subscriptionFID;
    @Column(name = "date_subscription_expire")
    private LocalDateTime dateSubscriptionExpire;

    @ManyToMany(mappedBy = "autoOwnerEntities")
    private Set<CarEntity> carEntities;

    public int getAutoOwnerID() {
        return autoOwnerID;
    }

    public void setAutoOwnerID(int autoOwnerID) {
        this.autoOwnerID = autoOwnerID;
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
