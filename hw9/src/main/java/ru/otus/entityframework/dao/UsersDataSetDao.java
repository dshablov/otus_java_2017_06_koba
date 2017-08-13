package ru.otus.entityframework.dao;

import org.hibernate.Session;
import ru.otus.domain.UserDataSet;

/**
 * User: Vladimir Koba
 * Date: 14.08.2017
 * Time: 0:02
 */
public class UsersDataSetDao {
    private Session session;

    public UsersDataSetDao(Session session) {
        this.session = session;
    }

    public void save(UserDataSet userDataSet) {
        session.save(userDataSet);
    }

    public UserDataSet load(long id) {
        return session.find(UserDataSet.class, id);
    }
}
