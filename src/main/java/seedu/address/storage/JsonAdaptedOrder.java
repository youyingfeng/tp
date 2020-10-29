package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Order;



/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";
    private final int orderId;
    private final int clientId;
    private final String description;
    private final String address;
    private final String deliveryDateTime;
    private final String creationDateTime;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("orderId") int orderId, @JsonProperty("clientId") int clientId,
                            @JsonProperty("description") String description, @JsonProperty("address") String address,
                            @JsonProperty("deliveryDateTime") String deliveryDateTime,
                            @JsonProperty("creationDateTime") String creationDateTime,
                            @JsonProperty("isDone") boolean isDone) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.description = description;
        this.address = address;
        this.deliveryDateTime = deliveryDateTime;
        this.creationDateTime = creationDateTime;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        this.orderId = source.getOrderId().getZeroBased();
        this.clientId = source.getClientId().getZeroBased();
        this.description = source.getDescription();
        this.address = source.getAddress().toString();
        this.deliveryDateTime = source.getDeliveryDateTime().toString();
        this.creationDateTime = source.getCreationDateTime().toString();
        this.isDone = source.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        // Field declarations

        final Index modelOrderId;
        final Index modelClientId;
        final Address modelAddress;
        final LocalDateTime modelDeliveryDateTime;
        final LocalDateTime modelCreationDateTime;

        // Safety checks and value assignment

        if (orderId <= 0) {
            throw new IllegalValueException("Invalid value for order ID!");
        }
        modelOrderId = Index.fromZeroBased(orderId);

        if (clientId <= 0) {
            throw new IllegalValueException("Invalid value for client ID!");
        }
        // TODO: find a better fix for the bug where client id tied to order increments on launch
        modelClientId = Index.fromOneBased(clientId);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        modelAddress = new Address(address);

        if (deliveryDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        modelDeliveryDateTime = LocalDateTime.parse(deliveryDateTime);

        if (creationDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        modelCreationDateTime = LocalDateTime.parse(creationDateTime);

        return new Order(modelOrderId,
                         modelClientId,
                         description,
                         modelAddress,
                         modelDeliveryDateTime,
                         modelCreationDateTime,
                         isDone);
    }

}
