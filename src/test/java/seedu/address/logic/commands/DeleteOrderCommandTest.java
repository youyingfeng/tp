package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER_ZEROBASED;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER_ZEROBASED;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Order;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteOrderCommand}.
 */
public class DeleteOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private CommandHistory commandHistory = new CommandHistory();


    @Test
    public void execute_validIndexUnfilteredList_success() {
        ObservableList<Order> tempOrderList = model.getFilteredOrderList();
        Order orderToDelete = null;

        Index targetId = INDEX_FIRST_ORDER_ZEROBASED;

        for (Order order : tempOrderList) {
            if (order.getOrderId().equals(targetId)) {
                orderToDelete = order;
                break;
            }
        }

        if (orderToDelete != null) {
            DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER_ZEROBASED);

            String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            expectedModel.deleteOrder(orderToDelete);
            expectedModel.commitAddressBook();

            assertCommandSuccess(deleteOrderCommand, model, commandHistory, expectedMessage, expectedModel);
        } else {
            assert false;
        }

    }


    @Test
    public void execute_lowerBoundInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromZeroBased(0);
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model,
                commandHistory, Messages.MESSAGE_INVALID_ORDER_INDEX);
    }


    @Test
    public void execute_upperBoundInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = null;
        int largestOrderId = 0;

        // find largest order id
        ObservableList<Order> tempOrderList = model.getFilteredOrderList();
        for (Order order : tempOrderList) {
            int orderId = order.getOrderId().getZeroBased();

            if (orderId > largestOrderId) {
                largestOrderId = orderId;
            }
        }

        if (largestOrderId > 0) {
            outOfBoundIndex = Index.fromZeroBased(largestOrderId + 1);
        }

        if (outOfBoundIndex != null) {
            DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

            assertCommandFailure(deleteOrderCommand, model,
                    commandHistory, Messages.MESSAGE_INVALID_ORDER_INDEX);
        } else {
            // no client in list
            assert false;
        }
    }


    @Test
    public void execute_validIndexFilteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER_ZEROBASED);

        Order orderToDelete = null;
        ObservableList<Order> tempOrderList = model.getFilteredOrderList();
        Index targetId = INDEX_FIRST_ORDER_ZEROBASED;
        for (Order order : tempOrderList) {
            if (order.getOrderId().equals(targetId)) {
                orderToDelete = order;
                break;
            }
        }

        if (orderToDelete != null) {
            DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER_ZEROBASED);

            String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

            Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            expectedModel.deleteOrder(orderToDelete);
            expectedModel.commitAddressBook();
            showNoOrder(expectedModel);

            assertCommandSuccess(deleteOrderCommand, model, commandHistory, expectedMessage, expectedModel);
        } else {
            assert false;
        }
    }


    @Test
    public void execute_lowerBoundInvalidIndexFilteredList_throwsCommandException() {
        // filter list to include only the order with unique order id 1
        showOrderAtIndex(model, INDEX_FIRST_ORDER_ZEROBASED);

        // outOfBoundIndex should be an invalid index regardless of filtered or non-filtered list
        Index outOfBoundIndex = Index.fromZeroBased(0);

        boolean isInAddressBook = false;
        ObservableList<Order> tempOrderList = model.getAddressBook().getOrderList();
        for (Order order : tempOrderList) {
            if (order.getOrderId().equals(outOfBoundIndex)) {
                isInAddressBook = true;
                break;
            }
        }

        // ensures that outOfBoundIndex should not exist in the addressbook list
        assertFalse(isInAddressBook);

        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model,
                commandHistory, Messages.MESSAGE_INVALID_ORDER_INDEX);
    }

    @Test
    public void execute_upperBoundInvalidIndexFilteredList_throwsCommandException() {
        // filter list to include only the client with unique client id 1
        showOrderAtIndex(model, INDEX_FIRST_ORDER_ZEROBASED);

        Index outOfBoundIndex = INDEX_SECOND_ORDER_ZEROBASED;

        boolean isInAddressBook = false;
        ObservableList<Order> tempOrderList = model.getAddressBook().getOrderList();
        for (Order order : tempOrderList) {
            if (order.getOrderId().equals(outOfBoundIndex)) {
                isInAddressBook = true;
                break;
            }
        }

        // ensures that outOfBoundIndex still exists address book list
        assertTrue(isInAddressBook);

        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model,
                commandHistory, Messages.MESSAGE_INVALID_ORDER_INDEX);
    }

    @Test
    public void equals() {
        DeleteOrderCommand deleteFirstCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER);
        DeleteOrderCommand deleteSecondCommand = new DeleteOrderCommand(INDEX_SECOND_ORDER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteOrderCommand deleteFirstCommandCopy = new DeleteOrderCommand(INDEX_FIRST_ORDER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no order.
     */
    private void showNoOrder(Model model) {
        model.updateFilteredOrderList(p -> false);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
}
