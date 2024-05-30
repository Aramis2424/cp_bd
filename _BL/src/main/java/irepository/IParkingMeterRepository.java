package irepository;

import model.ParkingMeter;

public interface IParkingMeterRepository {
    public int insertParkingMeter(ParkingMeter parkingMeter);

    public void updateParkingMeter(ParkingMeter parkingMeter);

    public void deleteParkingMeter(int id);

    public ParkingMeter getParkingMeterById(int parkingMeterId);
}
