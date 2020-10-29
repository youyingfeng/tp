package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Order;

public class DisplayOrderEvent extends LogOnceEvent {
    public static final EventType<DisplayOrderEvent> DISPLAY_ORDER_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "DisplayOrderEvent");
    private final Order order;

    /**
     * Creates a new event that can pass the order around.
     */
    public DisplayOrderEvent(Order order) {
        super(DISPLAY_ORDER_EVENT_TYPE);
        this.order = order;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onDisplayOrderEvent(order);
    }
}
