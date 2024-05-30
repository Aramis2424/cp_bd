package service;

import java.util.List;

import model.ParkingMeter;

public interface IParkingMeterService {
    public int createParkingMeter(ParkingMeter meter);

    public void updateParkingMeter(ParkingMeter meter);

    public void removeParkingMeter(int id);

    public ParkingMeter getParkingMeterByID(int id);
}
