package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Order;

/**
 * Marks an order as done in order list of the addressbook.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_SUCCESS = "Marked as done. ";
    public static final String MESSAGE_ALREADY_DONE = "This order is already completed. ";
    public static final String MESSAGE_NOT_FOUND = "There is no order with this index number. ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " INDEX"
            + ": marks the order identified by the index number used in the displayed order list as done.\n"
            + "Parameters: INDEX must be a positive integer.\n"
            + "Example: " + COMMAND_WORD + " 1";

    private Index toMarkAsDoneIndex;

    /**
     *Creates an DoneCommand to mark the {@code Order} specified by {@code Index index} as done
     */
    public DoneCommand(Index index) {
        requireNonNull(index);
        toMarkAsDoneIndex = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();
        requireNonNull(lastShownList);
        for (Order order : lastShownList) {
            if (order.getOrderId().getZeroBased() == toMarkAsDoneIndex.getZeroBased()) {
                if (order.isDone()) {
                    throw new CommandException(MESSAGE_ALREADY_DONE);
                } else {
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    Order updatedOrder =
                            new Order(order.getOrderId(), order.getClientId(), order.getDescription(),
                                    order.getAddress(), order.getDeliveryDateTime(), order.getCreationDateTime(),
                                    currentDateTime, true);
                    model.setOrder(order, updatedOrder);
                    model.commitAddressBook();
                    return new CommandResult(MESSAGE_SUCCESS);
                }
            }
        }
        throw new CommandException(MESSAGE_NOT_FOUND);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof DoneCommand // instanceof handles nulls
                           && toMarkAsDoneIndex.equals(((DoneCommand) other).toMarkAsDoneIndex)); // state check
    }
}
