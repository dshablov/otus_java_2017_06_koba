package ru.otus.domain;

import ru.otus.entityframework.DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * User: Vladimir Koba
 * Date: 07.09.2017
 * Time: 0:25
 */

@Entity
@Table(name = "audit")
public class AuditDataSet extends DataSet{
    @Column
    private LocalDateTime loginTime;
    @Column
    private String user;

    public AuditDataSet(Long id, LocalDateTime loginTime, String user) {
        super(id);
        this.loginTime = loginTime;
        this.user = user;
    }


    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
