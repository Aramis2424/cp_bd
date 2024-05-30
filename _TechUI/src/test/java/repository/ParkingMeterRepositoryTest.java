package repository;

import irepository.IParkingMeterRepository;
import model.ParkingMeter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingMeterRepositoryTest {
    private IParkingMeterRepository parkingMeterRepository;

    @BeforeEach
    void setUp() {
        parkingMeterRepository = new ParkingMeterRepository();
    }

    @Test
    void insertParkingMeter() {
        ParkingMeter parkingMeter = ParkingMeter.createParkingMeterModel(1, 1);
        int id = parkingMeterRepository.insertParkingMeter(parkingMeter);

        ParkingMeter newParkingMeter = parkingMeterRepository.getParkingMeterById(id);

        assertEquals(1, newParkingMeter.getGateNumber());
    }

    @Test
    void updateParkingMeter() {
        ParkingMeter parkingMeter = ParkingMeter.createParkingMeterModel(1, 1);
        int id = parkingMeterRepository.insertParkingMeter(parkingMeter);

        ParkingMeter newParkingMeter = parkingMeterRepository.getParkingMeterById(id);
        newParkingMeter.setGateNumber(5);
        parkingMeterRepository.updateParkingMeter(newParkingMeter);

        newParkingMeter = parkingMeterRepository.getParkingMeterById(id);
        assertEquals(5, newParkingMeter.getGateNumber());
    }

    @Test
    void deleteParkingMeter() {
        ParkingMeter parkingMeter = ParkingMeter.createParkingMeterModel(1, 1);
        int id = parkingMeterRepository.insertParkingMeter(parkingMeter);

        parkingMeterRepository.deleteParkingMeter(id);

        ParkingMeter newParkingMeter = parkingMeterRepository.getParkingMeterById(id);
        assertNull(newParkingMeter);
    }

    @Test
    void getParkingMeterById() {
        ParkingMeter parkingMeter = ParkingMeter.createParkingMeterModel(1, 1);
        int id = parkingMeterRepository.insertParkingMeter(parkingMeter);

        ParkingMeter newParkingMeter = parkingMeterRepository.getParkingMeterById(id);

        assertEquals(1, newParkingMeter.getGateNumber());
    }
}