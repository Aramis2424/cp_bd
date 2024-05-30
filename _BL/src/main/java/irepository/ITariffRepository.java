package irepository;

import model.Tariff;

public interface ITariffRepository {
    public int insertTariff(Tariff tariff);

    public void updateTariff(Tariff tariff);

    public void deleteTariff(int tariffId);

    public Tariff getTariffById(int tariffId);
}
