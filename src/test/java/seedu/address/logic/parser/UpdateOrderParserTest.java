//package seedu.address.logic.parser;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.*;
//import static seedu.address.testutil.TypicalOrders.SHOES;
//import static seedu.address.testutil.TypicalOrders.SKIRT;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.CommandHistory;
//import seedu.address.logic.commands.OrderCommand;
//import seedu.address.logic.commands.UpdateOrderCommand;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//
//import java.security.KeyStore;
//
//public class UpdateOrderParserTest {
//    private static final String EMPTY_STRING = "";
//    private static final String WHITESPACE_STRING = "";
//    private static final String VALID_ORDERID_SUBSTRING = " " + UPDATE_ORDER_PREFIX_ORDERID + "00123";
//    private static final String VALID_CLIENT_SUBSTRING = " " + UPDATE_ORDER_PREFIX_CLIENTID + " 00123";
//    private static final String VALID_DESCRIPTION_SUBSTRING = " " + UPDATE_ORDER_PREFIX_DESCRIPTION + " Items";
//    private static final String VALID_ADDRESS_SUBSTRING = " " + UPDATE_ORDER_PREFIX_ADDRESS +
//    " Somewhere over the Rainbow";
//    private static final String VALID_DATETIME_SUBSTRING = " " + UPDATE_ORDER_PREFIX_DATE + " 2020-11-12 1234";
//
//    private static final String VALID_ORDER_PARSER_INPUT =
//            VALID_DESCRIPTION_SUBSTRING + VALID_CLIENT_SUBSTRING + VALID_ADDRESS_SUBSTRING + VALID_DATETIME_SUBSTRING;
//    private static final String VALID_REORDERED_ORDER_PARSER_INPUT =
//            VALID_DATETIME_SUBSTRING + VALID_DESCRIPTION_SUBSTRING + VALID_CLIENT_SUBSTRING + VALID_ADDRESS_SUBSTRING;
//    private static final String INVALID_INPUT_NO_ARGUMENTS = "Im Ralph";
//    private static final String INVALID_INPUT_MISSING_CLIENT =
//            VALID_DESCRIPTION_SUBSTRING + VALID_ADDRESS_SUBSTRING + VALID_DATETIME_SUBSTRING;
//    private static final String INVALID_INPUT_MISSING_DESCRIPTION =
//            VALID_CLIENT_SUBSTRING + VALID_ADDRESS_SUBSTRING + VALID_DATETIME_SUBSTRING;
//    private static final String INVALID_INPUT_MISSING_ADDRESS =
//            VALID_DESCRIPTION_SUBSTRING + VALID_CLIENT_SUBSTRING + VALID_DATETIME_SUBSTRING;
//    private static final String INVALID_INPUT_MISSING_DATETIME =
//            VALID_DESCRIPTION_SUBSTRING + VALID_CLIENT_SUBSTRING + VALID_ADDRESS_SUBSTRING;
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    private CommandHistory commandHistory = new CommandHistory();
//    private UpdateOrderCommandParser parser = new UpdateOrderCommandParser();
//
//    @Test
//    public void parse_validDescription_success() {
//
//        try {
//
//            parser.parse(VALID_ORDERID_SUBSTRING + VALID_DESCRIPTION_SUBSTRING);
//            assert true;
//
//        } catch (ParseException e) {
//            assert false;
//        }
//
//        //assertParseSuccess(parser, VALID_ORDERID_SUBSTRING);
//
//
//
//    }
//
//    @Test
//    public void parse_invalidDate_failure() {
//
//         invalid month in date token
//        assertThrows(ParseException.class, () -> parser.parse(ORDER_PREFIX_DATE + " 2020-00-12 1234"));
//        assertThrows(ParseException.class, () -> parser.parse(ORDER_PREFIX_DATE + " 2020-13-12 1234"));
//        assertThrows(ParseException.class, () -> parser.parse(ORDER_PREFIX_DATE + " 2020-06-00 1234"));
//        assertThrows(ParseException.class, () -> parser.parse(ORDER_PREFIX_DATE + " 2020-06-32 1234"));
//        assertThrows(ParseException.class, () -> parser.parse(ORDER_PREFIX_DATE + " 2020-12-12 2400"));
//
//        assertParseSuccess(parser, UPDATE_ORDER_PREFIX_ORDERID + "123" + VALID_DATETIME_SUBSTRING,
//                new UpdateOrderCommand(123, null, null, null,
//                        ParserUtil.parseDate("2020-11-12 1234")));
//
//        String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateOrderCommand.MESSAGE_USAGE)
//
//        try {
//            parser.parse(VALID_DATETIME_SUBSTRING);
//            assert false;
//        } catch (ParseException e) {
//            assert true;
//        }
//
//
//    }
//
//    @Test
//    public void parse_invalidClientid_failure() {
//
//    }
//}
