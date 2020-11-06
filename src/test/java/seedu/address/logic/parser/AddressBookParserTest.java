package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
//import seedu.address.logic.commands.ClientCommand;
//import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
//import seedu.address.logic.commands.UpdateClientCommand;
//import seedu.address.logic.commands.UpdateClientCommand.UpdateClientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Client;
import seedu.address.model.person.ClientMultiPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.testutil.PersonUtil;
//import seedu.address.testutil.UpdateClientDescriptorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    //    @Test
    //    public void parseCommand_add() throws Exception {
    //        Client client = new PersonBuilder().build();
    //        ClientCommand command = (ClientCommand) parser.parseCommand(PersonUtil.getClientCommand(client));
    //        assertEquals(new ClientCommand(client), command);
    //    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    //      @Test
    //      public void parseCommand_delete() throws Exception {
    //          DeleteClientCommand command = (DeleteClientCommand) parser.parseCommand(
    //                  DeleteClientCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
    //          assertEquals(new DeleteClientCommand(INDEX_FIRST_CLIENT), command);
    //      }

    //    @Test
    //    public void parseCommand_edit() throws Exception {
    //        Client client = new PersonBuilder().build();
    //        UpdateClientDescriptor descriptor = new UpdateClientDescriptorBuilder(client).build();
    //        UpdateClientCommand command = (UpdateClientCommand) parser.parseCommand(UpdateClientCommand.COMMAND_WORD
    //                + " " + INDEX_FIRST_CLIENT.getOneBased() + " "
    //                + PersonUtil.getUpdateClientDescriptorDetails(descriptor));
    //        assertEquals(new UpdateClientCommand(INDEX_FIRST_CLIENT, descriptor.getName().get(),
    //                descriptor.getAddress().get(), descriptor.getEmail().get(), descriptor.getPhone().get()),
    //                command);
    //    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        ArrayList<Predicate<Client>> list = new ArrayList<>();
        list.add(new NameContainsKeywordsPredicate(keywords));
        ClientMultiPredicate predicates = new ClientMultiPredicate(list);
        assertEquals(new FindCommand(predicates), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
