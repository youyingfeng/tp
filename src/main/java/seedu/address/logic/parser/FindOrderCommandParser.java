package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DescriptionContainsKeywordsPredicate;


public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    private static final Logger findOrderCommandParserLogger = Logger.getLogger("focp");

    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            findOrderCommandParserLogger.log(Level.WARNING, "empty search keywords");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        String[] descriptionKeywords = trimmedArgs.split("\\s+");

        return new FindOrderCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(descriptionKeywords)));
    }

}
