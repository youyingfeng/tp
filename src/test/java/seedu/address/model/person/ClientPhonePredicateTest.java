package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;


public class ClientPhonePredicateTest {

    private Client firstTestClient = new PersonBuilder().withPhone("123456").build();
    private Client secondTestClient = new PersonBuilder().withPhone("654321").build();
    @Test
    public void equals() {

        ClientPhonePredicate firstPredicate = new ClientPhonePredicate(new Phone("1111"));
        ClientPhonePredicate secondPredicate = new ClientPhonePredicate(new Phone("2222"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClientPhonePredicate firstPredicateCopy = new ClientPhonePredicate(new Phone("1111"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different list of keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneMatches_returnsTrue() {

        ClientPhonePredicate firstPredicate = new ClientPhonePredicate(new Phone("123456"));
        ClientPhonePredicate secondPredicate = new ClientPhonePredicate(new Phone("654321"));

        assertTrue(firstPredicate.test(firstTestClient));
        assertTrue(secondPredicate.test(secondTestClient));

    }

    @Test
    public void test_emailDoesNotMatch_returnsFalse() {

        ClientPhonePredicate firstPredicate = new ClientPhonePredicate(new Phone("321321"));
        ClientPhonePredicate secondPredicate = new ClientPhonePredicate(new Phone("123123"));

        assertFalse(firstPredicate.test(firstTestClient));
        assertFalse(secondPredicate.test(secondTestClient));
    }
}
