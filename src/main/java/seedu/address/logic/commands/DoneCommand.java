package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.DONE_PREFIX_ORDER;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Order;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_SUCCESS = "Marked as done. ";
    public static final String MESSAGE_ALREADY_DONE = "This order is already completed. ";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": marks the order identified by the index number used in the displayed order list as done.\n"
            + "Parameters: INDEX must be a positive integer.\n"
            + "Example: " + COMMAND_WORD + " " + DONE_PREFIX_ORDER + " 1";

    private Index toMarkAsDoneIndex;

    /**
     *Creates an DoneCommand to mark the {@code Order} specified by {@code Index index} as done
     */
    public DoneCommand(Index index) {
        requireNonNull(index);
        toMarkAsDoneIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();
        Order orderToMark = lastShownList.get(toMarkAsDoneIndex.getZeroBased());
        if (orderToMark.isDone()) {
            throw new CommandException(MESSAGE_ALREADY_DONE);
        } else {
            orderToMark.markAsDone();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
