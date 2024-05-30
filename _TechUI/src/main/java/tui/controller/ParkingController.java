package tui.controller;

import model.Parking;
import repository.ParkingRepository;
import repository.TariffRepository;
import service.ParkingService;
import service.TariffService;
import service.TicketService;
import tui.view.ParkingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingController {
    private List<Parking> parkings = new ArrayList<>();
    private ParkingView parkingView = new ParkingView();
    private ParkingRepository parkingRepository = new ParkingRepository();
    private ParkingService parkingService = new ParkingService(parkingRepository);
    private TariffRepository tariffRepository = new TariffRepository();
    private TariffService tariffService = new TariffService(tariffRepository);

    public void execute() {
        printAllParkings();
    }

    public void printAllParkings() {
        parkings = parkingService.getAllParkings();
        Map<Parking, String> parkingsMap = new HashMap<>();
        for (Parking parking : parkings)
            parkingsMap.put(parking, tariffService.getTariffByID(parking.getTariffFID()).getTitle());
        parkingView.printAllParkings(parkingsMap);
    }

    public Map<Integer, Integer> makeChoiceParkings() {
        parkings = parkingService.getAllParkings();
        Map<Integer, Integer> indexToId = new HashMap<>();
        int i = 1;
        Map<Parking, String> parkingsMap = new HashMap<>();
        for (Parking parking : parkings) {
            parkingView.printAllParkings(i, parking,
                    tariffService.getTariffByID(parking.getTariffFID()).getTitle());
            indexToId.put(i, parking.getId());
            i++;
        }
        return indexToId;
    }

    public String getParkingAddress(int id) {
        return parkingService.getParkingByID(id).getAddress();
    }
}
