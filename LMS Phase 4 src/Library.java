import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of books and provides functionalities to interact with the library's database.
 * Supports operations such as adding, removing, checking in/out books, and displaying the collection.
 */
public class Library {
    private List<Book> books;

    /**
     * Constructs an empty library.
     */
    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Adds a book to the library.
     *
     * @param book the book to add to the library
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the library by its barcode.
     *
     * @param barcode the barcode of the book to remove
     * @return true if the book was successfully removed, false otherwise
     */
    public boolean removeBookByBarcode(String barcode) {
        return books.removeIf(book -> book.getBarcode().equals(barcode));
    }

    /**
     * Displays all books in the library.
     */
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }

    // Additional methods for checking in and checking out books...
}
