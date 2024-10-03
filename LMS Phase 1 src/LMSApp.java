import java.io.*;
import java.util.Scanner;

/**
 * Colin Williams, Software 1, 10/3/24
 * Class: LMSApp
 * This is the main class of the Library Management System. It interacts with the user, loads books, and performs various operations on the library.
 */
public class LMSApp {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // 1. Ask for file input and load books
        System.out.print("Enter the file name to load books: ");
        String fileName = scanner.nextLine();
        loadBooksFromFile(library, fileName);
        System.out.println("\nBooks in the library:");
        library.displayBooks();

        // 2. Remove a book by barcode
        System.out.print("\nEnter a barcode to remove a book: ");
        String barcode = scanner.nextLine();
        if (library.removeBookByBarcode(barcode)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
        library.displayBooks();

        // 3. Remove a book by title
        System.out.print("\nEnter a title to remove a book: ");
        String title = scanner.nextLine();
        if (library.removeBookByTitle(title)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
        library.displayBooks();

        // 4. Check out a book
        System.out.print("\nEnter a title to check out a book: ");
        title = scanner.nextLine();
        System.out.print("Enter the due date: ");
        String dueDate = scanner.nextLine();
        if (library.checkOutBook(title, dueDate)) {
            System.out.println("Book checked out successfully.");
        } else {
            System.out.println("Book not found or already checked out.");
        }
        library.displayBooks();

        // 5. Check in a book
        System.out.print("\nEnter a title to check in a book: ");
        title = scanner.nextLine();
        if (library.checkInBook(title)) {
            System.out.println("Book checked in successfully.");
        } else {
            System.out.println("Book not found or already checked in.");
        }
        library.displayBooks();
    }

    // Method: loadBooksFromFile
    /**
     * Method: loadBooksFromFile
     * Purpose: Loads books into the library from a file specified by the user.
     * Arguments: 
     *  - Library library: the library object where books are stored
     *  - String fileName: the name of the file to load books from
     * Return Value: None
     */
    public static void loadBooksFromFile(Library library, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 4) {
                    Book book = new Book(bookData[0], bookData[1], bookData[2], bookData[3]);
                    library.addBook(book);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}