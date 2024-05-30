package service;

import static org.junit.jupiter.api.Assertions.*;

import irepository.IParkingMeterRepository;
import model.ParkingMeter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ParkingMeterServiceTest {
    @Mock
    private IParkingMeterRepository mockRep;

    @InjectMocks
    private ParkingMeterService service;

    @Test
    public void createParkingMeter(){
        ParkingMeter newParkingMeter = new ParkingMeter(21, 123, 323);
        Mockito.when(mockRep.insertParkingMeter(newParkingMeter)).thenReturn(21);

        service.createParkingMeter(newParkingMeter);
    }

    @Test
    public void updatedParkingMeter(){
        ParkingMeter updatingParkingMeter = new ParkingMeter(21, 123, 323);
        Mockito.doNothing().when(mockRep).updateParkingMeter(updatingParkingMeter);

        service.updateParkingMeter(updatingParkingMeter);
        Mockito.verify(mockRep).updateParkingMeter(updatingParkingMeter);
    }

    @Test
    public void remoParkingMeter(){
        int removingParkingMeterId = 21;
        Mockito.doNothing().when(mockRep).deleteParkingMeter(removingParkingMeterId);

        service.removeParkingMeter(removingParkingMeterId);

        Mockito.verify(mockRep).deleteParkingMeter(removingParkingMeterId);
    }

    @Test
    public void getParkingMeterById(){
        ParkingMeter expected = new ParkingMeter(32, 312, 343);
        Mockito.when(mockRep.getParkingMeterById(32)).thenReturn(expected);

        ParkingMeter actual = service.getParkingMeterByID(32);
        assertEquals(expected, actual);
    }
}
