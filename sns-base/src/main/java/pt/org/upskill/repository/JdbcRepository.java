package pt.org.upskill.repository;
import pt.org.upskill.db.ConnectionFactory;
import pt.org.upskill.db.DatabaseConnection;
import pt.org.upskill.session.Context;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRepository {
    private static ConnectionFactory cf = Context.getConnectionFactory();
    private static DatabaseConnection dbc = cf.getDatabaseConnection();
    protected static Connection conn = dbc.getConnection();

    public void beginTransaction() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
