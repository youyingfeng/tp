package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Order;

public class DeleteOrderEvent extends LogOnceEvent {
    public static final EventType<DeleteOrderEvent> DELETE_ORDER_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "DeleteOrderEvent");
    private final Order order;

    /**
     * Creates a new event that can pass the order around.
     */
    public DeleteOrderEvent(Order order) {
        super(DELETE_ORDER_EVENT_TYPE);
        this.order = order;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onDeleteOrderEvent(order);
    }
}
