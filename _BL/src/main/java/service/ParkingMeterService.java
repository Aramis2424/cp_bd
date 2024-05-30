package service;

import irepository.IParkingMeterRepository;
import model.ParkingMeter;

import java.util.List;

public class ParkingMeterService implements IParkingMeterService {
    private IParkingMeterRepository parkingMeterRep;

    public ParkingMeterService(IParkingMeterRepository parkingMeterRep) {
        this.parkingMeterRep = parkingMeterRep;
    }

    @Override
    public int createParkingMeter(ParkingMeter meter) {
        int id = parkingMeterRep.insertParkingMeter(meter);
        meter.setId(id);
        return id;
    }

    @Override
    public void updateParkingMeter(ParkingMeter meter) {
        parkingMeterRep.updateParkingMeter(meter);
    }

    @Override
    public void removeParkingMeter(int id) {
        parkingMeterRep.deleteParkingMeter(id);
    }

    @Override
    public ParkingMeter getParkingMeterByID(int id) {
        return parkingMeterRep.getParkingMeterById(id);
    }


}
