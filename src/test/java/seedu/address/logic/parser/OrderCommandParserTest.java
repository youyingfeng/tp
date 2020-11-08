package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.OrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Order;

public class OrderCommandParserTest {
    private static final String EMPTY_STRING = "";
    private static final String WHITESPACE_STRING = "";
    private static final String VALID_CLIENT_SUBSTRING = " " + ORDER_PREFIX_CLIENT + " 00001";
    private static final String VALID_DESC_SUBSTRING = " " + ORDER_PREFIX_DESCRIPTION + " Kappa";
    private static final String VALID_ADDRESS_SUBSTRING = " " + ORDER_PREFIX_ADDRESS + " Somewhere";
    private static final String VALID_DATETIME_SUBSTRING = " " + ORDER_PREFIX_DATE + " 2020-02-20 1521";

    private static final String VALID_ORDER_PARSER_INPUT =
            VALID_DESC_SUBSTRING + VALID_CLIENT_SUBSTRING + VALID_ADDRESS_SUBSTRING + VALID_DATETIME_SUBSTRING;
    private static final String INVALID_INPUT_NO_ARGUMENTS = "Lorem Ipsum Dolor Sit Amet";
    private static final String INVALID_INPUT_MISSING_ARGUMENTS = VALID_CLIENT_SUBSTRING
            + VALID_ADDRESS_SUBSTRING + VALID_DESC_SUBSTRING;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();
    private OrderCommandParser parser = new OrderCommandParser();

    @Test
    public void parse_validInput_success() {
        Order expectedOrder;
        try {
            expectedOrder = new Order(Index.fromZeroBased(1), "Kappa", new Address("Somewhere"),
                    ParserUtil.parseDate("2020-02-20 1521"), ParserUtil.parseDate("2020-02-20 1521"),
                    ParserUtil.parseDate("2020-02-20 1521"), false);
            assertParseSuccess(parser, VALID_ORDER_PARSER_INPUT, new OrderCommand(expectedOrder));
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parse_invalidInput_failure() {
        assertThrows(ParseException.class, () -> parser.parse(EMPTY_STRING));
        assertThrows(ParseException.class, () -> parser.parse(WHITESPACE_STRING));
        assertThrows(ParseException.class, () -> parser.parse(INVALID_INPUT_NO_ARGUMENTS));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        assertThrows(ParseException.class, () ->
                parser.parse(VALID_ADDRESS_SUBSTRING + VALID_DESC_SUBSTRING + VALID_DATETIME_SUBSTRING));
        assertThrows(ParseException.class, () ->
                parser.parse(VALID_CLIENT_SUBSTRING + VALID_DESC_SUBSTRING + VALID_DATETIME_SUBSTRING));
        assertThrows(ParseException.class, () ->
                parser.parse(VALID_ADDRESS_SUBSTRING + VALID_CLIENT_SUBSTRING + VALID_DATETIME_SUBSTRING));
        assertThrows(ParseException.class, () ->
                parser.parse(VALID_ADDRESS_SUBSTRING + VALID_DESC_SUBSTRING + VALID_CLIENT_SUBSTRING));
    }
}
