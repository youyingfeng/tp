package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.DEFAULT_DATE_TIME_FORMATTER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.person.Order;


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

    OrderInfoDisplay(Order order) {
        super(FXML);
        this.order = order;
        orderId.setText("Order #" + String.format("%05d", order.getOrderId().getZeroBased()));
        description.setText(order.getDescription());
        client.setText(String.format("%05d", order.getClientId().getZeroBased()));
        address.setText(order.getAddress().value);
        date.setText(order.getDeliveryDateTime().format(DEFAULT_DATE_TIME_FORMATTER));
    }
}
