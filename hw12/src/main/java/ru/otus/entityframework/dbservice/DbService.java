package ru.otus.entityframework.dbservice;

import ru.otus.domain.UserDataSet;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:55
 */
public interface DbService {

    void save(UserDataSet audit);

    UserDataSet load(Long id);

    UserDataSet loadByUsernameAndPassword(String username, String password);


}
