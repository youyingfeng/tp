package seedu.address.logic.commands;

import java.time.LocalDateTime;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Address;

/**
 * Contains the fields that are to replace the current existing order. If the field is empty, it will simply be
 * the field of the existing order.
 */
public class UpdatedOrderFields {
    private Index orderId;
    private Index clientId;
    private String description;
    private Address address;
    private LocalDateTime dateTime;

    /**
     * Constructor for UpdatedOrderFields object. This object is used to contain the fields (if any) to be passed on
     * to the UpdateOrderCommand object.
     */
    public UpdatedOrderFields() {
    }

    public void setClientId(Index clientId) {
        this.clientId = clientId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setOrderId(Index orderId) {
        this.orderId = orderId;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<Index> getClientId() {
        return Optional.ofNullable(clientId);
    }

    public Optional<LocalDateTime> getDateTime() {
        return Optional.ofNullable(dateTime);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<Index> getOrderId() {
        return Optional.ofNullable(orderId);
    }
}
