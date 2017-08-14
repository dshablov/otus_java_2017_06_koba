package ru.otus.domain;

import ru.otus.entityframework.DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: Vladimir Koba
 * Date: 14.08.2017
 * Time: 22:55
 */

@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet {

    @Column(name = "number")
    private String number;

    @ManyToOne
    private UserDataSet user;

    public PhoneDataSet() {
        super(null);
    }

    public PhoneDataSet(String number) {
        super(null);
        this.number = number;
    }

    public PhoneDataSet(Long id) {
        super(id);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }
}
