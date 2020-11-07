package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientAddressPredicateTest {

    Client firstTestClient = new PersonBuilder().withAddress("Jurong West Central 3").build();
    Client secondTestClient = new PersonBuilder().withAddress("Bukit Batok West Avenue 6").build();
    @Test
    public void equals() {

        ClientAddressPredicate firstPredicate = new ClientAddressPredicate(Arrays.asList("west"));
        ClientAddressPredicate secondPredicate = new ClientAddressPredicate(Arrays.asList("Jurong", "West"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClientAddressPredicate firstPredicateCopy = new ClientAddressPredicate(Arrays.asList("west"));
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

        ClientAddressPredicate firstPredicate = new ClientAddressPredicate(Arrays.asList("west"));
        ClientAddressPredicate secondPredicate = new ClientAddressPredicate(Arrays.asList("Bukit", "Batok", "West"));

        // One keyword
        assertTrue(firstPredicate.test(firstTestClient));
        assertTrue(firstPredicate.test(secondTestClient));

        // Multiple keywords
        assertTrue(secondPredicate.test(firstTestClient));

        // Only one matching keyword
        assertTrue(firstPredicate.test(secondTestClient));

        // Mixed-case keywords
        ClientAddressPredicate thirdPredicate = new ClientAddressPredicate(Arrays.asList("wEst", "jUrOnG", "aVenUe"));
        assertTrue(thirdPredicate.test(firstTestClient));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        List<String> firstPredicateKeywordList = Arrays.asList("changi");

        // Zero keywords
        ClientAddressPredicate predicate = new ClientAddressPredicate(Collections.emptyList());
        assertFalse(predicate.test(firstTestClient));

        // Non-matching keyword
        predicate = new ClientAddressPredicate(firstPredicateKeywordList);
        assertFalse(predicate.test(firstTestClient));
    }
}
