package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_ADDRESS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.model.person.DescriptionContainsKeywordsPredicate;
import seedu.address.model.person.Order;
import seedu.address.model.person.OrderAddressPredicate;
import seedu.address.model.person.OrderClientIdPredicate;
import seedu.address.model.person.OrderDatePredicate;
import seedu.address.model.person.OrderMultiPredicate;

public class FindOrderCommandParserTest {
    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        ArrayList<Predicate<Order>> list = new ArrayList<>();
        list.add(new DescriptionContainsKeywordsPredicate(Arrays.asList("apple", "banana")));
        OrderMultiPredicate predicates = new OrderMultiPredicate(list);
        String userInput = "apple banana";

        // no leading and trailing whitespaces
        FindOrderCommand expectedFindCommand = new FindOrderCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n apple \n \t banana  \t", expectedFindCommand);

        // with client ID predicate
        list.add(new OrderClientIdPredicate(Index.fromZeroBased(1)));
        userInput += " --client 1";
        predicates = new OrderMultiPredicate(list);
        expectedFindCommand = new FindOrderCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with date predicate
        list.add(new OrderDatePredicate(LocalDate.parse("2020-12-31")));
        userInput += " --date 2020-12-31";
        predicates = new OrderMultiPredicate(list);
        expectedFindCommand = new FindOrderCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with address predicate
        list.add(new OrderAddressPredicate(Arrays.asList("Jurong")));
        userInput += " --address Jurong";
        predicates = new OrderMultiPredicate(list);
        expectedFindCommand = new FindOrderCommand(predicates);
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_invalidNumArg_throwsParseException() {

        assertParseFailure(parser, " --client 0", String.format(ParserUtil.MESSAGE_INVALID_INDEX));
        assertParseFailure(parser, " --client 100000", String.format(ParserUtil.MESSAGE_INVALID_INDEX));

    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        assertParseFailure(parser, " --date abcd", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyAddressArg_throwsParseException() {
        assertParseFailure(parser, " --address", String.format(MESSAGE_EMPTY_SEARCH_ADDRESS));
    }
}
