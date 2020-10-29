package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Client;

public class DeleteClientEvent extends LogOnceEvent {
    public static final EventType<LogOnceEvent> DELETE_CLIENT_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "DeleteClientEvent");
    private final Client client;

    /**
     * Creates a new event that can pass the client around.
     */
    public DeleteClientEvent(Client client) {
        super(DELETE_CLIENT_EVENT_TYPE);
        this.client = client;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onDeleteClientEvent(client);
    }
}
