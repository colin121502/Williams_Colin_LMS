import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection for the Library Management System.
 * Handles storing and retrieving book information from an SQLite database.
 */
public class BookDatabase {
    private static final String DATABASE_URL = "jdbc:sqlite:library.db";

    /**
     * Establishes a connection to the SQLite database.
     *
     * @return the Connection object
     * @throws SQLException if a database access error occurs
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    /**
     * Initializes the database by creating the required table if it does not exist.
     */
    public void initializeDatabase() {
        try (Connection conn = connect()) {
            String sql = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT, " +
                    "author TEXT, " +
                    "barcode TEXT, " +
                    "genre TEXT, " +
                    "status TEXT, " +
                    "due_date TEXT)";
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}