package entity;

import java.time.Period;
import javax.persistence.*;

@Entity
@Table(name="subscriptions")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptionID")
    private int subscriptionID;
    @Column(name = "title")
    private String title;
    @Column(name = "cost_")
    private int cost;
    @Column(name = "discount")
    private double discount;
    @Column(name = "period_")
    private Period subscriptionPeriod;
    @Column(name = "grace_hours")
    private int graceHours;

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Period getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(Period subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public int getGraceHours() {
        return graceHours;
    }

    public void setGraceHours(int graceHours) {
        this.graceHours = graceHours;
    }
}
