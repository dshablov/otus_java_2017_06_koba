package ru.otus.validation;

import ru.otus.entityframework.dbservice.DbService;

/**
 * User: Vladimir Koba
 * Date: 24.10.2017
 * Time: 0:15
 */
public class ValidationService {
    private DbService dbService;

    public ValidationService(DbService dbService) {
        this.dbService = dbService;
    }

    public boolean isValidCredentials(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        return dbService.loadByUsernameAndPassword(login, password) != null;
    }
}
