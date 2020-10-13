package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.ClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class ClientCommandParser implements Parser<ClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                                           CLIENT_PREFIX_NAME,
                                           CLIENT_PREFIX_ADDRESS,
                                           CLIENT_PREFIX_EMAIL,
                                           CLIENT_PREFIX_PHONE);

        if (!arePrefixesPresent(argMultimap,
                                CLIENT_PREFIX_NAME,
                                CLIENT_PREFIX_ADDRESS,
                                CLIENT_PREFIX_EMAIL,
                                CLIENT_PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(CLIENT_PREFIX_NAME).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(CLIENT_PREFIX_ADDRESS).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(CLIENT_PREFIX_EMAIL).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CLIENT_PREFIX_PHONE).get());

        Client client = new Client(name, phone, email, address);

        return new ClientCommand(client);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
