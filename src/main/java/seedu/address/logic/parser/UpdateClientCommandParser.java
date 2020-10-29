package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_PHONE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OrderCommand;
import seedu.address.logic.commands.UpdateClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class UpdateClientCommandParser implements Parser<UpdateClientCommand> {
    public static final String COMMAND_WORD = "update client";
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateClientCommand
     * and returns an UpdateClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateClientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        UPDATE_CLIENT_PREFIX_CLIENTID,
                        UPDATE_CLIENT_PREFIX_NAME,
                        UPDATE_CLIENT_PREFIX_ADDRESS,
                        UPDATE_CLIENT_PREFIX_EMAIL,
                        UPDATE_CLIENT_PREFIX_PHONE);
        if (argMultimap.getValue(UPDATE_CLIENT_PREFIX_CLIENTID).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderCommand.MESSAGE_USAGE));
        } else if (argMultimap.getValue(UPDATE_CLIENT_PREFIX_NAME).isEmpty()
                && argMultimap.getValue(UPDATE_CLIENT_PREFIX_ADDRESS).isEmpty()
                && argMultimap.getValue(UPDATE_CLIENT_PREFIX_EMAIL).isEmpty()
                && argMultimap.getValue(UPDATE_CLIENT_PREFIX_PHONE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderCommand.MESSAGE_USAGE));
        }
        Index clientId = ParserUtil.parseOrderIndex(argMultimap.getValue(UPDATE_CLIENT_PREFIX_CLIENTID).orElse(null));
        Name name = ParserUtil.parseName(argMultimap.getValue(UPDATE_CLIENT_PREFIX_NAME).orElse(null));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(UPDATE_CLIENT_PREFIX_ADDRESS).orElse(null));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(UPDATE_CLIENT_PREFIX_EMAIL).orElse(null));
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(UPDATE_CLIENT_PREFIX_PHONE).orElse(null));
        return new UpdateClientCommand(clientId, name, address, email, phone);
    }
}
