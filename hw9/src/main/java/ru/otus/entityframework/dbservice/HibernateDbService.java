package ru.otus.entityframework.dbservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.domain.UserDataSet;
import ru.otus.entityframework.DataSet;

import java.io.Serializable;
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

    public HibernateDbService(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public <T extends DataSet> void save(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();

        }
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(clazz, id);
        }
    }
}
