package repository;

import entity.AdminEntity;
import entity.AutoOwnerEntity;
import irepository.IAdminRepository;
import mapper.AutoOwnerEntityModelMapping;
import mapper.EntityModelMapper;
import model.Admin;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class AdminRepository implements IAdminRepository {
    private static final Logger logger = Logger.getLogger(AdminRepository.class);
    @Override
    public Admin getAdminBySignInfo(String login, String password) {
        AdminEntity adminEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM AdminEntity WHERE login = :login AND password = :password";
            Query<AdminEntity> query = session.createQuery(hql, AdminEntity.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            adminEntity = query.uniqueResult();
        } catch (Exception e) {
            logger.fatal(e);
            throw new ConnectionException();
        }
        if (adminEntity == null)
            return null;
        return EntityModelMapper.toModel(adminEntity, Admin.class);
    }
}
