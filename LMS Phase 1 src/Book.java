/**
 * Colin Williams, Software 1, 10/3/24
 * Class: Book
 * This class represents a book in the library system. It contains basic information such as title, author, barcode, genre, status, and due date.
 */
public class Book {
    private String title;
    private String author;
    private String barcode;
    private String genre;
    private boolean isCheckedOut;
    private String dueDate;

    /**
     * Constructor: Book
     * Purpose: Initializes a book object with the given title, author, barcode, and genre. Sets the status to available.
     * Arguments: 
     *  - String title: the title of the book
     *  - String author: the author of the book
     *  - String barcode: the barcode of the book
     *  - String genre: the genre of the book
     * Return Value: None
     */
    public Book(String title, String author, String barcode, String genre) {
        this.title = title;
        this.author = author;
        this.barcode = barcode;
        this.genre = genre;
        this.isCheckedOut = false;
        this.dueDate = "N/A";
    }

    // Getters and setters
    public String getTitle() { return title; }
    public String getBarcode() { return barcode; }
    public boolean isCheckedOut() { return isCheckedOut; }

    /**
     * Method: checkOut
     * Purpose: Sets the book's status to checked out and assigns a due date.
     * Arguments: 
     *  - String dueDate: the due date for the book when checked out
     * Return Value: None
     */
    public void checkOut(String dueDate) { this.isCheckedOut = true; this.dueDate = dueDate; }
    
    /**
     * Method: checkIn
     * Purpose: Sets the book's status to available and clears the due date.
     * Arguments: None
     * Return Value: None
     */
    public void checkIn() { this.isCheckedOut = false; this.dueDate = "N/A"; }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Barcode: " + barcode + ", Genre: " + genre +
                ", Status: " + (isCheckedOut ? "Checked Out (Due: " + dueDate + ")" : "Available");
    }
}