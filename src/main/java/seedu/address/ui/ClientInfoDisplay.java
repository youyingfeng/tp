package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.person.Client;
import seedu.address.ui.events.DeletionEvent;
import seedu.address.ui.events.EditClientEvent;

public class ClientInfoDisplay extends UiPart<Region> {
    private static final String FXML = "ClientInfoDisplay.fxml";

    private final Client client;

    @FXML
    private Label clientId;

    @FXML
    private Text name;

    @FXML
    private Text phone;

    @FXML
    private Text address;

    @FXML
    private Text email;

    ClientInfoDisplay(Client client) {
        super(FXML);
        this.client = client;
        clientId.setText("Client #" + String.format("%05d", client.getClientId()));
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
    }

    @FXML
    private void editClient() {
        this.getRoot().fireEvent(new EditClientEvent(client));
    }

    @FXML
    private void deleteClient() {
        this.getRoot().fireEvent(new DeletionEvent("delete-client --client "
                + String.format("%05d", client.getClientId())));
    }
}
