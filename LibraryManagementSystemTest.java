package lmsphaseone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class LibraryManagementSystemTest {

    private Library library;

    // Initialize a new Library instance before each test
    @BeforeEach
    void setUp() {
        library = new Library();
    }

    // Test to verify that a book can be added to the library
    @Test
    void testAddBook() {
        Book book = new Book("123", "Test Title", "Test Author");
        library.addBook(book);

        List<Book> books = library.getBooks();
        assertTrue(books.contains(book), "The book should be added to the library.");
    }

    // Test to verify that a book can be removed by its barcode
    @Test
    void testRemoveBookByBarcode() {
        Book book = new Book("123", "Test Title", "Test Author");
        library.addBook(book);

        library.removeBookByBarcode("123");

        List<Book> books = library.getBooks();
        assertFalse(books.contains(book), "The book should be removed from the library by barcode.");
    }

    // Test to verify that a book can be removed by its title
    @Test
    void testRemoveBookByTitle() {
        Book book = new Book("123", "Test Title", "Test Author");
        library.addBook(book);

        library.removeBookByTitle("Test Title");

        List<Book> books = library.getBooks();
        assertFalse(books.contains(book), "The book should be removed from the library by title.");
    }

    // Test to verify that a book can be checked out with a due date
    @Test
    void testCheckOutBook() {
        Book book = new Book("123", "Test Title", "Test Author");
        library.addBook(book);

        library.checkOutBook("123", "2024-12-01");

        assertTrue(book.isCheckedOut(), "The book should be marked as checked out.");
        assertEquals("2024-12-01", book.getDueDate(), "The due date should be set correctly.");
    }

    // Test to verify that a checked-out book can be checked in
    @Test
    void testCheckInBook() {
        Book book = new Book("123", "Test Title", "Test Author");
        book.setCheckedOut(true);
        book.setDueDate("2024-12-01");
        library.addBook(book);

        library.checkInBook("123");

        assertFalse(book.isCheckedOut(), "The book should be marked as checked in.");
        assertNull(book.getDueDate(), "The due date should be reset to null.");
    }

    // Test to verify the list of books in the library
    @Test
    void testGetBooks() {
        Book book1 = new Book("123", "Test Title 1", "Author 1");
        Book book2 = new Book("456", "Test Title 2", "Author 2");

        library.addBook(book1);
        library.addBook(book2);

        List<Book> books = library.getBooks();
        assertEquals(2, books.size(), "The library should contain two books.");
        assertTrue(books.contains(book1) && books.contains(book2),
                "Both books should be present in the library.");
    }
}