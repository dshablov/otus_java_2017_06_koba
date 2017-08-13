package ru.otus.domain;

import ru.otus.entityframework.DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:46
 */

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public UserDataSet(Long id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    private UserDataSet() {
        super(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        UserDataSet that = (UserDataSet) object;

        if (age != that.age) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
