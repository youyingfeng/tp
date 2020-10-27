package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ORDERID;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OrderCommand;
import seedu.address.logic.commands.UpdateOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;

public class UpdateOrderCommandParser implements Parser<UpdateOrderCommand> {
    public static final String COMMAND_WORD = "update order";
    /**
     * @param args full user input string
     * @return an UpdateOrderCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        UPDATE_ORDER_PREFIX_ORDERID,
                        UPDATE_ORDER_PREFIX_CLIENTID,
                        UPDATE_ORDER_PREFIX_DESCRIPTION,
                        UPDATE_ORDER_PREFIX_ADDRESS,
                        UPDATE_ORDER_PREFIX_DATE);
        if (argMultimap.getValue(UPDATE_ORDER_PREFIX_ORDERID).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderCommand.MESSAGE_USAGE));
        } else if (argMultimap.getValue(UPDATE_ORDER_PREFIX_CLIENTID).isEmpty()
                && argMultimap.getValue(UPDATE_ORDER_PREFIX_DESCRIPTION).isEmpty()
                && argMultimap.getValue(UPDATE_ORDER_PREFIX_ADDRESS).isEmpty()
                && argMultimap.getValue(UPDATE_ORDER_PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderCommand.MESSAGE_USAGE));
        }
        Index orderId = ParserUtil.parseOrderIndex(argMultimap.getValue(UPDATE_ORDER_PREFIX_ORDERID).orElse(null));
        Index clientId = ParserUtil.parseOrderIndex(argMultimap.getValue(UPDATE_CLIENT_PREFIX_CLIENTID).orElse(null));
        String description = argMultimap.getValue(UPDATE_ORDER_PREFIX_DESCRIPTION).orElse(null);
        Address address = ParserUtil.parseAddress(argMultimap.getValue(UPDATE_ORDER_PREFIX_ADDRESS).orElse(null));
        LocalDateTime dateTime = ParserUtil.parseDate(argMultimap.getValue(UPDATE_ORDER_PREFIX_DATE).orElse(null));
        return new UpdateOrderCommand(orderId, clientId, description, address, dateTime);
    }
}
