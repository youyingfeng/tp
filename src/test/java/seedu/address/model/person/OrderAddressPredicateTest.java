package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderAddressPredicateTest {
    private Order firstTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "apple and banana",
        new Address("Jurong West"),
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);
    private Order secondTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "chocolate banana",
        new Address("Clementi West"),
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);

    @Test
    public void equals() {

        OrderAddressPredicate firstPredicate = new OrderAddressPredicate(Arrays.asList("west"));
        OrderAddressPredicate secondPredicate = new OrderAddressPredicate(Arrays.asList("Jurong", "West"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderAddressPredicate firstPredicateCopy = new OrderAddressPredicate(Arrays.asList("west"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different list of keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {

        OrderAddressPredicate firstPredicate = new OrderAddressPredicate(Arrays.asList("west"));
        OrderAddressPredicate secondPredicate = new OrderAddressPredicate(Arrays.asList("Jurong", "West"));

        // One keyword
        assertTrue(firstPredicate.test(firstTestOrder));
        assertTrue(firstPredicate.test(secondTestOrder));

        // Multiple keywords
        assertTrue(secondPredicate.test(firstTestOrder));

        // Only one matching keyword
        assertTrue(firstPredicate.test(secondTestOrder));

        // Mixed-case keywords
        OrderAddressPredicate thirdPredicate = new OrderAddressPredicate(Arrays.asList("wEst", "jUrOnG"));
        assertTrue(thirdPredicate.test(firstTestOrder));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        List<String> firstPredicateKeywordList = Arrays.asList("changi");

        // Zero keywords
        OrderAddressPredicate predicate = new OrderAddressPredicate(Collections.emptyList());
        assertFalse(predicate.test(firstTestOrder));

        // Non-matching keyword
        predicate = new OrderAddressPredicate(firstPredicateKeywordList);
        assertFalse(predicate.test(firstTestOrder));
    }
}
