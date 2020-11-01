package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.DEFAULT_DATE_TIME_FORMATTER;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import seedu.address.model.person.Order;
import seedu.address.ui.events.DisplayOrderEvent;

/**
 * An UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label orderId;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label clientId;
    @FXML
    private Label date;
    @FXML
    private Rectangle completed;

    /**
     * Creates a {@code OrderCode} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        orderId.setText("Order #" + String.format("%05d", order.getOrderId().getZeroBased()));
        description.setText(order.getDescription());
        clientId.setText("Deliver to Client #" + String.format("%05d", order.getClientId().getZeroBased()));
        date.setText("Delivery date: " + order.getDeliveryDateTime().format(DEFAULT_DATE_TIME_FORMATTER));
        setEventHandlers(this.getRoot());
        completed.heightProperty().bind(cardPane.heightProperty());

        if (order.isDone()) {
            completed.setFill(Color.GREEN);
        } else {
            completed.setFill(Color.RED);
        }
    }

    private void setEventHandlers(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            node.fireEvent(new DisplayOrderEvent(order));
        });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
