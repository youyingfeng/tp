package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DESCRIPTION;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;

/**
 * Creates an order linked to an existing person in the address book.
 */
public class OrderCommand extends Command {

    public static final String COMMAND_WORD = "order";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates an order linked to an existing person in the address book.\n"
            + "Parameters: DESCRIPTION (string to describe the created order)"
            + " CLIENT_ID (must be a positive integer) ADDRESS (string to describe the delivery address)"
            + " DATE (date of delivery in yyyy-MM-dd hhmm).\n"
            + "Example: " + COMMAND_WORD + " " + ORDER_PREFIX_DESCRIPTION + " shoes " + ORDER_PREFIX_CLIENT + " 123"
            + ORDER_PREFIX_ADDRESS + " Jurong West Central 3" + ORDER_PREFIX_DATE + " 2020-11-30 2359";

    public static final String MESSAGE_SUCCESS = "New Order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This Order already exists in LogOnce";
    public static final String MESSAGE_NONEXISTENT_CLIENT = "This Order is linked to a client which does not exist";

    private final Order toAdd;

    /**
     * Creates an OrderCommand to add the specified {@code Order}
     */
    public OrderCommand(Order order) {
        requireAllNonNull(order);

        this.toAdd = order;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        int targetClientId = toAdd.getClientId().getZeroBased();
        boolean isExistingClient = false;
        ObservableList<Client> tempClientList = model.getFilteredPersonList();

        // loop through client list to check if client linked to order exists
        for (Client client : tempClientList) {
            int currentClientId = client.getClientId();
            if (currentClientId == targetClientId) {
                isExistingClient = true;
                break;
            }
        }

        if (!isExistingClient) {
            throw new CommandException(MESSAGE_NONEXISTENT_CLIENT);
        }

        model.addOrder(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderCommand // instanceof handles nulls
                && toAdd.equals(((OrderCommand) other).toAdd)); // same as ClientCommand's equals
    }
}
