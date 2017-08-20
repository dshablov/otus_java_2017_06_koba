package ru.otus.entityframework.dao;

import org.hibernate.Session;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.UserDataSet;

/**
 * User: Vladimir Koba
 * Date: 16.08.2017
 * Time: 0:07
 */
public class PhoneDao {
    private Session session;

    public PhoneDao(Session session) {
        this.session = session;
    }


    public void save(PhoneDataSet phoneDataSet) {
        session.saveOrUpdate(phoneDataSet);
    }

    public PhoneDataSet load(Long id) {
        return session.load(PhoneDataSet.class, id);
    }
}
