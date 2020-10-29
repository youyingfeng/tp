package seedu.address.ui.events;

import javafx.event.EventType;

public class DeletionEvent extends LogOnceEvent {
    public static final EventType<LogOnceEvent> DELETION_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "DeletionEvent");
    private final String deleteCommand;

    /**
     * Creates a new event that can pass the client around.
     */
    public DeletionEvent(String deleteCommand) {
        super(DELETION_EVENT_TYPE);
        this.deleteCommand = deleteCommand;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onDeletionEvent(deleteCommand);
    }
}
