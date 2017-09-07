package ru.otus.entityframework.dao;

import org.hibernate.Session;
import ru.otus.domain.AuditDataSet;

/**
 * User: Vladimir Koba
 * Date: 07.09.2017
 * Time: 0:23
 */
public class AuditDao {
    private Session session;

    public AuditDao(Session session) {
        this.session = session;
    }


    public void save(AuditDataSet auditDataSet) {
        session.saveOrUpdate(auditDataSet);
    }

    public AuditDataSet load(Long id) {
        return session.load(AuditDataSet.class, id);
    }
}
