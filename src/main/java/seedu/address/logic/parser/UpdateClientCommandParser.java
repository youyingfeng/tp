package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_PHONE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateClientCommand;
import seedu.address.logic.commands.UpdatedClientFields;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new UpdateClientCommand object
 */
public class UpdateClientCommandParser implements Parser<UpdateClientCommand> {
    public static final String COMMAND_WORD = "update-client";

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateClientCommand
     * and returns an UpdateClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateClientCommand parse(String args) throws ParseException {

        assert args.contains(UPDATE_CLIENT_PREFIX_CLIENTID.toString());

        requireNonNull(args);

        Index clientId;
        Name name;
        Address address;
        Email email;
        Phone phone;

        UpdatedClientFields fieldsToUpdate = new UpdatedClientFields();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        UPDATE_CLIENT_PREFIX_CLIENTID,
                        UPDATE_CLIENT_PREFIX_NAME,
                        UPDATE_CLIENT_PREFIX_ADDRESS,
                        UPDATE_CLIENT_PREFIX_EMAIL,
                        UPDATE_CLIENT_PREFIX_PHONE);

        if (args.contains(UPDATE_CLIENT_PREFIX_CLIENTID.toString())) {
            clientId = ParserUtil.parseClientIndex(argMultimap.getValue(UPDATE_CLIENT_PREFIX_CLIENTID).get());
            fieldsToUpdate.setClientId(clientId);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateClientCommand.MESSAGE_USAGE));
        }

        if (args.contains(UPDATE_CLIENT_PREFIX_ADDRESS.toString())) {
            address = ParserUtil.parseAddress(argMultimap.getValue(UPDATE_CLIENT_PREFIX_ADDRESS).get());
            fieldsToUpdate.setAddress(address);
        }

        if (args.contains(UPDATE_CLIENT_PREFIX_EMAIL.toString())) {
            email = ParserUtil.parseEmail(argMultimap.getValue(UPDATE_CLIENT_PREFIX_EMAIL).get());
            fieldsToUpdate.setEmail(email);
        }

        if (args.contains(UPDATE_CLIENT_PREFIX_NAME.toString())) {
            name = ParserUtil.parseName(argMultimap.getValue(UPDATE_CLIENT_PREFIX_NAME).get());
            fieldsToUpdate.setName(name);
        }

        if (args.contains(UPDATE_CLIENT_PREFIX_PHONE.toString())) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(UPDATE_CLIENT_PREFIX_PHONE).get());
            fieldsToUpdate.setPhone(phone);
        }

        return new UpdateClientCommand(fieldsToUpdate);
    }
}
