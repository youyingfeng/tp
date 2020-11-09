package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_PHONE;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.model.person.Client;
import seedu.address.ui.events.EditEvent;

/**
 * A UI component that allows the user to edit a {@code Client}.
 */
public class EditClientForm extends UiPart<Region> {
    private static final String FXML = "EditClientForm.fxml";

    // copied over from email
    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "[^\\W_]"; // alphanumeric characters except underscore
    private static final String DOMAIN_MIDDLE_REGEX = "[a-zA-Z0-9.-]*"; // alphanumeric, period and hyphen
    private static final String DOMAIN_LAST_CHARACTER_REGEX = "[^\\W_]$";
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@"
            + DOMAIN_FIRST_CHARACTER_REGEX + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;

    private final Client client;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private Label nameErrorDisplay;

    @FXML
    private Label phoneErrorDisplay;

    @FXML
    private Label addressErrorDisplay;

    @FXML
    private Label emailErrorDisplay;

    /**
     * Creates a {@code EditClientForm}.
     */
    public EditClientForm(Client client) {
        super(FXML);
        this.client = client;
        nameField.setText(client.getName().fullName);
        phoneField.setText(client.getPhone().value);
        addressField.setText(client.getAddress().value);
        emailField.setText(client.getEmail().value);
    }

    // main issue here is getting mainwindow to display the result properly
    // thankfully cyclic dependencies are allowed

    @FXML
    private void submitForm() {
        try {
            validateInput();
            String commandString = "update-client "
                    + UPDATE_CLIENT_PREFIX_CLIENTID + " " + String.format("%05d", client.getClientId()) + " "
                    + UPDATE_CLIENT_PREFIX_NAME + " " + nameField.getText() + " "
                    + UPDATE_CLIENT_PREFIX_PHONE + " " + phoneField.getText() + " "
                    + UPDATE_CLIENT_PREFIX_ADDRESS + " " + addressField.getText() + " "
                    + UPDATE_CLIENT_PREFIX_EMAIL + " " + emailField.getText();

            this.getRoot().fireEvent(new EditEvent(commandString));
        } catch (ValidationException e) {
            // The behaviour for validationException is already handled properly in validateInput
            // ParseException and CommandException should not occur unless the command format changes,
            // which is highly unlikely.
            // Therefore this is left blank intentionally.
        }
    }

    @FXML
    private void resetForm() {
        nameField.setText(client.getName().fullName);
        phoneField.setText(client.getPhone().value);
        addressField.setText(client.getAddress().value);
        emailField.setText(client.getEmail().value);
    }

    private void validateInput() throws ValidationException {
        boolean isInputValid = true;
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            isInputValid = false;
            nameErrorDisplay.setText("Name cannot be blank!");
        } else {
            nameErrorDisplay.setText("");
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            isInputValid = false;
            phoneErrorDisplay.setText("Phone cannot be blank!");
        } else if (!phoneField.getText().matches("\\d{3,}")) {
            isInputValid = false;
            phoneErrorDisplay.setText("Phone number is invalid!");
        } else {
            phoneErrorDisplay.setText("");
        }

        if (addressField.getText() == null || addressField.getText().length() == 0) {
            isInputValid = false;
            addressErrorDisplay.setText("Address cannot be blank!");
        } else {
            addressErrorDisplay.setText("");
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            isInputValid = false;
            emailErrorDisplay.setText("Email cannot be blank!");
        } else if (!emailField.getText().matches(VALIDATION_REGEX)) {
            isInputValid = false;
            emailErrorDisplay.setText("Email provided is not of a valid format!");
        } else {
            emailErrorDisplay.setText("");
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
