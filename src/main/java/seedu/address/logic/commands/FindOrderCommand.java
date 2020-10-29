package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.OrderMultiPredicate;

/**
 * Finds and lists all orders in address book whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders whose descriptions contain any of "
                                                       + "the specified keywords (case-insensitive) and displays "
                                                       + "them as a list with index numbers.\n"
                                                       + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
                                                       + "Example: " + COMMAND_WORD + "phone keyboard mouse";

    private final OrderMultiPredicate predicate;

    public FindOrderCommand(OrderMultiPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        assert other != null;
        return other == this // short circuit if same object
                       || (other instanceof FindOrderCommand // instanceof handles nulls
                                   && predicate.equals(((FindOrderCommand) other).predicate)); // state check
    }
}
