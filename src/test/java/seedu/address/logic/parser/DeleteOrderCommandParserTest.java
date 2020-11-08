package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER_ZEROBASED;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteOrderCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteOrderCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteOrderCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteOrderCommandParserTest {
    private DeleteOrderCommandParser parser = new DeleteOrderCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteOrderCommand() {
        assertParseSuccess(parser, " --order 1", new DeleteOrderCommand(INDEX_FIRST_ORDER_ZEROBASED));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNumArg_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
              DeleteOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_outOfRangeArg_throwsParseException() {
        assertParseFailure(parser, " --order 9999999", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_INVALID_INDEX));
    }
}
