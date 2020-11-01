package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Order;

public class EditOrderEvent extends LogOnceEvent {
    public static final EventType<EditOrderEvent> EDIT_ORDER_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "EditOrderEvent");
    private final Order order;

    /**
     * Creates a new event that can pass the order around.
     */
    public EditOrderEvent(Order order) {
        super(EDIT_ORDER_EVENT_TYPE);
        this.order = order;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onEditOrderEvent(order);
    }
}
