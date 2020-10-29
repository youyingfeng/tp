package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import seedu.address.model.Model;
import seedu.address.ui.MainWindow;

/**
 * Lists all orders in the address book to the user.
 */
public class ListOrderCommand extends Command {

    public static final String COMMAND_WORD = "listO";

    public static final String MESSAGE_SUCCESS = "Listed all orders";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        MainWindow.getInstance().handleOrders();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
