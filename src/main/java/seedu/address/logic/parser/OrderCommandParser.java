package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DESCRIPTION;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Order;

/**
 * Parses input arguments and creates a new OrderCommand object
 */
public class OrderCommandParser {
    /**
     *
     * @param args full user input string
     * @return an OrderCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public OrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                                           ORDER_PREFIX_DESCRIPTION,
                                           ORDER_PREFIX_CLIENT,
                                           ORDER_PREFIX_ADDRESS,
                                           ORDER_PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap,
                                ORDER_PREFIX_DESCRIPTION,
                                ORDER_PREFIX_CLIENT,
                                ORDER_PREFIX_ADDRESS,
                                ORDER_PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderCommand.MESSAGE_USAGE));
        }

        Index orderId = Index.fromZeroBased(0);
        Index clientId = ParserUtil.parseClientIndex(argMultimap.getValue(ORDER_PREFIX_CLIENT).get());
        String description = argMultimap.getValue(ORDER_PREFIX_DESCRIPTION).orElse("");
        Address address = ParserUtil.parseAddress(argMultimap.getValue(ORDER_PREFIX_ADDRESS).get());
        System.out.println(argMultimap.getValue(ORDER_PREFIX_DATE).get());
        LocalDateTime deliveryDateTime = ParserUtil.parseDate(argMultimap.getValue(ORDER_PREFIX_DATE).get());
        LocalDateTime creationDateTime = LocalDateTime.now();
        boolean isDone = false;

        Order order = new Order(orderId, clientId, description, address, deliveryDateTime, creationDateTime,
                creationDateTime, false);

        return new OrderCommand(order);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
