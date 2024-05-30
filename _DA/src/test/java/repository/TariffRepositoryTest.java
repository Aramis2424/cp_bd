package repository;

import irepository.ITariffRepository;
import model.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffRepositoryTest {
    private ITariffRepository tariffRepository;

    @BeforeEach
    void setUp() {
        tariffRepository = new TariffRepository();
    }

    @Test
    void insertTariff() {
        Tariff tariff = Tariff.createTariffModel("Tester", 10, 15);
        int id = tariffRepository.insertTariff(tariff);

        Tariff newTariff = tariffRepository.getTariffById(id);

        assertEquals("Tester", newTariff.getTitle());
    }

    @Test
    void updateTariff() {
        Tariff tariff = Tariff.createTariffModel("Tester", 10, 15);
        int id = tariffRepository.insertTariff(tariff);

        Tariff newTariff = tariffRepository.getTariffById(id);
        newTariff.setTitle("Dev");
        tariffRepository.updateTariff(newTariff);

        newTariff = tariffRepository.getTariffById(id);
        assertEquals("Dev", newTariff.getTitle());
    }

    @Test
    void deleteTariff() {
        Tariff tariff = Tariff.createTariffModel("Tester", 10, 15);
        int id = tariffRepository.insertTariff(tariff);

        tariffRepository.deleteTariff(id);

        Tariff newTariff = tariffRepository.getTariffById(id);
        assertNull(newTariff);
    }

    @Test
    void getTariffById() {
        Tariff tariff = Tariff.createTariffModel("Tester", 10, 15);
        int id = tariffRepository.insertTariff(tariff);

        Tariff newTariff = tariffRepository.getTariffById(id);

        assertEquals("Tester", newTariff.getTitle());
    }
}