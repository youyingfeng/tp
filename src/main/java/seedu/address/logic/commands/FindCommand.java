package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.ClientMultiPredicate;

/**
 * Finds and lists all clients in client list of address book whose name contains any of the argument keywords.
 * Additional tokens can be used to narrow down the search: --phone, --address, --email.
 * These tokens can be used in any order.
 * A client has to match all the tokens in order to be displayed.
 * Address matching is done via keywords, where one keyword match is enough, similar to name.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]..."
            + " --phone PHONE --address KEYWORD MORE_KEYWORDS... --email EMAIL\n"
            + "Example: " + COMMAND_WORD + " alice bob" + "--address Jurong --phone 1234 --email team@gmail.com";

    private final ClientMultiPredicate predicate;

    /**
     * Creates a FindCommand with the supplied {@code ClientMultiPredicate} to check {@code Client} with.
     * @param predicate a {@code ClientMultiPredicate} used to test {@code Client} objects
     */
    public FindCommand(ClientMultiPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
