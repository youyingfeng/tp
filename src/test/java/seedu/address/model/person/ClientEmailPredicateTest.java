package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ClientEmailPredicateTest {

    private Client firstTestClient = new PersonBuilder().withEmail("test1@test.com").build();
    private Client secondTestClient = new PersonBuilder().withEmail("test2@test.com").build();

    @Test
    public void equals() {

        ClientEmailPredicate firstPredicate = new ClientEmailPredicate(new Email("test1@test.com"));
        ClientEmailPredicate secondPredicate = new ClientEmailPredicate(new Email("test2@test.com"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClientEmailPredicate firstPredicateCopy = new ClientEmailPredicate(new Email("test1@test.com"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different list of keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailMatches_returnsTrue() {

        ClientEmailPredicate firstPredicate = new ClientEmailPredicate(new Email("test1@test.com"));
        ClientEmailPredicate secondPredicate = new ClientEmailPredicate(new Email("test2@test.com"));

        assertTrue(firstPredicate.test(firstTestClient));
        assertTrue(secondPredicate.test(secondTestClient));

    }

    @Test
    public void test_emailDoesNotMatch_returnsFalse() {

        ClientEmailPredicate firstPredicate = new ClientEmailPredicate(new Email("groble@test.com"));
        ClientEmailPredicate secondPredicate = new ClientEmailPredicate(new Email("droble@test.com"));

        assertFalse(firstPredicate.test(firstTestClient));
        assertFalse(secondPredicate.test(secondTestClient));
    }
}
