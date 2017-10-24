package ru.otus.domain;

import ru.otus.entityframework.DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "userinfo")
public class UserDataSet extends DataSet {
    @Column
    private String login;
    @Column
    private String password;


    public UserDataSet() {
        super(null);
    }

    public UserDataSet(Long id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public UserDataSet(Long id) {
        super(id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
