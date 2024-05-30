package model;

import exception.InvalidArgumentException;

public class Tariff {
    private int id;
    private String title;
    private int costPerHour;
    private int costPerDay;

    public Tariff() {
    }

    public Tariff(int id, String title, int costPerHour, int costPerDay) {
        this.id = id;
        this.title = title;
        this.costPerHour = costPerHour;
        this.costPerDay = costPerDay;
    }

    public static Tariff createTariffModel(String title, int costPerHour, int costPerDay) {
        validateTariff(title, costPerHour, costPerDay);
        return new Tariff(-1, title, costPerHour, costPerDay);
    }

    public static void validateTariff(String title, int costPerHour, int costPerDay) {
        if (title.isBlank())
            throw new InvalidArgumentException("Blank title");
        if (costPerHour <= 0)
            throw new InvalidArgumentException("Negative cost per hour");
        if (costPerDay <= 0)
            throw new InvalidArgumentException("Negative cost per day");
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

    public int getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(int costPerHour) {
        this.costPerHour = costPerHour;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }
}
