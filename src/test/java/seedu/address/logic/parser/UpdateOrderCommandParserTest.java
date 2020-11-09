package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ORDERID;
import static seedu.address.logic.parser.UpdateOrderCommandParser.COMMAND_WORD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UpdateOrderCommandParserTest {
    private final UpdateOrderCommandParser parser = new UpdateOrderCommandParser();
    private final String commandKeyword = COMMAND_WORD + " ";
    private final String validOrderId = UPDATE_ORDER_PREFIX_ORDERID.toString() + " 1 ";
    private final String validClientId = UPDATE_ORDER_PREFIX_CLIENTID.toString() + " 1 ";
    private final String validOrderDescription = UPDATE_ORDER_PREFIX_DESCRIPTION.toString() + " valid order ";
    private final String validOrderAddress = UPDATE_ORDER_PREFIX_ADDRESS.toString() + " valid address ";
    private final String validOrderDateTime = UPDATE_ORDER_PREFIX_DATE.toString() + " 2020-11-11 1111 ";

    @Test
    public void validParserArgsAll() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderId + validOrderDescription + validClientId
            + validOrderDateTime + validOrderAddress);
            assertEquals(result.getFieldsToUpdate().getDescription().get(), "valid order");
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
            assertEquals(result.getFieldsToUpdate().getDateTime().get().toString(), "2020-11-11T11:11");
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsAllReordered() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderId + validOrderDateTime + validClientId
                    + validOrderDescription + validOrderAddress);
            assertEquals(result.getFieldsToUpdate().getDescription().get(), "valid order");
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
            assertEquals(result.getFieldsToUpdate().getDateTime().get().toString(), "2020-11-11T11:11");
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsClientId() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderId + validClientId);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsClientIdReversed() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validClientId + validOrderId);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserDescription() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderId + validOrderDescription);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getDescription().get(), "valid order");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserDescriptionReversed() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderDescription + validOrderId);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getDescription().get(), "valid order");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserAddress() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderId + validOrderAddress);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserAddressReversed() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderAddress + validOrderId);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserDateTime() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderId + validOrderDateTime);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getDateTime().get().toString(), "2020-11-11T11:11");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserDateTimeReversed() {
        UpdateOrderCommand result;
        try {
            result = parser.parse(commandKeyword + validOrderDateTime + validOrderId);
            assertEquals(result.getFieldsToUpdate().getOrderId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getDateTime().get().toString(), "2020-11-11T11:11");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void invalidArguments() {
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword + "--orderid abc"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword + "--orderid 0"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword + "--orderid 1 --clientid abc"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword + "--orderid 1 --clientid 0"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 02020-11-11 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 0000-11-11 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-13-11 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-00-11 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-1-11 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-011-11 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-11-1 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-11-00 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-11-32 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-11-2 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-11-011 1111"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-11-11 2360"));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + "--orderid 1 --date 2020-11-11 2511"));
    }
}
