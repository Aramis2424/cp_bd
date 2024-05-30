package repository;

import entity.JobEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import mapper.JobEntityModelMapping;
import util.HibernateUtil;
import irepository.IJobRepository;
import model.Job;

public class JobRepository implements IJobRepository {

    @Override
    public int insertJob(Job job) {
        JobEntity jobEntity = JobEntityModelMapping.toEntity(job);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(jobEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return jobEntity.getJobID();
    }

    @Override
    public void updateJob(Job job) {
        JobEntity jobEntity = JobEntityModelMapping.toEntity(job);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(jobEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteJob(int jobId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            JobEntity jobEntity = session.get(JobEntity.class, jobId);
            if (jobEntity == null)
                throw new ObjectNotFountException();
            session.delete(jobEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ConnectionException();
        }
    }

    @Override
    public Job getJobById(int jobId) {
        JobEntity jobEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            jobEntity = session.get(JobEntity.class, jobId);
        } catch (Exception e) {
            throw new ConnectionException();
        }
        if (jobEntity == null)
            return null;
        return JobEntityModelMapping.toModel(jobEntity);
    }
}
