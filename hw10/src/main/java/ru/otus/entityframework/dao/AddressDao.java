package ru.otus.entityframework.dao;

import org.hibernate.Session;
import ru.otus.domain.AddressDataSet;
import ru.otus.domain.UserDataSet;

/**
 * User: Vladimir Koba
 * Date: 14.08.2017
 * Time: 23:52
 */
public class AddressDao {
    private Session session;

    public AddressDao(Session session) {
        this.session = session;
    }


    public void save(AddressDataSet addressDataSet) {
        session.saveOrUpdate(addressDataSet);
    }

    public AddressDataSet load(Long id) {
        return session.load(AddressDataSet.class, id);
    }
}
