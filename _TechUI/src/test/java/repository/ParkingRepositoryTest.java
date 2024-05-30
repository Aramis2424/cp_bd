package repository;

import irepository.IParkingRepository;
import model.Parking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingRepositoryTest {
    private IParkingRepository parkingRepository;

    @BeforeEach
    void setUp() {
        parkingRepository = new ParkingRepository();
    }

    @Test
    void insertParking() {
        Parking parking = Parking.createParkingModel("Belarusskaya, 9", 10, 1);
        int id = parkingRepository.insertParking(parking);

        Parking newParking = parkingRepository.getParkingById(id);

        assertEquals("Belarusskaya, 9", newParking.getAddress());
        assertEquals(10, newParking.getFreeParkingPlaces());
    }

    @Test
    void updateParking() {
        Parking parking = Parking.createParkingModel("Belarusskaya, 9", 10, 1);
        int id = parkingRepository.insertParking(parking);

        Parking newParking = parkingRepository.getParkingById(id);
        newParking.setFreeParkingSpace(newParking.getTotalParkingSpace() - 1);
        parkingRepository.updateParking(newParking);

        newParking = parkingRepository.getParkingById(id);
        assertEquals("Belarusskaya, 9", newParking.getAddress());
        assertEquals(9, newParking.getFreeParkingPlaces());
    }

    @Test
    void deleteParking() {
        Parking parking = Parking.createParkingModel("Belarusskaya, 9", 10, 1);
        int id = parkingRepository.insertParking(parking);

        parkingRepository.deleteParking(id);
        Parking newParking = parkingRepository.getParkingById(id);

        assertNull(newParking);
    }

    @Test
    void getAllParkings() {
        List<Parking> parkingList = new ArrayList<>(3);
        parkingList.add(Parking.createParkingModel("Aaa", 10, 1));
        parkingList.add(Parking.createParkingModel("Bbb", 15, 2));
        parkingList.add(Parking.createParkingModel("Ccc", 100, 1));
        for (Parking parking : parkingList)
            parkingRepository.insertParking(parking);

        List<Parking> parkings = parkingRepository.getAllParkings();

        assertNotNull(parkings);
        assertTrue(parkings.size() >= 3);
    }

    @Test
    void getParkingById() {
        Parking parking = Parking.createParkingModel("Belarusskaya, 9", 10, 1);
        int id = parkingRepository.insertParking(parking);

        Parking newParking = parkingRepository.getParkingById(id);

        assertEquals("Belarusskaya, 9", newParking.getAddress());
        assertEquals(10, newParking.getFreeParkingPlaces());
    }

    @Test
    void getFreePlaces() {
        Parking parking = Parking.createParkingModel("Belarusskaya, 9", 10, 1);
        int id = parkingRepository.insertParking(parking);

        int cnt = parkingRepository.getFreePlaces(id);

        assertEquals(10, cnt);
    }

    @Test
    void getTotalPlaces() {
        Parking parking = Parking.createParkingModel("Belarusskaya, 9", 10, 1);
        int id = parkingRepository.insertParking(parking);

        int cnt = parkingRepository.getTotalPlaces(id);

        assertEquals(10, cnt);
    }
}