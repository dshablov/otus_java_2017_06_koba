package ru.otus.entityframework.dao;

import org.hibernate.Session;
import ru.otus.domain.UserDataSet;

/**
 * User: Vladimir Koba
 * Date: 14.08.2017
 * Time: 23:52
 */
public class UserDao {
    private Session session;

    public UserDao(Session session) {
        this.session = session;
    }


    public void save(UserDataSet user) {
        session.saveOrUpdate(user);
    }

    public UserDataSet load(Long id) {
        return session.load(UserDataSet.class, id);
    }
}
