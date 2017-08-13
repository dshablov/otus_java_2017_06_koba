package entityframework.query;

/**
 * User: Vladimir Koba
 * Date: 11.08.2017
 * Time: 23:35
 */
public interface SqlQuery {
    void addParameter(Class<?> fieldType, String name, String value);

    String bulid();
}
