package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Client;

public class DisplayClientEvent extends LogOnceEvent {
    public static final EventType<LogOnceEvent> DISPLAY_CLIENT_EVENT_TYPE =
            new EventType<>(ANY, "DisplayClientEvent");
    private final Client client;

    public DisplayClientEvent(Client client) {
        super(DISPLAY_CLIENT_EVENT_TYPE);
        this.client = client;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onDisplayClientEvent(client);
    }
}
