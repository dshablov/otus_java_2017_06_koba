package ru.otus.domain;

import ru.otus.entityframework.DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: Vladimir Koba
 * Date: 14.08.2017
 * Time: 22:54
 */

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet {
    @Column(name = "street")
    private String street;


    public AddressDataSet() {
        super(null);
    }

    public AddressDataSet(Long id) {
        super(id);
    }

    public AddressDataSet(String address) {
        super(null);
        this.street = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
