package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Client;

public class DisplayClientEvent extends LogOnceEvent {
    public static final EventType<LogOnceEvent> DISPLAY_CLIENT_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "DisplayClientEvent");
    private final Client client;

    /**
     * Creates a new event that can pass the client around.
     */
    public DisplayClientEvent(Client client) {
        super(DISPLAY_CLIENT_EVENT_TYPE);
        this.client = client;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onDisplayClientEvent(client);
    }
}
