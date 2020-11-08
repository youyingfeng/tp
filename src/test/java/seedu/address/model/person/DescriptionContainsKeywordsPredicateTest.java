package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;



public class DescriptionContainsKeywordsPredicateTest {

    private Order firstTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "apple and banana",
        new Address("Test Address 2"),
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);
    private Order secondTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "chocolate banana",
        new Address("Test Address 2"),
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate =
            new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate =
            new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
            new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
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

        List<String> firstPredicateKeywordList = Arrays.asList("apple");
        List<String> secondPredicateKeywordList = Arrays.asList("apple", "banana");
        List<String> thirdPredicateKeywordList = Arrays.asList("aPplE", "banAna");


        // One keyword
        DescriptionContainsKeywordsPredicate predicate =
            new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(predicate.test(firstTestOrder));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);
        assertTrue(predicate.test(firstTestOrder));

        // Only one matching keyword
        assertTrue(predicate.test(secondTestOrder));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(thirdPredicateKeywordList);
        assertTrue(predicate.test(firstTestOrder));
        assertTrue(predicate.test(secondTestOrder));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        List<String> firstPredicateKeywordList = Arrays.asList("orange");

        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate =
            new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(firstTestOrder));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertFalse(predicate.test(firstTestOrder));

        // Keywords address, but does not match name
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("test", "address", "2"));
        assertFalse(predicate.test(firstTestOrder));
    }
}
