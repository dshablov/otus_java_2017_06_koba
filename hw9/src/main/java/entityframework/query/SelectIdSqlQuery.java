package entityframework.query;

/**
 * User: Vladimir Koba
 * Date: 11.08.2017
 * Time: 23:53
 */
public class SelectIdSqlQuery implements SqlQuery {
    private final String tableName;
    private final Long id;

    public SelectIdSqlQuery(String tableName, long id) {
        this.tableName = tableName;
        this.id = id;
    }

    @Override
    public void addParameter(Class<?> fieldType, String name, String value) {
        return;
    }

    @Override
    public String bulid() {
        return "select * from %table% where id = %id%"
                .replaceAll("%table%", tableName)
                .replaceAll("%id%", id.toString());

    }
}
