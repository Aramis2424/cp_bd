package repository;

import entity.EmployeeEntity;
import irepository.IEmployeeRepository;
import mapper.EntityModelMapper;
import model.Employee;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {
    private static final Logger logger = Logger.getLogger(EmployeeRepository.class);
    @Override
    public int insertEmployee(Employee employees) {
        EmployeeEntity employeeEntity = EntityModelMapper.toEntity(employees, EmployeeEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employeeEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InsertException();
        }
        return employeeEntity.getEmployeeID();
    }

    @Override
    public void updateEmployee(Employee employee) {
        EmployeeEntity employeeEntity = EntityModelMapper.toEntity(employee, EmployeeEntity.class);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employeeEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ObjectNotFountException();
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            EmployeeEntity carEntity = session.get(EmployeeEntity.class, employeeId);
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
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeesEntities = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM EmployeeEntity";
            Query<EmployeeEntity> query = session.createQuery(hql, EmployeeEntity.class);
            employeesEntities = query.list();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (employeesEntities == null || employeesEntities.isEmpty())
            return null;
        return EntityModelMapper.ListToModel(employeesEntities, Employee.class);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        EmployeeEntity employeeEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employeeEntity = session.get(EmployeeEntity.class, employeeId);
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (employeeEntity == null)
            return null;
        return  EntityModelMapper.toModel(employeeEntity, Employee.class);
    }
}
