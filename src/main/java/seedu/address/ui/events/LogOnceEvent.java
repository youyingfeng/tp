package seedu.address.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

public abstract class LogOnceEvent extends Event {
    public static final EventType<LogOnceEvent> LOGONCE_EVENT_TYPE = new EventType<>(ANY);

    public LogOnceEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public abstract void invokeHandler(LogOnceEventHandler handler);
}
