package service;

import java.util.List;

import model.Parking;
import model.Tariff;

public interface IParkingService {
    public int createParking(Parking parking);

    public void updateParking(Parking parking);

    public void removeParking(int id);

    public List<Parking> getAllParkings();

    public Parking getParkingByID(int id);

    public int getTotalPlacesCnt(int id);

    public int getFreePlacesCnt(int id);

    public void incFreePlaces(int parkingId);

    public void decFreePlaces(int parkingId);
}
