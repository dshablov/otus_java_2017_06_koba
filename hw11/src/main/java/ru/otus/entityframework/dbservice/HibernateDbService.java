package ru.otus.entityframework.dbservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.domain.AddressDataSet;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.UserDataSet;
import ru.otus.entityframework.dao.UserDao;

import java.util.Properties;


/**
 * User: Vladimir Koba
 * Date: 13.08.2017
 * Time: 23:23
 */
public class HibernateDbService implements DbService {

    private final SessionFactory sessionFactory;

    public HibernateDbService() {
        Properties props = new Properties();
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        props.put("hibernate.dialect", ru.otus.hibernate.SQLiteDialect.class);
        props.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
        props.put("hibernate.connection.url", "jdbc:sqlite:sample.db");
        props.put("hibernate.connection.username", "");
        props.put("hibernate.connection.password", "");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put("hibernate.connection.useSSL", "false");
        props.put("hibernate.enable_lazy_load_no_trans", "true");
        configuration.setProperties(props);
        sessionFactory = createSessionFactory(configuration);

    }


    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void save(UserDataSet user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            UserDao userDao = new UserDao(session);
            userDao.save(user);
            session.getTransaction().commit();

        }
    }

    @Override
    public UserDataSet load(Long id) {
        try (Session session = sessionFactory.openSession()) {
            UserDao userDao = new UserDao(session);
            return userDao.load(id);
        }
    }
}
