package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT_ZEROBASED;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT_ZEROBASED;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Client;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteClientCommand}.
 */
public class DeleteClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ObservableList<Client> tempClientList = model.getFilteredPersonList();
        Client clientToDelete = null;
        int targetId = INDEX_FIRST_CLIENT_ZEROBASED.getZeroBased();

        for (Client client : tempClientList) {
            if (client.getClientId() == targetId) {
                clientToDelete = client;
                break;
            }
        }

        if (clientToDelete != null) {
            DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_FIRST_CLIENT_ZEROBASED);

            String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            expectedModel.deletePerson(clientToDelete);
            expectedModel.commitAddressBook();

            assertCommandSuccess(deleteClientCommand, model, commandHistory, expectedMessage, expectedModel);

        } else {
            assert false;
        }
    }


    @Test
    public void execute_lowerBoundInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromZeroBased(0);
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteClientCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }


    @Test
    public void execute_upperBoundInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = null;
        int largestClientId = 0;

        // find largest client id
        ObservableList<Client> tempClientList = model.getFilteredPersonList();
        for (Client client : tempClientList) {
            int clientId = client.getClientId();
            if (clientId > largestClientId) {
                largestClientId = clientId;
            }
        }

        if (largestClientId > 0) {
            outOfBoundIndex = Index.fromZeroBased(largestClientId + 1);
        }

        if (outOfBoundIndex != null) {
            DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

            assertCommandFailure(deleteClientCommand, model, commandHistory,
                    Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        } else {
            // no client in list
            assert false;
        }
    }


    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT_ZEROBASED);

        Client clientToDelete = null;
        ObservableList<Client> tempClientList = model.getFilteredPersonList();
        int targetId = INDEX_FIRST_CLIENT_ZEROBASED.getZeroBased();
        for (Client client : tempClientList) {
            if (client.getClientId() == targetId) {
                clientToDelete = client;
                break;
            }
        }

        if (clientToDelete != null) {
            DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_FIRST_CLIENT_ZEROBASED);

            String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

            Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            expectedModel.deletePerson(clientToDelete);
            expectedModel.commitAddressBook();
            showNoClient(expectedModel);

            assertCommandSuccess(deleteClientCommand, model, commandHistory, expectedMessage, expectedModel);
        } else {
            assert false;
        }
    }

    @Test
    public void execute_lowerBoundInvalidIndexFilteredList_throwsCommandException() {
        // filter list to include only the client with unique client id 1
        showClientAtIndex(model, INDEX_FIRST_CLIENT_ZEROBASED);

        // outOfBoundIndex should be an invalid index regardless of filtered or non-filtered list
        Index outOfBoundIndex = Index.fromZeroBased(0);
        int outOfBoundInt = outOfBoundIndex.getZeroBased();

        boolean isInAddressBook = false;
        ObservableList<Client> tempClientList = model.getAddressBook().getPersonList();
        for (Client client : tempClientList) {
            if (client.getClientId() == outOfBoundInt) {
                isInAddressBook = true;
                break;
            }
        }

        // ensures that outOfBoundIndex should not exist in the addressbook list
        assertFalse(isInAddressBook);

        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteClientCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_upperBoundInvalidIndexFilteredList_throwsCommandException() {
        // filter list to include only the client with unique client id 1
        showClientAtIndex(model, INDEX_FIRST_CLIENT_ZEROBASED);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT_ZEROBASED;
        int outOfBoundInt = outOfBoundIndex.getZeroBased();

        boolean isInAddressBook = false;
        ObservableList<Client> tempClientList = model.getAddressBook().getPersonList();
        for (Client client : tempClientList) {
            if (client.getClientId() == outOfBoundInt) {
                System.out.println("here");
                isInAddressBook = true;
                break;
            }
        }

        // ensures that outOfBoundIndex still exists address book list
        assertTrue(isInAddressBook);

        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteClientCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteClientCommand deleteFirstCommand = new DeleteClientCommand(INDEX_FIRST_CLIENT);
        DeleteClientCommand deleteSecondCommand = new DeleteClientCommand(INDEX_SECOND_CLIENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteClientCommand deleteFirstCommandCopy = new DeleteClientCommand(INDEX_FIRST_CLIENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no client.
     */
    private void showNoClient(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
