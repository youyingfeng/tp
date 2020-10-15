package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_CREATE_ORDER_SUCCESS;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
            + "CLIENT_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + ORDER_PREFIX_DESCRIPTION + " shoes " + ORDER_PREFIX_CLIENT + " 123";

    public static final String MESSAGE_ARGUMENTS = "Description: %1$s, Client id: %2$d";

    private final Index clientId;
    private final String description;

    /**
     * @param description of the order
     * @param clientId of the person the order is linked to
     */
    public OrderCommand(String description, Index clientId) {
        requireAllNonNull(description, clientId);

        this.description = description;
        this.clientId = clientId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Client> lastShownList = model.getFilteredPersonList();

        if (clientId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client personToEdit = lastShownList.get(clientId.getZeroBased());
        List<Order> orderList = personToEdit.getOrderList();
        orderList.add(new Order(description, clientId));
        Client editedPerson = new Client(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), model, personToEdit.getTags(), orderList);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_CREATE_ORDER_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCommand)) {
            return false;
        }

        // state check
        OrderCommand e = (OrderCommand) other;
        return clientId.equals(e.clientId)
                && description.equals(e.description);
    }
}
