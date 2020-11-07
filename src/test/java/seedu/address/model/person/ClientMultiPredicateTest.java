package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientMultiPredicateTest {

    Client firstTestClient = new PersonBuilder().withName("Alice")
                                 .withAddress("Jurong Point")
                                 .withPhone("1234")
                                 .withEmail("test1@test.com").build();
    Client secondTestClient = new PersonBuilder().withName("Bob")
                                  .withAddress("North Point")
                                  .withPhone("2345")
                                  .withEmail("test2@test.com").build();

    ClientEmailPredicate firstPredicate = new ClientEmailPredicate(new Email("test1@test.com"));
    ClientPhonePredicate secondPredicate = new ClientPhonePredicate(new Phone("1234"));
    ClientAddressPredicate thirdPredicate = new ClientAddressPredicate(Arrays.asList("point"));
    NameContainsKeywordsPredicate fourthPredicate = new NameContainsKeywordsPredicate(Arrays.asList("alice", "bob"));
    NameContainsKeywordsPredicate fifthPredicate = new NameContainsKeywordsPredicate(Arrays.asList("alice", "charles"));

    @Test
    public void equals() {

        ArrayList<Predicate<Client>> firstPredicateList = new ArrayList<>();
        ArrayList<Predicate<Client>> secondPredicateList = new ArrayList<>();

        firstPredicateList.add(firstPredicate);
        firstPredicateList.add(secondPredicate);
        firstPredicateList.add(thirdPredicate);
        firstPredicateList.add(fourthPredicate);

        secondPredicateList.add(firstPredicate);
        secondPredicateList.add(secondPredicate);
        secondPredicateList.add(thirdPredicate);
        secondPredicateList.add(fifthPredicate);

        ClientMultiPredicate firstMultiPredicate = new ClientMultiPredicate(firstPredicateList);
        ClientMultiPredicate secondMultiPredicate = new ClientMultiPredicate(secondPredicateList);
        ClientMultiPredicate thirdMultiPredicate = new ClientMultiPredicate(firstPredicateList);


        // same object -> returns true
        assertTrue(firstMultiPredicate.equals(firstMultiPredicate));

        // same values -> returns true
        assertTrue(firstMultiPredicate.equals(thirdMultiPredicate));

        // different types -> returns false
        assertFalse(firstMultiPredicate.equals(1));

        // null -> returns false
        assertFalse(firstMultiPredicate.equals(null));

        // different list of keywords -> returns false
        assertFalse(firstMultiPredicate.equals(secondMultiPredicate));
    }

    @Test
    public void test_allPredicatesMatch_returnsTrue() {

        ArrayList<Predicate<Client>> firstPredicateList = new ArrayList<>();
        ArrayList<Predicate<Client>> secondPredicateList = new ArrayList<>();

        firstPredicateList.add(firstPredicate);
        firstPredicateList.add(secondPredicate);
        firstPredicateList.add(thirdPredicate);
        firstPredicateList.add(fourthPredicate);

        secondPredicateList.add(fourthPredicate);
        secondPredicateList.add(thirdPredicate);

        ClientMultiPredicate firstMultiPredicate = new ClientMultiPredicate(firstPredicateList);
        ClientMultiPredicate secondMultiPredicate = new ClientMultiPredicate(secondPredicateList);
        ClientMultiPredicate thirdMultiPredicate = new ClientMultiPredicate(new ArrayList<>());

        // 4 predicates in 1 multi predicate
        assertTrue(firstMultiPredicate.test(firstTestClient));

        // 2 predicates in 1 multi predicate
        assertTrue(secondMultiPredicate.test(secondTestClient));

        // no predicates
        assertTrue(thirdMultiPredicate.test(firstTestClient));
        assertTrue(thirdMultiPredicate.test(secondTestClient));
    }

    @Test
    public void test_oneOrMorePredicatesFail_returnsFalse() {

        ArrayList<Predicate<Client>> firstPredicateList = new ArrayList<>();

        firstPredicateList.add(secondPredicate);
        firstPredicateList.add(thirdPredicate);
        firstPredicateList.add(fourthPredicate);

        // fails phone predicate
        assertFalse(firstPredicate.test(secondTestClient));
    }

}
