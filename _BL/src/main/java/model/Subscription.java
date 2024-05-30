package model;

import exception.InvalidArgumentException;

import java.time.Period;

public class Subscription {
    private int id;
    private String title;
    private int cost;
    private double discount;
    private Period subscriptionPeriod;
    private int graceHours;

    public Subscription() {
    }

    public Subscription(int id, String title, int cost, double discount, Period subscriptionPeriod, int graceHours) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.discount = discount;
        this.subscriptionPeriod = subscriptionPeriod;
        this.graceHours = graceHours;
    }

    public static Subscription createSubscriptionModel(String title, int cost, double discount,
                                                       Period subscriptionPeriod, int graceHours) {
        validateArguments(title, cost, discount, subscriptionPeriod, graceHours);
        return new Subscription(-1, title, cost, discount, subscriptionPeriod, graceHours);
    }

    public static void validateArguments(String title, int cost, double discount,
                                         Period subscriptionPeriod, int graceHours) {
        if (title.isBlank())
            throw new InvalidArgumentException("Blank title");
        if (cost < 0)
            throw new InvalidArgumentException("Negative cost");
        if (discount < 0 || discount >= 1)
            throw new InvalidArgumentException("Invalid discount value");
        if (subscriptionPeriod.isZero())
            throw new InvalidArgumentException("Zero duration");
        if (graceHours < 0)
            throw new InvalidArgumentException("Zero period");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
