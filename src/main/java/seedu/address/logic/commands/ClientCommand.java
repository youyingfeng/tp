package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_PHONE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Client;

/**
 * Adds a client to the address book.
 */
public class ClientCommand extends Command {
    public static final String COMMAND_WORD = "client";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to LogOnce. "
            + "Parameters: "
            + CLIENT_PREFIX_NAME + "NAME "
            + CLIENT_PREFIX_ADDRESS + "ADDRESS "
            + CLIENT_PREFIX_EMAIL + "EMAIL "
            + CLIENT_PREFIX_PHONE + "PHONE";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This client already exists in LogOnce";

    private final Client toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientCommand // instanceof handles nulls
                && toAdd.equals(((ClientCommand) other).toAdd));
    }
}
