package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DescriptionContainsKeywordsPredicate;
import seedu.address.model.person.Order;
import seedu.address.model.person.OrderMultiPredicate;

public class FindOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
            new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        DescriptionContainsKeywordsPredicate secondPredicate =
            new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        ArrayList<Predicate<Order>> firstList = new ArrayList<>();
        ArrayList<Predicate<Order>> secondList = new ArrayList<>();

        firstList.add(firstPredicate);
        secondList.add((secondPredicate));

        OrderMultiPredicate firstMulti = new OrderMultiPredicate(firstList);
        OrderMultiPredicate secondMulti = new OrderMultiPredicate(secondList);

        FindOrderCommand findFirstCommand = new FindOrderCommand(firstMulti);
        FindOrderCommand findSecondCommand = new FindOrderCommand(secondMulti);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same predicate -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstMulti);
        // assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different command with different value -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}
