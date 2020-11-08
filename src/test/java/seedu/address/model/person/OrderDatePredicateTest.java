package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class OrderDatePredicateTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private Order firstTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "apple and banana",
        new Address("Test Address 2"),
        LocalDateTime.parse("2020-10-10 1200", formatter),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);
    private Order secondTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "chocolate banana",
        new Address("Test Address 2"),
        LocalDateTime.parse("2019-12-10 1200", formatter),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);

    @Test
    public void equals() {

        OrderDatePredicate firstPredicate = new OrderDatePredicate(LocalDate.parse("2012-12-12"));
        OrderDatePredicate secondPredicate = new OrderDatePredicate(LocalDate.parse("2015-12-10"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderDatePredicate firstPredicateCopy = new OrderDatePredicate(LocalDate.parse("2012-12-12"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different date -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_orderContainsDate_returnsTrue() {

        OrderDatePredicate firstPredicate = new OrderDatePredicate(LocalDate.parse("2020-10-10"));

        assertTrue(firstPredicate.test(firstTestOrder));
    }

    @Test
    public void test_orderDifferentDate_returnsFalse() {
        OrderDatePredicate firstPredicate = new OrderDatePredicate(LocalDate.parse("2020-10-10"));

        assertFalse(firstPredicate.test(secondTestOrder));
    }
}
