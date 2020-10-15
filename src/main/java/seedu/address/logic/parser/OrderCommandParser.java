package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.ORDER_PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.OrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class OrderCommandParser {


    /**
     *
     * @param args full user input string
     * @return an OrderCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public OrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                ORDER_PREFIX_DESCRIPTION, ORDER_PREFIX_CLIENT);

        Index clientId;
        try {
            clientId = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    OrderCommand.MESSAGE_USAGE), ive);
        }

        String description = argMultimap.getValue(ORDER_PREFIX_DESCRIPTION).orElse("");

        return new OrderCommand(description, clientId);
    }

}
