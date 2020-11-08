package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.UPDATE_ORDER_PREFIX_ORDERID;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateOrderCommand;
import seedu.address.logic.commands.UpdatedOrderFields;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;

/**
 * Parses input arguments and creates a new UpdateOrderCommand object
 */
public class UpdateOrderCommandParser implements Parser<UpdateOrderCommand> {
    public static final String COMMAND_WORD = "update-order";
    /**
     * @param args full user input string
     * @return an UpdateOrderCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateOrderCommand parse(String args) throws ParseException {

        assert args.contains(UPDATE_ORDER_PREFIX_ORDERID.toString());

        requireNonNull(args);

        Index orderId;
        Index clientId;
        String description;
        Address address;
        LocalDateTime dateTime;

        UpdatedOrderFields fieldsToUpdate = new UpdatedOrderFields();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        UPDATE_ORDER_PREFIX_ORDERID,
                        UPDATE_ORDER_PREFIX_CLIENTID,
                        UPDATE_ORDER_PREFIX_DESCRIPTION,
                        UPDATE_ORDER_PREFIX_ADDRESS,
                        UPDATE_ORDER_PREFIX_DATE);

        // check for the tokens present, and add them to UpdatedOrderFields
        if (args.contains(UPDATE_ORDER_PREFIX_ORDERID.toString())) {
            orderId = ParserUtil.parseOrderIndex(argMultimap.getValue(UPDATE_ORDER_PREFIX_ORDERID).get());
            fieldsToUpdate.setOrderId(orderId);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateOrderCommand.MESSAGE_USAGE));
        }


        if (args.contains(UPDATE_ORDER_PREFIX_CLIENTID.toString())) {
            clientId = ParserUtil.parseClientIndex(argMultimap.getValue(UPDATE_ORDER_PREFIX_CLIENTID).get());
            fieldsToUpdate.setClientId(clientId);
        }

        if (args.contains(UPDATE_ORDER_PREFIX_DESCRIPTION.toString())) {
            description = argMultimap.getValue(UPDATE_ORDER_PREFIX_DESCRIPTION).get();
            fieldsToUpdate.setDescription(description);
        }

        if (args.contains(UPDATE_ORDER_PREFIX_ADDRESS.toString())) {
            address = ParserUtil.parseAddress(argMultimap.getValue(UPDATE_ORDER_PREFIX_ADDRESS).get());
            fieldsToUpdate.setAddress(address);
        }

        if (args.contains(UPDATE_ORDER_PREFIX_DATE.toString())) {
            dateTime = ParserUtil.parseDate(argMultimap.getValue(UPDATE_ORDER_PREFIX_DATE).get());
            fieldsToUpdate.setDateTime(dateTime);
        }

        return new UpdateOrderCommand(fieldsToUpdate);
    }
}
