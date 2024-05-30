package model;

import exception.InvalidArgumentException;

public class ParkingMeter {
    private int id;
    private int gateNumber;
    private int parkingFID;

    public ParkingMeter() {
    }

    public ParkingMeter(int id, int gateNumber, int parkingFID) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.parkingFID = parkingFID;
    }

    public static ParkingMeter createParkingMeterModel(int gateNumber, int parkingFID) {
        validateTariff(gateNumber, parkingFID);
        return new ParkingMeter(-1, gateNumber, parkingFID);
    }

    public static void validateTariff(int gateNumber, int parkingFID) {
        if (gateNumber <= 0)
            throw new InvalidArgumentException("Negative gate number");
        if (parkingFID <= 0)
            throw new InvalidArgumentException("Negative parking id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public int getParkingFID() {
        return parkingFID;
    }

    public void setParkingFID(int parkingFID) {
        this.parkingFID = parkingFID;
    }
}
