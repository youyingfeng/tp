package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

//import org.junit.jupiter.api.Test;
import seedu.address.logic.CommandHistory;
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.UpdateClientCommand.UpdateClientDescriptor;
//import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.person.Client;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.testutil.UpdateClientDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class UpdateClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    //    @Test
    //    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //        Client editedClient = new PersonBuilder().build();
    //        UpdateClientDescriptor descriptor = new UpdateClientDescriptorBuilder(editedClient).build();
    //        UpdateClientCommand updateClientCommand = new UpdateClientCommand(INDEX_FIRST_CLIENT,
    //        descriptor.getName().get(), descriptor.getAddress().get(), descriptor.getEmail().get(),
    //        descriptor.getPhone().get());
    //
    //        String expectedMessage = String.format(UpdateClientCommand.MESSAGE_UPDATE_CLIENT_SUCCESS, editedClient);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedClient);
    //        expectedModel.commitAddressBook();
    //
    //        assertCommandSuccess(updateClientCommand, model, commandHistory, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_someFieldsSpecifiedUnfilteredList_success() {
    //        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
    //        Client lastClient = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
    //
    //        PersonBuilder personInList = new PersonBuilder(lastClient);
    //        Client editedClient = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
    //                .withTags(VALID_TAG_HUSBAND).build();
    //
    //        UpdateClientDescriptor descriptor = new UpdateClientDescriptorBuilder().withName(VALID_NAME_BOB)
    //                .withPhone(VALID_PHONE_BOB).build();
    //        UpdateClientCommand editCommand = new UpdateClientCommand(indexLastPerson, descriptor);
    //
    //        String expectedMessage = String.format(UpdateClientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedClient);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setPerson(lastClient, editedClient);
    //        expectedModel.commitAddressBook();
    //
    //        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_noFieldSpecifiedUnfilteredList_success() {
    //        UpdateClientCommand editCommand = new UpdateClientCommand(INDEX_FIRST_CLIENT,
    //            new UpdateClientDescriptor());
    //        Client editedClient = model.getFilteredPersonList().get(INDEX_FIRST_CLIENT.getZeroBased());
    //
    //        String expectedMessage = String.format(UpdateClientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedClient);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.commitAddressBook();
    //
    //        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
    //    }
    //
    //        @Test
    //        public void execute_filteredList_success() {
    //            showClientAtIndex(model, INDEX_FIRST_CLIENT);
    //
    //            Client clientInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_CLIENT.getZeroBased());
    //            Client editedClient = new PersonBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
    //            UpdateClientCommand editCommand = new UpdateClientCommand(INDEX_FIRST_CLIENT,
    //                    new UpdateClientDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //            String expectedMessage = String.format(UpdateClientCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedClient);
    //
    //            Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //            expectedModel.setPerson(model.getFilteredPersonList().get(0), editedClient);
    //            expectedModel.commitAddressBook();
    //
    //            assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
    //        }
    //
    //    @Test
    //    public void execute_duplicatePersonUnfilteredList_failure() {
    //        Client firstClient = model.getFilteredPersonList().get(INDEX_FIRST_CLIENT.getZeroBased());
    //        UpdateClientDescriptor descriptor = new UpdateClientDescriptorBuilder(firstClient).build();
    //        UpdateClientCommand editCommand = new UpdateClientCommand(INDEX_SECOND_CLIENT, descriptor);
    //
    //        assertCommandFailure(editCommand, model, commandHistory, UpdateClientCommand.MESSAGE_DUPLICATE_PERSON);
    //    }
    //
    //    @Test
    //    public void execute_duplicatePersonFilteredList_failure() {
    //        showClientAtIndex(model, INDEX_FIRST_CLIENT);
    //
    //        // edit person in filtered list into a duplicate in address book
    //        Client clientInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_CLIENT.getZeroBased());
    //        EditCommand editCommand = new EditCommand(INDEX_FIRST_CLIENT,
    //                new EditPersonDescriptorBuilder(clientInList).build());
    //
    //        assertCommandFailure(editCommand, model, commandHistory, EditCommand.MESSAGE_DUPLICATE_PERSON);
    //    }
    //
    //    @Test
    //    public void execute_invalidPersonIndexUnfilteredList_failure() {
    //        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
    //        UpdateClientDescriptor descriptor = new UpdateClientDescriptorBuilder().withName(VALID_NAME_BOB).build();
    //        UpdateClientCommand editCommand = new UpdateClientCommand(outOfBoundIndex, descriptor);
    //
    //        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    //    }
    //
    //    /**
    //     * Edit filtered list where index is larger than size of filtered list,
    //     * but smaller than size of address book
    //     */
    //    @Test
    //    public void execute_invalidPersonIndexFilteredList_failure() {
    //        showClientAtIndex(model, INDEX_FIRST_CLIENT);
    //        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
    //
    //        UpdateClientCommand editCommand = new UpdateClientCommand(outOfBoundIndex,
    //                new UpdateClientDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    //    }
    //
    //    @Test
    //    public void equals() {
    //        final UpdateClientCommand standardCommand = new UpdateClientCommand(INDEX_FIRST_CLIENT, DESC_AMY);
    //
    //        // same values -> returns true
    //        UpdateClientDescriptor copyDescriptor = new UpdateClientDescriptor(DESC_AMY);
    //        UpdateClientCommand commandWithSameValues = new UpdateClientCommand(INDEX_FIRST_CLIENT, copyDescriptor);
    //        assertTrue(standardCommand.equals(commandWithSameValues));
    //
    //        // same object -> returns true
    //        assertTrue(standardCommand.equals(standardCommand));
    //
    //        // null -> returns false
    //        assertFalse(standardCommand.equals(null));
    //
    //        // different types -> returns false
    //        assertFalse(standardCommand.equals(new ClearCommand()));
    //
    //        // different index -> returns false
    //        assertFalse(standardCommand.equals(new UpdateClientCommand(INDEX_SECOND_CLIENT, DESC_AMY)));
    //
    //        // different descriptor -> returns false
    //        assertFalse(standardCommand.equals(new UpdateClientCommand(INDEX_FIRST_CLIENT, DESC_BOB)));
    //    }
}
