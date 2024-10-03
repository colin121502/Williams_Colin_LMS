import java.util.ArrayList;

/**
 * Colin Williams, Software 1, 10/3/24
 * Class: Library
 * This class manages the collection of books in the library. It allows for adding, removing, checking in/out, and displaying books.
 */
public class Library {
    private ArrayList<Book> books;

    // Constructor
    /**
     * Constructor: Library
     * Purpose: Initializes a new Library object with an empty collection of books.
     * Arguments: None
     * Return Value: None
     */
    public Library() {
        books = new ArrayList<>();
    }

    // Method: addBook
    /**
     * Method: addBook
     * Purpose: Adds a new book to the library's collection.
     * Arguments:
     *  - Book book: the book to be added to the collection
     * Return Value: None
     */
    public void addBook(Book book) {
        books.add(book);
    }

    // Method: removeBookByBarcode
    /**
     * Method: removeBookByBarcode
     * Purpose: Removes a book from the library's collection based on the barcode.
     * Arguments: 
     *  - String barcode: the barcode of the book to be removed
     * Return Value: 
     *  - boolean: true if the book was found and removed, false otherwise
     */
    public boolean removeBookByBarcode(String barcode) {
        for (Book book : books) {
            if (book.getBarcode().equals(barcode)) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    // Method: removeBookByTitle
    /**
     * Method: removeBookByTitle
     * Purpose: Removes a book from the library's collection based on the title.
     * Arguments: 
     *  - String title: the title of the book to be removed
     * Return Value: 
     *  - boolean: true if the book was found and removed, false otherwise
     */
    public boolean removeBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    // Method: checkOutBook
    /**
     * Method: checkOutBook
     * Purpose: Checks out a book by setting its status to checked out and assigning a due date.
     * Arguments:
     *  - String title: the title of the book to be checked out
     *  - String dueDate: the due date for the book when checked out
     * Return Value: 
     *  - boolean: true if the book was found and successfully checked out, false otherwise
     */
    public boolean checkOutBook(String title, String dueDate) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isCheckedOut()) {
                book.checkOut(dueDate);
                return true;
            }
        }
        return false;
    }

    // Method: checkInBook
    /**
     * Method: checkInBook
     * Purpose: Checks in a book by setting its status to available.
     * Arguments: 
     *  - String title: the title of the book to be checked in
     * Return Value: 
     *  - boolean: true if the book was found and successfully checked in, false otherwise
     */
    public boolean checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isCheckedOut()) {
                book.checkIn();
                return true;
            }
        }
        return false;
    }

    // Method: displayBooks
    /**
     * Method: displayBooks
     * Purpose: Prints out all books currently in the library's collection.
     * Arguments: None
     * Return Value: None
     */
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}