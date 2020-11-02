package seedu.address.ui.events;

import javafx.event.EventType;

public class EditEvent extends LogOnceEvent {
    public static final EventType<LogOnceEvent> EDIT_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "EditEvent");
    private final String editCommand;

    /**
     * Creates a new event that can pass the client around.
     */
    public EditEvent(String editCommand) {
        super(EDIT_EVENT_TYPE);
        this.editCommand = editCommand;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onEditEvent(editCommand);
    }
}
