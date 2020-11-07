package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderClientIdPredicateTest {
    private Order firstTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "apple and banana",
        new Address("Test Address 2"),
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);
    private Order secondTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(2),
        "chocolate banana",
        new Address("Test Address 2"),
        LocalDateTime.now(),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);

    @Test
    public void equals() {

        OrderClientIdPredicate firstPredicate = new OrderClientIdPredicate(Index.fromZeroBased(1));
        OrderClientIdPredicate secondPredicate = new OrderClientIdPredicate(Index.fromZeroBased(2));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderClientIdPredicate firstPredicateCopy = new OrderClientIdPredicate(Index.fromZeroBased(1));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Client ID -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_orderContainsID_returnsTrue() {

        OrderClientIdPredicate firstPredicate = new OrderClientIdPredicate(Index.fromZeroBased(1));
        OrderClientIdPredicate secondPredicate = new OrderClientIdPredicate(Index.fromZeroBased(2));
        assertTrue(firstPredicate.test(firstTestOrder));
        assertTrue(secondPredicate.test(secondTestOrder));
    }

    @Test
    public void test_orderDifferentID_returnsFalse() {
        OrderClientIdPredicate firstPredicate = new OrderClientIdPredicate(Index.fromZeroBased(60));

        assertFalse(firstPredicate.test(secondTestOrder));
    }
}
