package repository;

import entity.TicketEntity;
import irepository.ITicketRepository;
import mapper.TicketEntityModelMapping;
import model.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class TicketRepository implements ITicketRepository {
    private static final Logger logger = Logger.getLogger(TicketRepository.class);
    @Override
    public int insertTicket(Ticket ticket) {
        TicketEntity ticketEntity = TicketEntityModelMapping.toEntity(ticket);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ticketEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return ticketEntity.getTicketID();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        TicketEntity bookingEntity = TicketEntityModelMapping.toEntity(ticket);

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
    public Ticket getTicketById(int id) {
        TicketEntity bookingEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookingEntity = session.get(TicketEntity.class, id);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (bookingEntity == null)
            return null;
        return TicketEntityModelMapping.toModel(bookingEntity);
    }
}
