package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Client;

public class EditClientEvent extends LogOnceEvent {
    public static final EventType<LogOnceEvent> EDIT_CLIENT_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "EditClientEvent");
    private final Client client;

    /**
     * Creates a new event that can pass the client around.
     */
    public EditClientEvent(Client client) {
        super(EDIT_CLIENT_EVENT_TYPE);
        this.client = client;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onEditClientEvent(client);
    }
}
