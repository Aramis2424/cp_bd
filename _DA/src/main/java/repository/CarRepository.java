package repository;

import entity.AutoOwnerEntity;
import irepository.ICarRepository;
import entity.CarEntity;
import mapper.AutoOwnerEntityModelMapping;
import mapper.CarEntityModelMapping;
import model.Car;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CarRepository implements ICarRepository {
    private static final Logger logger = Logger.getLogger(CarRepository.class);
    @Override
    public void insertCar(Car car) {
        CarEntity carEntity = CarEntityModelMapping.toEntity(car);
        AutoOwnerRepository aor = new AutoOwnerRepository();
        AutoOwnerEntity autoOwnerEntity = AutoOwnerEntityModelMapping
                .toEntity(aor.getAutoOwnerByID(car.getAutoOwnerID()));
        carEntity.getAutoOwnerEntities().add(autoOwnerEntity);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(carEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
    }

    @Override
    public void updateCar(Car car) {
        CarEntity carEntity = CarEntityModelMapping.toEntity(car);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(carEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteCar(String number) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CarEntity carEntity = session.get(CarEntity.class, number);
            if (carEntity == null)
                throw new ObjectNotFountException();
            session.delete(carEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ConnectionException();
        }
    }

    @Override
    public Car getCarByNumber(String numberCar) {
        CarEntity carEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            carEntity = session.get(CarEntity.class, numberCar);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (carEntity == null)
            return null;
        return CarEntityModelMapping.toModel(carEntity);
    }

    @Override
    public List<Car> getCarsByUser(int userId) {
        List<CarEntity> carsEntities = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM CarEntity as ce JOIN ce.autoOwnerEntities as aoe " +
                    "WHERE aoe.autoOwnerID = :userID";
            Query<?> query = session.createQuery(hql);
            query.setParameter("userID", userId);
            List<?> list = query.list();
            for (Object o : list) {
                Object[] row = (Object[]) o;
                carsEntities.add((CarEntity) row[0]);
            }
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (carsEntities.isEmpty())
            return null;
        return CarEntityModelMapping.ListToModel(carsEntities);
    }
}
