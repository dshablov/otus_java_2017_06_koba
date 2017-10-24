package ru.otus.entityframework.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.otus.domain.UserDataSet;

import java.util.List;


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

    public UserDataSet loadByUsernameAndPassword(String username, String password) {
        Query query = session.createQuery("from userinfo where login = :login and password = :password");
        query.setParameter("login", username);
        query.setParameter("password", password);
        List list = query.list();
        if (list.isEmpty()) {
            return null;
        }
        return (UserDataSet) list.get(0);
    }
}
