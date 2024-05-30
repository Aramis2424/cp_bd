package repository;

import entity.SubscriptionEntity;
import irepository.ISubscriptionRepository;
import mapper.EntityModelMapper;
import model.Subscription;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class SubscriptionRepository implements ISubscriptionRepository {
    private static final Logger logger = Logger.getLogger(SubscriptionRepository.class);
    @Override
    public int insertSubscription(Subscription subscription) {
        SubscriptionEntity subscriptionEntity = EntityModelMapper.toEntity(subscription, SubscriptionEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(subscriptionEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return subscriptionEntity.getSubscriptionID();
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        SubscriptionEntity subscriptionEntity = EntityModelMapper.toEntity(subscription, SubscriptionEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(subscriptionEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteSubscription(int subscriptionId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            SubscriptionEntity subscriptionEntity = session.get(SubscriptionEntity.class, subscriptionId);
            if (subscriptionEntity == null)
                throw new ObjectNotFountException();
            session.delete(subscriptionEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ConnectionException();
        }
    }

    @Override
    public Subscription getSubscriptionByID(int subscriptionId) {
        SubscriptionEntity subscriptionEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            subscriptionEntity = session.get(SubscriptionEntity.class, subscriptionId);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (subscriptionEntity == null)
            return null;
        return  EntityModelMapper.toModel(subscriptionEntity, Subscription.class);
    }
}
