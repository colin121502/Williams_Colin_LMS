import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all database interactions related to books in the LMS.
 * Includes methods to view, remove, check out, and check in books.
 *
 * Author: Colin Williams
 * Date: 11/17/2024
 */
public class BookManager {

    /**
     * Fetches all books from the database.
     *
     * @return A list of string arrays, where each array represents a book's details.
     */
    public List<String[]> viewBooks() {
        List<String[]> books = new ArrayList<>();
        String query = "SELECT * FROM Books";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Iterate through the result set and add each book to the list
            while (rs.next()) {
                books.add(new String[]{
                        String.valueOf(rs.getInt("Barcode")),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Genre"),
                        rs.getString("Status"),
                        rs.getString("DueDate") == null ? "N/A" : rs.getString("DueDate")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Removes a book from the database by title or barcode.
     *
     * @param titleOrBarcode The title or barcode of the book to remove.
     * @return true if the book was removed successfully, false otherwise.
     */
    public boolean removeBook(String titleOrBarcode) {
        String query = "DELETE FROM Books WHERE Title = ? OR Barcode = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, titleOrBarcode);
            pstmt.setString(2, titleOrBarcode);
            return pstmt.executeUpdate() > 0; // Returns true if at least one row was affected
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks out a book by updating its status and setting a due date.
     *
     * @param barcode The unique identifier of the book to check out.
     * @param dueDate The due date for the book (in YYYY-MM-DD format).
     * @return true if the book was checked out successfully, false otherwise.
     */
    public boolean checkOutBook(int barcode, String dueDate) {
        String query = "UPDATE Books SET Status = 'Checked Out', DueDate = ? WHERE Barcode = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, dueDate);
            pstmt.setInt(2, barcode);
            return pstmt.executeUpdate() > 0; // Returns true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks in a book by updating its status and clearing the due date.
     *
     * @param barcode The unique identifier of the book to check in.
     * @return true if the book was checked in successfully, false otherwise.
     */
    public boolean checkInBook(int barcode) {
        String query = "UPDATE Books SET Status = 'Checked In', DueDate = NULL WHERE Barcode = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, barcode);
            return pstmt.executeUpdate() > 0; // Returns true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}