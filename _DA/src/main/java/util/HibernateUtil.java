package util;

import entity.*;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                addClasses(configuration);

                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.fatal(e);
            }
        }
        return sessionFactory;
    }

    public static void addClasses(Configuration cong) {
        cong.addAnnotatedClass(JobEntity.class);
        cong.addAnnotatedClass(AutoOwnerEntity.class);
        cong.addAnnotatedClass(BookingEntity.class);
        cong.addAnnotatedClass(TicketEntity.class);
        cong.addAnnotatedClass(ParkingEntity.class);
        cong.addAnnotatedClass(CarEntity.class);
        cong.addAnnotatedClass(EmployeeEntity.class);
        cong.addAnnotatedClass(ParkingMeterEntity.class);
        cong.addAnnotatedClass(SubscriptionEntity.class);
        cong.addAnnotatedClass(TariffEntity.class);
        cong.addAnnotatedClass(AdminEntity.class);
    }

    public static void close() {
        if(serviceRegistry!= null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}

