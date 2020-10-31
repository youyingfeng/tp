package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.DELETE_PREFIX_ORDER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Order;

/**
 * Deletes an order identified using it's displayed index from the address book.
 */
public class DeleteOrderCommand extends Command {

    public static final String COMMAND_WORD = "delete-order";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + DELETE_PREFIX_ORDER + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    private final Index targetIndex;

    public DeleteOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();
        int targetId = targetIndex.getOneBased();


        boolean isOrderFound = false;
        Order orderToDelete = null;
        for (Order order : lastShownList) {
            if (order.getOrderId().getZeroBased() == targetId) {
                // order is found with correct order id
                orderToDelete = order;
                isOrderFound = true;
                break;
            }
        }

        if (!isOrderFound) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        requireNonNull(orderToDelete);
        model.deleteOrder(orderToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteOrderCommand) other).targetIndex)); // state check
    }
}

