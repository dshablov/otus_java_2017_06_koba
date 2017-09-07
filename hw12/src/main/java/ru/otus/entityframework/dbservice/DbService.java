package ru.otus.entityframework.dbservice;

import ru.otus.domain.AuditDataSet;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:55
 */
public interface DbService {

    void save(AuditDataSet audit);

    AuditDataSet load(Long id);

    


}
