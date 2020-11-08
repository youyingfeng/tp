package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_ADDRESS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Client;
import seedu.address.model.person.ClientAddressPredicate;
import seedu.address.model.person.ClientEmailPredicate;
import seedu.address.model.person.ClientMultiPredicate;
import seedu.address.model.person.ClientPhonePredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        ArrayList<Predicate<Client>> list = new ArrayList<>();
        list.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        ClientMultiPredicate predicates = new ClientMultiPredicate(list);
        String userInput = "Alice Bob";
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);

        // with phone predicate
        list.add(new ClientPhonePredicate(new Phone("1234")));
        userInput += " --phone 1234";
        predicates = new ClientMultiPredicate(list);
        expectedFindCommand = new FindCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with email predicate
        list.add(new ClientEmailPredicate(new Email("test@test.com")));
        userInput += " --email test@test.com";
        predicates = new ClientMultiPredicate(list);
        expectedFindCommand = new FindCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with address predicate
        list.add(new ClientAddressPredicate(Arrays.asList("Jurong", "Clementi")));
        userInput += " --address Jurong Clementi";
        predicates = new ClientMultiPredicate(list);
        expectedFindCommand = new FindCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);

    }
    @Test
    public void parse_invalidEmail_throwsParseException() {

        assertParseFailure(parser, " --email 0", Email.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " --email abc", Email.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_invalidPhone_throwsParseException() {
        assertParseFailure(parser, " --phone abc", String.format(Phone.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, " --phone 12", String.format(Phone.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, " --phone 123abc", String.format(Phone.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_emptyAddressArg_throwsParseException() {
        assertParseFailure(parser, " --address", String.format(MESSAGE_EMPTY_SEARCH_ADDRESS));
    }
}
