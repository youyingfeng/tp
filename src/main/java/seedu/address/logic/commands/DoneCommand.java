package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Order;

/**
 * Marks an order as done in the addressbook.
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
            if (order.getOrderId().getZeroBased() == toMarkAsDoneIndex.getOneBased()) {
                if (order.isDone()) {
                    return new CommandResult(MESSAGE_ALREADY_DONE);
                } else {
                    order.markAsDone();
                    model.commitAddressBook();
                    return new CommandResult(MESSAGE_SUCCESS);
                }
            }
        }
        return new CommandResult(MESSAGE_NOT_FOUND);
    }
}
