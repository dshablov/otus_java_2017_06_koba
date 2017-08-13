package entityframework;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * User: Vladimir Koba
 * Date: 09.08.2017
 * Time: 23:46
 */

@MappedSuperclass
public abstract class DataSet {
    @Column(name = "id")
    protected Long id;

    public DataSet(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
