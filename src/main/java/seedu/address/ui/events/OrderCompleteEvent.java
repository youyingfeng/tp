package seedu.address.ui.events;

import javafx.event.EventType;
import seedu.address.model.person.Order;

public class OrderCompleteEvent extends LogOnceEvent {
    public static final EventType<OrderCompleteEvent> ORDER_COMPLETE_EVENT_TYPE =
            new EventType<>(LOGONCE_EVENT_TYPE, "OrderCompleteEvent");
    private final Order order;

    /**
     * Creates a new event that can pass the order around.
     */
    public OrderCompleteEvent(Order order) {
        super(ORDER_COMPLETE_EVENT_TYPE);
        this.order = order;
    }

    @Override
    public void invokeHandler(LogOnceEventHandler handler) {
        handler.onOrderCompleteEvent(order);
    }
}
