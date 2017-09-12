package ru.otus.entityframework.dao;

import org.hibernate.Session;
import ru.otus.domain.UserDataSet;

/**
 * User: Vladimir Koba
 * Date: 07.09.2017
 * Time: 0:23
 */
public class UserDao {
    private Session session;

    public UserDao(Session session) {
        this.session = session;
    }


    public void save(UserDataSet userDataSet) {
        session.saveOrUpdate(userDataSet);
    }

    public UserDataSet load(Long id) {
        return session.load(UserDataSet.class, id);
    }
}
