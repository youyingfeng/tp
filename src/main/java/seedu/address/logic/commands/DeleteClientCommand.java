package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.DELETE_PREFIX_CLIENT;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Client;

/**
 * Deletes a client identified using it's unique client ID displayed in the client list of the address book.
 */
public class DeleteClientCommand extends Command {

    public static final String COMMAND_WORD = "delete-client";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by the unique client ID in the displayed client list.\n"
            + "Parameters: CLIENT INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + DELETE_PREFIX_CLIENT + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Deleted Client: %1$s";

    private final Index targetIndex;

    public DeleteClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredPersonList();
        int targetId = targetIndex.getZeroBased();


        boolean isClientFound = false;
        Client clientToDelete = null;
        for (Client client : lastShownList) {
            if (client.getClientId() == targetId) {
                // client is found with correct client id
                clientToDelete = client;
                isClientFound = true;
                break;
            }
        }

        if (!isClientFound) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_INDEX);
        }

        requireNonNull(clientToDelete);
        model.deletePerson(clientToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClientCommand) other).targetIndex)); // state check
    }
}

