package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.model.Model;
import seedu.address.ui.MainWindow;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "listC";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private static Logger logger = Logger.getLogger("list");

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "start processing");
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        MainWindow.getInstance().handleClients();
        logger.log(Level.INFO, "end processing");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
