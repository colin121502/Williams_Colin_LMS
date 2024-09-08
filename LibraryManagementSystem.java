import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Colin Williams
 * Course: Software Development 1
 * Date: 9/8/2024
 *
 * Class: Book
 *
 * This class represents a Book in the library.
 * Each Book object contains an ID, a title, and an author.
 * It is part of the Library Management System and is used to store and retrieve
 * book details within the library collection.
 */
class Book {
    private int id;
    private String title;
    private String author;

    // Constructor: Initializes a new Book object with an ID, title, and author.
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getter method for the book's ID
    public int getId() {
        return id;
    }

    // Overriding the toString() method to return a formatted string for displaying book details
    @Override
    public String toString() {
        return "ID: " + id + ", Title: '" + title + "', Author: " + author;
    }
}

/**
 * Author: Colin Williams
 * Course: Software Development 1
 * Date: 9/8/2024
 *
 * Class: Library
 *
 * This class represents the Library. It stores a collection of Book objects
 * and provides methods to add books from a file, remove books by their ID,
 * and list all books in the collection.
 */
class Library {
    private ArrayList<Book> books;

    // Constructor: Initializes the Library with an empty collection of books
    public Library() {
        this.books = new ArrayList<>();
    }

    /**
     * Method: addBooksFromFile
     *
     * This method reads a text file, where each line represents a book with an ID, title, and author.
     * It parses the file and adds valid books to the collection.
     *
     * @param filePath The path to the text file containing book data.
     *
     * @return None (void)
     */
    public void addBooksFromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 3) {
                    int id = Integer.parseInt(bookData[0].trim());
                    String title = bookData[1].trim();
                    String author = bookData[2].trim();
                    books.add(new Book(id, title, author));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        } catch (NumberFormatException e) {
            System.out.println("Error in file format.");
        }
    }

    /**
     * Method: removeBookById
     *
     * This method removes a book from the collection by its unique ID.
     * If the book is found, it is removed from the list; otherwise, an error message is displayed.
     *
     * @param id The unique ID of the book to be removed.
     *
     * @return None (void)
     */
    public void removeBookById(int id) {
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
                System.out.println("Book with ID " + id + " removed.");
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("No book found with ID " + id + ".");
        }
    }

    /**
     * Method: listBooks
     *
     * This method lists all the books currently in the library's collection.
     * If the collection is empty, it displays a message indicating no books are available.
     *
     * @return None (void)
     */
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}

/**
 * Author: Colin Williams
 * Course: Software Development 1
 * Date: 9/8/2024
 *
 * Class: LibraryManagementSystem
 *
 * This class contains the main method, which serves as the entry point of the program.
 * The program allows the user to interact with the Library system by adding books from a file,
 * removing books by their ID, listing all books in the collection, or exiting the program.
 */
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add books from file");
            System.out.println("2. Remove book by ID");
            System.out.println("3. List all books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the file path: ");
                    String filePath = scanner.next();
                    library.addBooksFromFile(filePath);
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int id = scanner.nextInt();
                    library.removeBookById(id);
                    break;
                case 3:
                    library.listBooks();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}