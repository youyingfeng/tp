package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.*;
import seedu.address.ui.MainWindow;

import java.nio.file.Path;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListOrderCommandTest {

    @TempDir
    public Path testFolder;

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListOrderCommand(), model, commandHistory, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        assertCommandSuccess(new ListOrderCommand(), model, commandHistory, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
