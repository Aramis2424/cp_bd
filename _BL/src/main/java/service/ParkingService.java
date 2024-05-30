package service;

import irepository.IParkingRepository;
import model.Parking;

import java.util.List;

public class ParkingService implements IParkingService {

    private IParkingRepository parkingRep;

    public ParkingService(IParkingRepository parkingRep) {
        this.parkingRep = parkingRep;
    }

    @Override
    public int createParking(Parking parking) {
        int id = parkingRep.insertParking(parking);
        parking.setId(id);
        return id;
    }

    @Override
    public void updateParking(Parking parking) {
        parkingRep.updateParking(parking);
    }

    @Override
    public void removeParking(int id) {
        parkingRep.deleteParking(id);
    }

    @Override
    public List<Parking> getAllParkings() {
        return parkingRep.getAllParkings();
    }

    @Override
    public Parking getParkingByID(int id) {
        return parkingRep.getParkingById(id);
    }

    @Override
    public int getTotalPlacesCnt(int id) {
        return parkingRep.getTotalPlaces(id);
    }

    @Override
    public int getFreePlacesCnt(int id) {
        return parkingRep.getFreePlaces(id);
    }

    @Override
    public void incFreePlaces(int parkingId) {
        Parking parking = this.getParkingByID(parkingId);
        parking.setFreeParkingSpace(parking.getFreeParkingPlaces() + 1);
        this.updateParking(parking);
    }

    @Override
    public void decFreePlaces(int parkingId) {
        Parking parking = this.getParkingByID(parkingId);
        parking.setFreeParkingSpace(parking.getFreeParkingPlaces() - 1);
        this.updateParking(parking);
    }
}
