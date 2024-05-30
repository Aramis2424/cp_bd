package repository;

import entity.TariffEntity;
import irepository.ITariffRepository;
import mapper.EntityModelMapper;
import model.Tariff;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class TariffRepository implements ITariffRepository {
    private static final Logger logger = Logger.getLogger(TariffRepository.class);
    @Override
    public int insertTariff(Tariff tariff) {
        TariffEntity tariffEntity = EntityModelMapper.toEntity(tariff, TariffEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tariffEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return tariffEntity.getTariffID();
    }

    @Override
    public void updateTariff(Tariff tariff) {
        TariffEntity tariffEntity = EntityModelMapper.toEntity(tariff, TariffEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tariffEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteTariff(int tariffId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TariffEntity parkingMeterEntity = session.get(TariffEntity.class, tariffId);
            if (parkingMeterEntity == null)
                throw new ObjectNotFountException();
            session.delete(parkingMeterEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ConnectionException();
        }
    }

    @Override
    public Tariff getTariffById(int tariffId) {
        TariffEntity tariffEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tariffEntity = session.get(TariffEntity.class, tariffId);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (tariffEntity == null)
            return null;
        return  EntityModelMapper.toModel(tariffEntity, Tariff.class);
    }
}
