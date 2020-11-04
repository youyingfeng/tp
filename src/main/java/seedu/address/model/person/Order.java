package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.DEFAULT_DATE_TIME_FORMATTER;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;


public class Order {
    public static final String MESSAGE_CONSTRAINTS =
            "Order date should be in the format yyyy-mm-dd";

    private Index orderId;
    private final Index clientId;
    private final String description;
    private final Address address;
    private final LocalDateTime deliveryDateTime;
    private final LocalDateTime creationDateTime;
    private final LocalDateTime lastModifiedDateTime;
    private boolean isDone;

    /**
     * Constructor which returns an order object with additional fields
     * @param description of the order
     * @param clientId id of the client this order is attached to
     */
    public Order(Index clientId,
                 String description,
                 Address address,
                 LocalDateTime deliveryDateTime,
                 LocalDateTime creationDateTime,
                 LocalDateTime lastModifiedDateTime,
                 boolean isDone) {
        this.orderId = Index.fromZeroBased(0);
        this.clientId = Index.fromZeroBased(clientId.getZeroBased());
        this.description = description;
        this.address = address;
        this.deliveryDateTime = deliveryDateTime;
        this.creationDateTime = creationDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.isDone = isDone;
    }

    /**
     * Constructor which returns an order object with additional fields
     */
    public Order(Index orderId,
                 Index clientId,
                 String description,
                 Address address,
                 LocalDateTime deliveryDateTime,
                 LocalDateTime creationDateTime,
                 boolean isDone) {
        this.orderId = orderId;
        this.clientId = Index.fromZeroBased(clientId.getZeroBased());
        this.description = description;
        this.address = address;
        this.deliveryDateTime = deliveryDateTime;
        this.creationDateTime = creationDateTime;
        this.lastModifiedDateTime = creationDateTime;
        this.isDone = isDone;
    }

    /**
     * Constructor which returns an order object with additional fields
     * This constructor takes in an additional field (orderId)
     */
    public Order(Index orderId,
                 Index clientId,
                 String description,
                 Address address,
                 LocalDateTime deliveryDateTime,
                 LocalDateTime creationDateTime,
                 LocalDateTime lastModifiedDateTime,
                 boolean isDone) {
        this.orderId = orderId;
        this.clientId = Index.fromZeroBased(clientId.getZeroBased());
        this.description = description;
        this.address = address;
        this.deliveryDateTime = deliveryDateTime;
        this.creationDateTime = creationDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.isDone = isDone;
    }

    public Index getOrderId() {
        return orderId;
    }

    public Index getClientId() {
        return this.clientId;
    }

    public String getDescription() {
        return this.description;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns true if both orders of the same id have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        return otherOrder == this;
    }

    public void setOrderId(int orderId) {
        this.orderId = Index.fromZeroBased(orderId);
    }

    /**
     * Marks this {@code Order} as done
     */
    public void markAsDone() {
        // feel free to change - tx
        isDone = true;
        // TODO: Refactor to use copy constructor?
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        final String stringRepresentation = "Order #" + String.format("%05d", getOrderId().getZeroBased()) + " "
                + " Description: " + description
                + " Client: #" + String.format("%05d", getClientId().getZeroBased())
                + " Address: " + address
                + " Deliver by: " + deliveryDateTime.format(DEFAULT_DATE_TIME_FORMATTER);
        return stringRepresentation;
    }
}
