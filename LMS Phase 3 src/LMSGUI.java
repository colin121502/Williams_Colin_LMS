import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * JavaFX GUI for the Library Management System.
 * Allows users to interact with the LMS through buttons and tables.
 *
 * Author: Colin Williams
 * Date: 11/17/2024
 */
public class LMSGUI extends Application {

    private final BookManager bookManager = new BookManager(); // Instance of BookManager for database interactions

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        // Layout container for GUI components
        VBox layout = new VBox(10);

        // TableView to display book details
        TableView<String[]> tableView = new TableView<>();
        tableView.getColumns().addAll(
                createColumn("Barcode", 0),
                createColumn("Title", 1),
                createColumn("Author", 2),
                createColumn("Genre", 3),
                createColumn("Status", 4),
                createColumn("Due Date", 5)
        );

        // Buttons for user interactions
        Button viewBooksBtn = new Button("View Books");
        Button removeBookBtn = new Button("Remove Book");
        Button checkOutBookBtn = new Button("Check Out Book");
        Button checkInBookBtn = new Button("Check In Book");

        // View Books action
        viewBooksBtn.setOnAction(e -> {
            List<String[]> books = bookManager.viewBooks();
            tableView.getItems().clear();
            tableView.getItems().addAll(books);
        });

        // Remove Book action
        removeBookBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter the Title or Barcode of the Book to Remove:");
            dialog.showAndWait().ifPresent(input -> {
                boolean result = bookManager.removeBook(input);
                showAlert(result, "Book Removed Successfully!", "Failed to Remove Book!");
            });
        });

        // Check Out Book action
        checkOutBookBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter the Barcode of the Book to Check Out:");
            dialog.showAndWait().ifPresent(input -> {
                try {
                    int barcode = Integer.parseInt(input);
                    TextInputDialog dateDialog = new TextInputDialog();
                    dateDialog.setHeaderText("Enter Due Date (YYYY-MM-DD):");
                    dateDialog.showAndWait().ifPresent(dueDate -> {
                        boolean result = bookManager.checkOutBook(barcode, dueDate);
                        showAlert(result, "Book Checked Out Successfully!", "Failed to Check Out Book!");
                    });
                } catch (NumberFormatException ex) {
                    showAlert(false, null, "Invalid Barcode!");
                }
            });
        });

        // Check In Book action
        checkInBookBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter the Barcode of the Book to Check In:");
            dialog.showAndWait().ifPresent(input -> {
                try {
                    int barcode = Integer.parseInt(input);
                    boolean result = bookManager.checkInBook(barcode);
                    showAlert(result, "Book Checked In Successfully!", "Failed to Check In Book!");
                } catch (NumberFormatException ex) {
                    showAlert(false, null, "Invalid Barcode!");
                }
            });
        });

        // Add components to layout
        layout.getChildren().addAll(tableView, viewBooksBtn, removeBookBtn, checkOutBookBtn, checkInBookBtn);

        // Create and show the scene
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a TableColumn for the TableView.
     *
     * @param title The title of the column.
     * @param index The index of the data in the row array.
     * @return A configured TableColumn.
     */
    private TableColumn<String[], String> createColumn(String title, int index) {
        TableColumn<String[], String> column = new TableColumn<>(title);
        column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[index]));
        return column;
    }

/**
 * Shows an alert dialog based on operation success.
 *
 * @param success Indicates if the operation was successful.
 * @param successMessage Message to display on success.
 *