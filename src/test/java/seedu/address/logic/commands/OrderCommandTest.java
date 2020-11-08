package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.SHOES;
import static seedu.address.testutil.TypicalOrders.SKIRT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class OrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();
    private OrderCommand validOrderCommandShoes = new OrderCommand(SHOES);
    private OrderCommand validOrderCommandSkirt = new OrderCommand(SKIRT);

    @Test
    public void equals() {
        assertTrue(validOrderCommandShoes.equals(validOrderCommandShoes));

        // construct from same source command -> return true
        OrderCommand validCommandCopy = new OrderCommand(SHOES);
        assertTrue(validOrderCommandShoes.equals(validCommandCopy));

        // other type -> return false
        assertFalse(validOrderCommandShoes.equals(5));

        // null -> return false
        assertFalse(validOrderCommandShoes.equals(null));

        // other command from different order -> return false
        assertFalse(validOrderCommandShoes.equals(validOrderCommandSkirt));
    }

    @Test
    public void execute_uniqueOrders_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Start with empty addressbook
        assertTrue(expectedModel.getUnfilteredOrderList().size() == 0);

        try {
            validOrderCommandShoes.execute(expectedModel, commandHistory);
        } catch (CommandException e) {
            assert false;
        }

        // Addressbook should contain 1 order
        assertTrue(expectedModel.getUnfilteredOrderList().size() == 1);

        try {
            validOrderCommandSkirt.execute(expectedModel, commandHistory);
        } catch (CommandException e) {
            assert false;
        }

        // Addressbook should contain 2 orders
        assertTrue(expectedModel.getUnfilteredOrderList().size() == 2);
    }

    @Test
    public void execute_duplicateOrders_throwsCommandException() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Start with empty addressbook
        assertTrue(expectedModel.getUnfilteredOrderList().size() == 0);

        try {
            validOrderCommandShoes.execute(expectedModel, commandHistory);
        } catch (CommandException e) {
            assert false;
        }

        // Addressbook should contain 1 order
        assertTrue(expectedModel.getUnfilteredOrderList().size() == 1);

        try {
            validOrderCommandShoes.execute(expectedModel, commandHistory);

            // the statement below should be unreachable unless the command execution above is successful
            assert false;
        } catch (CommandException e) {
            assert true;
        }

        // Addressbook should contain 2 orders
        assertTrue(expectedModel.getUnfilteredOrderList().size() == 1);
    }
}
