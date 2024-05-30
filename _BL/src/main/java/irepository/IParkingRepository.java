package irepository;

import model.Parking;

import java.util.List;

public interface IParkingRepository {
    public int insertParking(Parking parking);

    public void updateParking(Parking parking);

    public void deleteParking(int id);

    public List<Parking> getAllParkings();

    public Parking getParkingById(int parkingId);
    
    public int getFreePlaces(int parkingId);

    public int getTotalPlaces(int parkingId);
}
