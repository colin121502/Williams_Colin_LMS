/**
 * Represents a book in the Library Management System.
 * Each book has a title, author, barcode number, genre, status, and an optional due date.
 */
public class Book {
    private String title;
    private String author;
    private String barcode;
    private String genre;
    private String status; // "available" or "checked out"
    private String dueDate; // null if not checked out

    /**
     * Constructs a Book with the specified attributes.
     *
     * @param title    the title of the book
     * @param author   the author of the book
     * @param barcode  the unique barcode identifier for the book
     * @param genre    the genre of the book
     * @param status   the availability status of the book
     * @param dueDate  the due date for the book, or null if not checked out
     */
    public Book(String title, String author, String barcode, String genre, String status, String dueDate) {
        this.title = title;
        this.author = author;
        this.barcode = barcode;
        this.genre = genre;
        this.status = status;
        this.dueDate = dueDate;
    }

    /**
     * Gets the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    // Additional getters, setters, and methods...
}