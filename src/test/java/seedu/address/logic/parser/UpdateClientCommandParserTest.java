package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_CLIENTID;
import static seedu.address.logic.parser.UpdateClientCommandParser.COMMAND_WORD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UpdateClientCommandParserTest {
    private final UpdateClientCommandParser parser = new UpdateClientCommandParser();
    private final String commandKeyword = COMMAND_WORD + " ";
    private final String validClientId = UPDATE_CLIENT_PREFIX_CLIENTID + " 1 ";
    private final String validName = UPDATE_CLIENT_PREFIX_NAME + " valid name ";
    private final String validAddress = UPDATE_CLIENT_PREFIX_ADDRESS + " valid address ";
    private final String validEmail = UPDATE_CLIENT_PREFIX_EMAIL + " validEmail@example.com ";
    private final String validPhone = UPDATE_CLIENT_PREFIX_PHONE + " 87654321 ";
    @Test
    public void validParserArgsAll() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validClientId + validAddress + validEmail + validName
                    + validPhone);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
            assertEquals(result.getFieldsToUpdate().getEmail().get().toString(), "validEmail@example.com");
            assertEquals(result.getFieldsToUpdate().getName().get().toString(), "valid name");
            assertEquals(result.getFieldsToUpdate().getPhone().get().toString(), "87654321");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsAllReordered() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validEmail + validPhone + validAddress + validName
                    + validClientId);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
            assertEquals(result.getFieldsToUpdate().getEmail().get().toString(), "validEmail@example.com");
            assertEquals(result.getFieldsToUpdate().getName().get().toString(), "valid name");
            assertEquals(result.getFieldsToUpdate().getPhone().get().toString(), "87654321");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsName() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validClientId + validName);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getName().get().toString(), "valid name");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsNameReversed() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validName + validClientId);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getName().get().toString(), "valid name");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsAddress() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validClientId + validAddress);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsAddressReversed() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validAddress + validClientId);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getAddress().get().toString(), "valid address");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsEmail() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validClientId + validEmail);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getEmail().get().toString(), "validEmail@example.com");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsEmailReversed() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validEmail + validClientId);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getEmail().get().toString(), "validEmail@example.com");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsPhone() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validClientId + validPhone);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getPhone().get().toString(), "87654321");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void validParserArgsPhoneReversed() {
        UpdateClientCommand result;
        try {
            result = parser.parse(commandKeyword + validPhone + validClientId);
            assertEquals(result.getFieldsToUpdate().getClientId().get().getZeroBased(), 1);
            assertEquals(result.getFieldsToUpdate().getPhone().get().toString(), "87654321");
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void invalidParserArgs() {
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + UPDATE_ORDER_PREFIX_CLIENTID + "0" + validName));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + UPDATE_ORDER_PREFIX_CLIENTID + "a" + validName));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + validClientId + UPDATE_CLIENT_PREFIX_EMAIL + " invalid email "));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + validClientId + UPDATE_CLIENT_PREFIX_PHONE + " invalid phone "));
        assertThrows(ParseException.class, () -> parser.parse(commandKeyword
                + validClientId + UPDATE_CLIENT_PREFIX_PHONE + " 1 "));
    }
}
