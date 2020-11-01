package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.DEFAULT_DATE_TIME_FORMATTER;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ORDERID;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.model.person.Order;
import seedu.address.ui.events.EditEvent;


public class EditOrderForm extends UiPart<Region> {
    private static final String FXML = "EditOrderForm.fxml";

    private Order order;

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
    public EditOrderForm(Order order) {
        super(FXML);
        this.order = order;
        descriptionField.setText(order.getDescription());
        clientField.setText(String.format("%05d", order.getClientId().getZeroBased()));
        addressField.setText(order.getAddress().value);
        dateField.setText(order.getDeliveryDateTime().format(DEFAULT_DATE_TIME_FORMATTER));
    }

    // main issue here is getting mainwindow to display the result properly
    // thankfully cyclic dependencies are allowed

    @FXML
    private void submitForm() {
        try {
            validateInput();
            String commandString = "update-order "
                    + UPDATE_ORDER_PREFIX_ORDERID + " " + String.format("%05d", order.getOrderId().getZeroBased()) + " "
                    + UPDATE_ORDER_PREFIX_DESCRIPTION + " " + descriptionField.getText() + " "
                    + UPDATE_ORDER_PREFIX_CLIENTID + " " + clientField.getText() + " "
                    + UPDATE_ORDER_PREFIX_ADDRESS + " " + addressField.getText() + " "
                    + UPDATE_ORDER_PREFIX_DATE + " " + dateField.getText();

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
        descriptionField.setText(order.getDescription());
        clientField.setText(String.format("%05d", order.getClientId().getZeroBased()));
        addressField.setText(order.getAddress().value);
        dateField.setText(order.getDeliveryDateTime().format(DEFAULT_DATE_TIME_FORMATTER));
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
