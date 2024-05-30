package repository;

import entity.AutoOwnerEntity;
import irepository.IAutoOwnerRepository;
import mapper.AutoOwnerEntityModelMapping;
import model.AutoOwner;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class AutoOwnerRepository implements IAutoOwnerRepository {
    private static final Logger logger = Logger.getLogger(AutoOwnerRepository.class);
    @Override
    public int insertAutoOwner(AutoOwner autoOwner) {
        AutoOwnerEntity autoOwnerEntity = AutoOwnerEntityModelMapping.toEntity(autoOwner);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(autoOwnerEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return autoOwnerEntity.getAutoOwnerID();
    }

    @Override
    public void updateAutoOwner(AutoOwner autoOwner) {
        AutoOwnerEntity autoOwnerEntity = AutoOwnerEntityModelMapping.toEntity(autoOwner);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(autoOwnerEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteAutoOwner(int autoOwnerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            AutoOwnerEntity autoOwnerEntity = session.get(AutoOwnerEntity.class, autoOwnerId);
            if (autoOwnerEntity == null)
                throw new ObjectNotFountException();
            session.delete(autoOwnerEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ConnectionException();
        }
    }

    @Override
    public AutoOwner getAutoOwnerByID(int autoOwnerId) {
        AutoOwnerEntity autoOwnerEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            autoOwnerEntity = session.get(AutoOwnerEntity.class, autoOwnerId);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (autoOwnerEntity == null)
            return null;
        return AutoOwnerEntityModelMapping.toModel(autoOwnerEntity);
    }

    @Override
    public AutoOwner getAutoOwnerBySignInfo(String login, String password) {
        AutoOwnerEntity autoOwnerEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM AutoOwnerEntity WHERE login = :login AND password = :password";
            Query<AutoOwnerEntity> query = session.createQuery(hql, AutoOwnerEntity.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            autoOwnerEntity = query.uniqueResult();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (autoOwnerEntity == null)
            return null;
        return AutoOwnerEntityModelMapping.toModel(autoOwnerEntity);
    }

    @Override
    public List<AutoOwner> getAllAutoOwners() {
        List<AutoOwnerEntity> autoOwnersEntities = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM AutoOwnerEntity";
            Query<AutoOwnerEntity> query = session.createQuery(hql, AutoOwnerEntity.class);
            autoOwnersEntities = query.list();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (autoOwnersEntities == null || autoOwnersEntities.isEmpty())
            return null;
        return AutoOwnerEntityModelMapping.ListToModel(autoOwnersEntities);
    }
}
