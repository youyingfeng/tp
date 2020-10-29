package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_PHONE;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Updates the fields of an existing person in the address book.
 */
public class UpdateClientCommand extends Command {

    public static final String COMMAND_WORD = "update client";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the person identified "
            + "by the index number provided.\n"
            + "Parameters: INDEX (must be a positive integer representing a client in LogOnce; must not be empty) "
            + UPDATE_CLIENT_PREFIX_NAME + "NAME "
            + UPDATE_CLIENT_PREFIX_ADDRESS + "ADDRESS "
            + UPDATE_CLIENT_PREFIX_EMAIL + "EMAIL "
            + UPDATE_CLIENT_PREFIX_PHONE + "PHONE"
            + "Example: " + COMMAND_WORD + " 1 " + CLIENT_PREFIX_NAME + "Peter";

    public static final String MESSAGE_UPDATE_CLIENT_SUCCESS = "Updated Client: %1$s";

    private final Index targetIndex;
    private Name name;
    private Address address;
    private Email email;
    private Phone phone;

    /**
     * @param targetIndex index of the client the user would like to modify
     * @param name (if applicable) modified name of the client
     * @param address (if applicable) modified address of the client
     * @param email (if applicable) modified email of the client
     * @param phone (if applicable) modified phone of the client
     */
    public UpdateClientCommand(Index targetIndex, Name name, Address address, Email email, Phone phone) {
        this.targetIndex = targetIndex;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToUpdate = lastShownList.get(targetIndex.getZeroBased());
        if (Objects.isNull(name)) {
            name = clientToUpdate.getName();
        }
        if (Objects.isNull(address)) {
            address = clientToUpdate.getAddress();
        }
        if (Objects.isNull(email)) {
            email = clientToUpdate.getEmail();
        }
        if (Objects.isNull(phone)) {
            phone = clientToUpdate.getPhone();
        }
        List<Index> orderList = clientToUpdate.getOrders();
        Client newClient = new Client(name, phone, email, address, targetIndex, orderList);
        model.setPerson(clientToUpdate, newClient);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_UPDATE_CLIENT_SUCCESS, clientToUpdate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateClientCommand // instanceof handles nulls
                && targetIndex.equals(((UpdateClientCommand) other).targetIndex)); // state check
    }
}
