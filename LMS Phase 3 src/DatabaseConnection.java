import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the database connection for the LMS.
 * Provides a method to establish a connection with the SQL Server database.
 *
 * Author: Colin Williams
 * Date: 11/17/2024
 */
public class DatabaseConnection {

    // Database connection parameters
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LMS"; // Connection URL
    private static final String USER = "your_username"; // Replace with SQL Server username
    private static final String PASSWORD = "your_password"; // Replace with SQL Server password

    /**
     * Establishes and returns a connection to the SQL Server database.
     *
     * @return Connection object to interact with the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}