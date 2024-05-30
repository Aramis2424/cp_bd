package model;

import exception.InvalidArgumentException;

import java.time.LocalDateTime;

public class Parking {
    private int id;
    private String address;
    private int totalParkingPlaces;
    private int freeParkingPlaces;
    private int tariffFID;

    public Parking() {
    }

    public Parking(int id, String address, int totalParkingPlaces, int freeParkingPlaces, int tariffFID) {
        this.id = id;
        this.address = address;
        this.totalParkingPlaces = totalParkingPlaces;
        this.freeParkingPlaces = freeParkingPlaces;
        this.tariffFID = tariffFID;
    }

    public static Parking createParkingModel(String address, int totalParkingPlaces, int tariffFID) {
        validateArguments(address, totalParkingPlaces, tariffFID);
        return new Parking(-1, address, totalParkingPlaces, totalParkingPlaces, tariffFID);
    }

    public static void validateArguments(String address, int totalParkingPlaces, int tariffFID) {
        if (address.isBlank())
            throw new InvalidArgumentException("Blank address");
        if (totalParkingPlaces < 0)
            throw new InvalidArgumentException("Negative total parking places");
        if (tariffFID < 0)
            throw new InvalidArgumentException("Negative tariffFID Id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalParkingSpace() {
        return totalParkingPlaces;
    }

    public void setTotalParkingSpace(int totalParkingSpace) {
        this.totalParkingPlaces = totalParkingSpace;
    }

    public int getFreeParkingSpace() {
        return freeParkingPlaces;
    }

    public void setFreeParkingSpace(int freeParkingSpace) {
        this.freeParkingPlaces = freeParkingSpace;
    }

    public int getTotalParkingPlaces() {
        return totalParkingPlaces;
    }

    public void setTotalParkingPlaces(int totalParkingPlaces) {
        this.totalParkingPlaces = totalParkingPlaces;
    }

    public int getFreeParkingPlaces() {
        return freeParkingPlaces;
    }

    public void setFreeParkingPlaces(int freeParkingPlaces) {
        this.freeParkingPlaces = freeParkingPlaces;
    }

    public int getTariffFID() {
        return tariffFID;
    }

    public void setTariffFID(int tariffFID) {
        this.tariffFID = tariffFID;
    }

}
