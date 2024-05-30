package repository;

import entity.ParkingEntity;
import irepository.IParkingRepository;
import mapper.ParkingEntityModelMapping;
import model.Parking;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ParkingRepository implements IParkingRepository {
    private static final Logger logger = Logger.getLogger(ParkingRepository.class);
    @Override
    public int insertParking(Parking parking) {
        ParkingEntity parkingEntity = ParkingEntityModelMapping.toEntity(parking);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(parkingEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return parkingEntity.getParkingID();
    }

    @Override
    public void updateParking(Parking parking) {
        ParkingEntity parkingEntity = ParkingEntityModelMapping.toEntity(parking);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(parkingEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteParking(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ParkingEntity parkingEntity = session.get(ParkingEntity.class, id);
            if (parkingEntity == null)
                throw new ObjectNotFountException();
            session.delete(parkingEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ConnectionException();
        }
    }

    @Override
    public List<Parking> getAllParkings() {
        List<ParkingEntity> parkingsEntity = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ParkingEntity";
            Query<ParkingEntity> query = session.createQuery(hql, ParkingEntity.class);
            parkingsEntity = query.list();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (parkingsEntity == null || parkingsEntity.isEmpty())
            return null;
        return ParkingEntityModelMapping.ListToModel(parkingsEntity);
    }

    @Override
    public Parking getParkingById(int parkingId) {
        ParkingEntity parkingEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            parkingEntity = session.get(ParkingEntity.class, parkingId);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (parkingEntity == null)
            return null;
        return ParkingEntityModelMapping.toModel(parkingEntity);
    }

    @Override
    public int getFreePlaces(int parkingId) {
        ParkingEntity parkingEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ParkingEntity WHERE parkingId = :parkingId";
            Query<ParkingEntity> query = session.createQuery(hql, ParkingEntity.class);
            query.setParameter("parkingId", parkingId);
            parkingEntity = query.uniqueResult();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (parkingEntity == null)
            return -1;
        return parkingEntity.getFreeParkingPlaces();
    }

    @Override
    public int getTotalPlaces(int parkingId) {
        ParkingEntity parkingEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ParkingEntity WHERE parkingId = :parkingId";
            Query<ParkingEntity> query = session.createQuery(hql, ParkingEntity.class);
            query.setParameter("parkingId", parkingId);
            parkingEntity = query.uniqueResult();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (parkingEntity == null)
            return -1;
        return parkingEntity.getTotalParkingPlaces();
    }
}
