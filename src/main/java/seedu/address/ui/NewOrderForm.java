package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DESCRIPTION;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

public class NewOrderForm extends UiPart<Region> {
    private static final String FXML = "NewOrderForm.fxml";

    private final MainWindow mainWindow;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField clientField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField dateField;

    @FXML
    private Label descriptionErrorDisplay;

    @FXML
    private Label clientErrorDisplay;

    @FXML
    private Label addressErrorDisplay;

    @FXML
    private Label dateErrorDisplay;

    /**
     * Creates a {@code NewClientForm}.
     */
    public NewOrderForm(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
    }

    // main issue here is getting mainwindow to display the result properly
    // thankfully cyclic dependencies are allowed

    @FXML
    private void submitForm() {
        try {
            validateInput();
            String commandString = "order "
                    + ORDER_PREFIX_DESCRIPTION + " " + descriptionField.getText() + " "
                    + ORDER_PREFIX_CLIENT + " " + clientField.getText() + " "
                    + ORDER_PREFIX_ADDRESS + " " + addressField.getText() + " "
                    + ORDER_PREFIX_DATE + " " + dateField.getText();

            mainWindow.executeCommand(commandString);
            resetForm();
        } catch (ValidationException | ParseException | CommandException e) {
            // The behaviour for validationException is already handled properly in validateInput
            // ParseException and CommandException should not occur unless the command format changes,
            // which is highly unlikely.
            // Therefore this is left blank intentionally.
        }
    }

    @FXML
    private void resetForm() {
        descriptionField.clear();
        clientField.clear();
        addressField.clear();
        dateField.clear();
    }

    private void validateInput() throws ValidationException {
        boolean isInputValid = true;
        if (descriptionField.getText() == null || descriptionField.getText().length() == 0) {
            isInputValid = false;
            descriptionErrorDisplay.setText("Description cannot be blank!");
        } else {
            descriptionErrorDisplay.setText("");
        }

        if (clientField.getText() == null || dateField.getText().length() == 0) {
            isInputValid = false;
            clientErrorDisplay.setText("Client ID cannot be blank!");
        } else if (!clientField.getText().matches("\\d{5}")) {
            // this should mean "repeat digit 5 times"
            isInputValid = false;
            clientErrorDisplay.setText("Enter a valid 5-digit client ID!");
        } else {
            clientErrorDisplay.setText("");
        }

        if (addressField.getText() == null || dateField.getText().length() == 0) {
            isInputValid = false;
            addressErrorDisplay.setText("Address cannot be blank!");
        } else {
            addressErrorDisplay.setText("");
        }

        if (dateField.getText() == null || dateField.getText().length() == 0) {
            isInputValid = false;
            dateErrorDisplay.setText("Date cannot be blank!");
        } else {
            dateErrorDisplay.setText("");
        }

        if (!isInputValid) {
            throw new ValidationException("Invalid input!");
        }
    }

    static class ValidationException extends Exception {
        ValidationException(String message) {
            super(message);
        }
    }
}
