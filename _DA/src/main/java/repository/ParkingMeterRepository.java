package repository;

import entity.ParkingMeterEntity;
import irepository.IParkingMeterRepository;
import mapper.EntityModelMapper;
import model.ParkingMeter;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ParkingMeterRepository implements IParkingMeterRepository {
    private static final Logger logger = Logger.getLogger(ParkingMeter.class);
    @Override
    public int insertParkingMeter(ParkingMeter parkingMeter) {
        ParkingMeterEntity parkingMeterEntity = EntityModelMapper.toEntity(parkingMeter, ParkingMeterEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(parkingMeterEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return parkingMeterEntity.getParkingMeterID();
    }

    @Override
    public void updateParkingMeter(ParkingMeter parkingMeter) {
        ParkingMeterEntity parkingMeterEntity = EntityModelMapper.toEntity(parkingMeter, ParkingMeterEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(parkingMeterEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteParkingMeter(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ParkingMeterEntity parkingMeterEntity = session.get(ParkingMeterEntity.class, id);
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
    public ParkingMeter getParkingMeterById(int parkingMeterId) {
        ParkingMeterEntity employeeEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employeeEntity = session.get(ParkingMeterEntity.class, parkingMeterId);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (employeeEntity == null)
            return null;
        return  EntityModelMapper.toModel(employeeEntity, ParkingMeter.class);
    }
}
