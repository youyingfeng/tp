package seedu.address.ui.events;

import javafx.event.EventHandler;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;

public abstract class LogOnceEventHandler implements EventHandler<LogOnceEvent> {
    public abstract void onDisplayOrderEvent(Order order);

    public abstract void onDisplayClientEvent(Client client);

    public abstract void onDeletionEvent(String command);

    @Override
    public void handle(LogOnceEvent event) {
        event.invokeHandler(this);
    }
}
