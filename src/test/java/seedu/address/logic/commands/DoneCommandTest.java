package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Order;

/**
 * Contains integration tests (interaction with the Model) for {@code DoneCommand}.
 */
public class DoneCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();
    private Order firstTestOrder = new Order(Index.fromZeroBased(0),
            Index.fromZeroBased(1),
            "testOrder",
            new Address("Test Address"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            false);
    private Order secondTestOrder = new Order(Index.fromZeroBased(0),
            Index.fromZeroBased(1),
            "testOrder2",
            new Address("Test Address"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            false);

    @Test
    public void equals() {
        Index firstIndex = Index.fromZeroBased(1);
        Index secondIndex = Index.fromZeroBased(2);
        DoneCommand firstCommand = new DoneCommand(firstIndex);
        DoneCommand secondCommand = new DoneCommand(secondIndex);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same value -> returns true
        DoneCommand firstCopy = new DoneCommand(firstIndex);
        assertTrue(firstCommand.equals(firstCopy));

        // different type -> returns false
        assertFalse(firstCommand.equals(firstIndex));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different commands with different index -> returns false
        assertFalse(firstCommand.equals(secondCommand));

    }

    @Test
    public void execute_success() {
        DoneCommand firstCommand = new DoneCommand(Index.fromZeroBased(1));
        DoneCommand secondCommand = new DoneCommand(Index.fromZeroBased(2));
        model.addOrder(firstTestOrder);
        model.addOrder(secondTestOrder);

        try {
            String result = firstCommand.execute(model, commandHistory).getFeedbackToUser();
        } catch (CommandException e) {
            assert false;
        }

        // first command executed -> order with ID #00001 is done, but order with ID #00002 is not
        Order newFirstOrder = model.getUnfilteredOrderList().get(0);
        Order newSecondOrder = model.getUnfilteredOrderList().get(1);
        assertTrue(newFirstOrder.isDone());
        assertFalse(newSecondOrder.isDone());

        // second command executed -> both orders are done
        try {
            String result = secondCommand.execute(model, commandHistory).getFeedbackToUser();
        } catch (CommandException e) {
            assert false;
        }
        newSecondOrder = model.getUnfilteredOrderList().get(1);
        assertTrue(newSecondOrder.isDone());
    }

    @Test
    public void execute_orderNotFound_throwsCommandException() {
        DoneCommand firstCommand = new DoneCommand(Index.fromZeroBased(2));
        DoneCommand secondCommand = new DoneCommand(Index.fromZeroBased(0));
        DoneCommand thirdCommand = new DoneCommand(Index.fromZeroBased(100));

        // firstTestOrder will have an ID of #00001
        model.addOrder(firstTestOrder);

        assertThrows(CommandException.class,
            DoneCommand.MESSAGE_NOT_FOUND, () -> firstCommand.execute(model, commandHistory));
        assertThrows(CommandException.class,
            DoneCommand.MESSAGE_NOT_FOUND, () -> secondCommand.execute(model, commandHistory));
        assertThrows(CommandException.class,
            DoneCommand.MESSAGE_NOT_FOUND, () -> thirdCommand.execute(model, commandHistory));
    }

    @Test
    public void execute_orderAlreadyDone_throwsCommandException() {
        DoneCommand firstCommand = new DoneCommand(Index.fromZeroBased(1));
        model.addOrder(firstTestOrder);

        try {
            String result = firstCommand.execute(model, commandHistory).getFeedbackToUser();
        } catch (CommandException e) {
            assert false;
        }

        Order newFirstOrder = model.getUnfilteredOrderList().get(0);
        assertTrue(newFirstOrder.isDone());
        assertThrows(CommandException.class,
            DoneCommand.MESSAGE_ALREADY_DONE, () -> firstCommand.execute(model, commandHistory));
    }
}
