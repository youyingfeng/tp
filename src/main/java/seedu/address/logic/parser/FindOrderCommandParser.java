package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_ADDRESS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DescriptionContainsKeywordsPredicate;
import seedu.address.model.person.Order;
import seedu.address.model.person.OrderAddressPredicate;
import seedu.address.model.person.OrderClientIdPredicate;
import seedu.address.model.person.OrderDatePredicate;
import seedu.address.model.person.OrderMultiPredicate;


public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    private static final Logger findOrderCommandParserLogger = Logger.getLogger("focp");

    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, ORDER_PREFIX_ADDRESS, ORDER_PREFIX_CLIENT, ORDER_PREFIX_DATE);

        ArrayList<Predicate<Order>> predicates = new ArrayList<>();

        if (args.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argMultimap.getPreamble();

        if (!trimmedArgs.isEmpty()) {
            String[] descKeywords = trimmedArgs.split("\\s+");
            predicates.add(new DescriptionContainsKeywordsPredicate(Arrays.asList(descKeywords)));
        }
        if (argMultimap.getValue(ORDER_PREFIX_CLIENT).isPresent()) {
            predicates.add(
                new OrderClientIdPredicate(ParserUtil.parseClientIndex(argMultimap.getValue(ORDER_PREFIX_CLIENT).get()))
            );
        }
        if (argMultimap.getValue(ORDER_PREFIX_DATE).isPresent()) {
            try {
                predicates.add(new OrderDatePredicate(LocalDate.parse(argMultimap.getValue(ORDER_PREFIX_DATE).get())));
            } catch (DateTimeParseException e) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
            }
        }
        if (argMultimap.getValue(ORDER_PREFIX_ADDRESS).isPresent()) {
            String address = argMultimap.getValue(ORDER_PREFIX_ADDRESS).get();
            if (address.isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_SEARCH_ADDRESS);
            } else {
                String[] addressKeywords = address.split("\\s+");
                predicates.add(new OrderAddressPredicate(Arrays.asList(addressKeywords)));
            }
        }
        return new FindOrderCommand(new OrderMultiPredicate(predicates));
    }

}
