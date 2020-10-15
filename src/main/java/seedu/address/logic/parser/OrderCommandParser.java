package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DESCRIPTION;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Order;

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
                                           ORDER_PREFIX_CLIENT);

        if (!arePrefixesPresent(argMultimap,
                                ORDER_PREFIX_DESCRIPTION,
                                ORDER_PREFIX_CLIENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(ORDER_PREFIX_DESCRIPTION).orElse("");
        Index clientId = ParserUtil.parseOrderIndex(argMultimap.getValue(ORDER_PREFIX_CLIENT).get());

        Order order = new Order(description, clientId);

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
