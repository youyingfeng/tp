package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Order;

public class UpdateOrderCommand extends Command {
    public static final String COMMAND_WORD = "update order";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the order identified ";

    public static final String MESSAGE_UPDATE_CLIENT_SUCCESS = "Updated Order: %1$s";
    private final Index orderId;
    private Index clientId;
    private String description;
    private Address address;
    private LocalDateTime dateTime;

    /**
     * @param orderId order to update
     * @param clientId (if applicable) modified client this order is tied to
     * @param description (if applicable) modified description of this order
     * @param address (if applicable) modified address of this order
     * @param dateTime (if applicable) modified LocalDateTime of this order
     */
    public UpdateOrderCommand(Index orderId, Index clientId, String description, Address address,
                              LocalDateTime dateTime) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.description = description;
        this.address = address;
        this.dateTime = dateTime;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (orderId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToUpdate = lastShownList.get(orderId.getZeroBased());
        if (Objects.isNull(clientId)) {
            clientId = orderToUpdate.getClientId();
        }
        if (Objects.isNull(description)) {
            description = orderToUpdate.getDescription();
        }
        if (Objects.isNull(dateTime)) {
            dateTime = orderToUpdate.getDeliveryDateTime();
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
