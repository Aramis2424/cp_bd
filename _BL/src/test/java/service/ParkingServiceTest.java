package service;

import irepository.IParkingRepository;
import model.Parking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {
    @Mock
    private IParkingRepository mockRep;

    @InjectMocks
    private ParkingService service;

    @Test
    public void createParking(){
        Parking newParking = new Parking(1, "Moscow 21", 10, 5, 3);
        Mockito.when(mockRep.insertParking(newParking)).thenReturn(1);

        service.createParking(newParking);

        Mockito.verify(mockRep).insertParking(newParking);
        assertEquals(1, newParking.getId());
    }

    @Test
    public void updateParking(){
        Parking updatingParking = new Parking(1, "Moscow 21", 10, 5, 3);
        Mockito.doNothing().when(mockRep).updateParking(updatingParking);

        service.updateParking(updatingParking);
        Mockito.verify(mockRep).updateParking(updatingParking);
    }

    @Test
    public void getALlParking(){
        List<Parking> parkings = new ArrayList<>();
        parkings.add(new Parking(1, "Bavarskay 32", 13, 4, 2));
        parkings.add(new Parking(2, "Korona 24", 73, 32, 4));
        parkings.add(new Parking(3, "Virus 32", 32, 22, 3));

        Mockito.when(mockRep.getAllParkings()).thenReturn(parkings);

        List<Parking> retrievedParking = service.getAllParkings();
        assertEquals(parkings, retrievedParking);
    }

    @Test
    public void getParkingById(){
        Parking expected = new Parking(1, "Dvoskiy St3", 32, 10, 1);
        Mockito.when(mockRep.getParkingById(1)).thenReturn(expected);

        Parking actual = service.getParkingByID(1);
        assertEquals(expected, actual);
    }

    @Test
    public void getTotalPlacesCnt(){
        Parking parking = new Parking(62, "Dvoskiy 2/3", 50, 21, 42);
        int expected = parking.getTotalParkingPlaces();
        Mockito.when(mockRep.getTotalPlaces(62)).thenReturn(expected);

        int actual = service.getTotalPlacesCnt(62);
        assertEquals(expected, actual);
    }

    @Test
    public void getFreePlacesCnt(){
        Parking parking = new Parking(62, "Dvoskiy 2/3", 50, 21, 42);
        int expected = parking.getFreeParkingPlaces();
        Mockito.when(mockRep.getFreePlaces(62)).thenReturn(expected);

        int actual = service.getFreePlacesCnt(62);
        assertEquals(expected, actual);
    }

    @Test
    void removeParking() {
        int removingParkingId = 1;
        Mockito.doNothing().when(mockRep).deleteParking(removingParkingId);

        service.removeParking(removingParkingId);

        Mockito.verify(mockRep).deleteParking(removingParkingId);
    }
}
