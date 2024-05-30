package service;

import model.Tariff;

public interface ITariffService {
    public int createTariff(Tariff tariff);

    public void updateTariff(Tariff tariff);

    public void removeTariff(int id);

    public Tariff getTariffByID(int id);
}
