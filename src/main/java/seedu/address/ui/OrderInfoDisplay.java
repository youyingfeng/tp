package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.DEFAULT_DATE_TIME_FORMATTER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.person.Order;
import seedu.address.ui.events.DeletionEvent;
import seedu.address.ui.events.EditOrderEvent;
import seedu.address.ui.events.OrderCompleteEvent;


public class OrderInfoDisplay extends UiPart<Region> {
    private static final String FXML = "OrderInfoDisplay.fxml";

    private final Order order;

    @FXML
    private Label orderId;

    @FXML
    private Text description;

    @FXML
    private Text client;

    @FXML
    private Text address;

    @FXML
    private Text date;

    @FXML
    private Text completionStatus;

    OrderInfoDisplay(Order order) {
        super(FXML);
        this.order = order;
        orderId.setText("Order #" + String.format("%05d", order.getOrderId().getZeroBased()));
        description.setText(order.getDescription());
        client.setText(String.format("%05d", order.getClientId().getZeroBased()));
        address.setText(order.getAddress().value);
        date.setText(order.getDeliveryDateTime().format(DEFAULT_DATE_TIME_FORMATTER));

        if (order.isDone()) {
            completionStatus.setText("Completed");
        } else {
            completionStatus.setText("Incomplete");
        }
    }

    @FXML
    private void markOrderDone() {
        this.getRoot().fireEvent(new OrderCompleteEvent(order));
    }

    @FXML
    private void editOrder() {
        this.getRoot().fireEvent(new EditOrderEvent(order));
    }

    @FXML
    private void deleteOrder() {
        this.getRoot().fireEvent(new DeletionEvent("delete-order --order "
                + String.format("%05d", order.getOrderId().getZeroBased())));
    }
}
