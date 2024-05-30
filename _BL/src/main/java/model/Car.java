package model;

import exception.InvalidArgumentException;

public class Car {
    private String number;
    private String model;
    private int autoOwnerID;

    public Car() {
    }

    public Car(String number, String model, int autoOwnerID) {
        this.number = number;
        this.model = model;
        this.autoOwnerID = autoOwnerID;
    }

    public static Car createCarModel(String number, String type, int autoOwnerID) {
        validateArguments(number, type);
        return new Car(number, type, autoOwnerID);
    }

    public static void validateArguments(String number, String type) {
        if (number.isBlank())
            throw new InvalidArgumentException("Blank car number");
        if (type.isBlank())
            throw new InvalidArgumentException("Blank car type");
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAutoOwnerID() {
        return autoOwnerID;
    }

    public void setAutoOwnerID(int autoOwnerID) {
        this.autoOwnerID = autoOwnerID;
    }
}
