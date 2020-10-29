package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_ADDRESS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Client;
import seedu.address.model.person.ClientAddressPredicate;
import seedu.address.model.person.ClientEmailPredicate;
import seedu.address.model.person.ClientMultiPredicate;
import seedu.address.model.person.ClientPhonePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CLIENT_PREFIX_PHONE, CLIENT_PREFIX_EMAIL, CLIENT_PREFIX_ADDRESS);

        ArrayList<Predicate<Client>> predicates = new ArrayList<>();


        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argMultimap.getPreamble();

        if (!trimmedArgs.isEmpty()) {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(CLIENT_PREFIX_PHONE).isPresent()) {
            predicates.add(
                    new ClientPhonePredicate(ParserUtil.parsePhone(argMultimap.getValue(CLIENT_PREFIX_PHONE).get()))
            );
        }
        if (argMultimap.getValue(CLIENT_PREFIX_EMAIL).isPresent()) {
            predicates.add(
                new ClientEmailPredicate(ParserUtil.parseEmail(argMultimap.getValue(CLIENT_PREFIX_EMAIL).get()))
            );
        }
        if (argMultimap.getValue(CLIENT_PREFIX_ADDRESS).isPresent()) {
            String address = argMultimap.getValue(CLIENT_PREFIX_ADDRESS).get();
            if (address.isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_SEARCH_ADDRESS);
            } else {
                String[] addressKeywords = address.split("\\s+");
                predicates.add(new ClientAddressPredicate(Arrays.asList(addressKeywords)));
            }
        }

        return new FindCommand(new ClientMultiPredicate(predicates));
    }

}
