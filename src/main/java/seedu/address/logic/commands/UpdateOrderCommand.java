package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Order;

public class UpdateOrderCommand extends Command {
    public static final String COMMAND_WORD = "update-order";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the order identified ";

    public static final String MESSAGE_UPDATE_CLIENT_SUCCESS = "Updated Order: %1$s";
    private UpdatedOrderFields fieldsToUpdate;
    private Index orderId;

    /**
     * @param fieldsToUpdate contains all the fields to update for the respective Order object.
     */
    public UpdateOrderCommand(UpdatedOrderFields fieldsToUpdate) {
        this.fieldsToUpdate = fieldsToUpdate;
        this.orderId = fieldsToUpdate.getOrderId().get();
    }

    public Index getOrderId() {
        return orderId;
    }

    public UpdatedOrderFields getFieldsToUpdate() {
        return fieldsToUpdate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getUnfilteredOrderList();

        if (fieldsToUpdate.getOrderId().get().getZeroBased()
                > lastShownList.get(lastShownList.size() - 1).getOrderId().getZeroBased()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_INDEX);
        }

        Order orderToUpdate = null;

        for (Order order : lastShownList) {
            if (order.getOrderId().getZeroBased() == orderId.getZeroBased()) {
                orderToUpdate = order;
                break;
            }
        }

        if (orderToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_INDEX);
        }

        Index clientId;
        if (fieldsToUpdate.getClientId().isEmpty()) {
            clientId = orderToUpdate.getClientId();
        } else {
            clientId = fieldsToUpdate.getClientId().get();
        }

        String description;
        if (fieldsToUpdate.getDescription().isEmpty()) {
            description = orderToUpdate.getDescription();
        } else {
            description = fieldsToUpdate.getDescription().get();
        }

        LocalDateTime dateTime;
        if (fieldsToUpdate.getDateTime().isEmpty()) {
            dateTime = orderToUpdate.getDeliveryDateTime();
        } else {
            dateTime = fieldsToUpdate.getDateTime().get();
        }

        Address address;
        if (fieldsToUpdate.getAddress().isEmpty()) {
            address = orderToUpdate.getAddress();
        } else {
            address = fieldsToUpdate.getAddress().get();
        }

        LocalDateTime creationDateTime = orderToUpdate.getCreationDateTime();
        LocalDateTime lastModifiedDateTime = LocalDateTime.now();
        boolean isDone = orderToUpdate.getIsDone();
        Order newOrder = new Order(orderId, clientId, description, address, dateTime, creationDateTime,
                lastModifiedDateTime, isDone);
        model.setOrder(orderToUpdate, newOrder);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_UPDATE_CLIENT_SUCCESS, orderToUpdate));
    }
}

