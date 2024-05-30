package repository;

import entity.BookingEntity;
import irepository.IBookingRepository;
import mapper.BookingEntityModelMapping;
import model.Booking;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository implements IBookingRepository {
    private static final Logger logger = Logger.getLogger(BookingRepository.class);
    @Override
    public int insertBooking(Booking booking) {
        BookingEntity bookingEntity = BookingEntityModelMapping.toEntity(booking);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(bookingEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return bookingEntity.getBookingID();
    }

    @Override
    public void updateBooking(Booking booking) {
        BookingEntity bookingEntity = BookingEntityModelMapping.toEntity(booking);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(bookingEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public Booking getBookingById(int bookingId) {
        BookingEntity bookingEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookingEntity = session.get(BookingEntity.class, bookingId);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (bookingEntity == null)
            return null;
        return BookingEntityModelMapping.toModel(bookingEntity);
    }

    @Override
    public List<Booking> getAllBookings() {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM BookingEntity";
            Query<BookingEntity> query = session.createQuery(hql, BookingEntity.class);
            bookingEntities = query.list();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (bookingEntities == null || bookingEntities.isEmpty())
            return null;
        return BookingEntityModelMapping.ListToModel(bookingEntities);
    }

    @Override
    public List<Booking> getActiveBookingsByUser(int userId) {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM BookingEntity as be " +
                    "WHERE be.autoOwnerFID = :userID and be.isActive = TRUE";
            Query<BookingEntity> query = session.createQuery(hql, BookingEntity.class);
            query.setParameter("userID", userId);
            bookingEntities = query.list();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (bookingEntities == null || bookingEntities.isEmpty())
            return null;
        return BookingEntityModelMapping.ListToModel(bookingEntities);
    }
}
