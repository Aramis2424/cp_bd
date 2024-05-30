package service;

import irepository.ITariffRepository;
import model.Tariff;

public class TariffService implements ITariffService {

    private ITariffRepository tariffRep;

    public TariffService(ITariffRepository tariffRepository){tariffRep = tariffRepository;}

    @Override
    public int createTariff(Tariff tariff) {
        int id = tariffRep.insertTariff(tariff);
        tariff.setId(id);
        return id;
    }

    @Override
    public void updateTariff(Tariff tariff) {
        tariffRep.updateTariff(tariff);
    }

    @Override
    public void removeTariff(int id) {
        tariffRep.deleteTariff(id);
    }

    @Override
    public Tariff getTariffByID(int id) {
        return tariffRep.getTariffById(id);
    }
}
