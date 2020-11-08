package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class OrderMultiPredicateTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private Order firstTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(1),
        "apple and banana",
        new Address("Jurong West"),
        LocalDateTime.parse("2012-12-12 1200", formatter),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);
    private Order secondTestOrder = new Order(Index.fromZeroBased(0),
        Index.fromZeroBased(2),
        "chocolate banana",
        new Address("Clementi West"),
        LocalDateTime.parse("2020-10-10 1200", formatter),
        LocalDateTime.now(),
        LocalDateTime.now(),
        false);

    private DescriptionContainsKeywordsPredicate firstPredicate =
        new DescriptionContainsKeywordsPredicate(Arrays.asList("banana"));
    private OrderClientIdPredicate secondPredicate = new OrderClientIdPredicate(Index.fromZeroBased(1));
    private OrderAddressPredicate thirdPredicate = new OrderAddressPredicate(Arrays.asList("West"));
    private OrderDatePredicate fourthPredicate = new OrderDatePredicate(LocalDate.parse("2012-12-12"));

    @Test
    public void equals() {

        ArrayList<Predicate<Order>> firstPredicateList = new ArrayList<>();
        ArrayList<Predicate<Order>> secondPredicateList = new ArrayList<>();

        firstPredicateList.add(firstPredicate);
        firstPredicateList.add(secondPredicate);
        firstPredicateList.add(thirdPredicate);
        firstPredicateList.add(fourthPredicate);

        secondPredicateList.add(firstPredicate);
        secondPredicateList.add(secondPredicate);
        secondPredicateList.add(thirdPredicate);

        OrderMultiPredicate firstMultiPredicate = new OrderMultiPredicate(firstPredicateList);
        OrderMultiPredicate secondMultiPredicate = new OrderMultiPredicate(secondPredicateList);
        OrderMultiPredicate thirdMultiPredicate = new OrderMultiPredicate(firstPredicateList);


        // same object -> returns true
        assertTrue(firstMultiPredicate.equals(firstMultiPredicate));

        // same values -> returns true
        assertTrue(firstMultiPredicate.equals(thirdMultiPredicate));

        // different types -> returns false
        assertFalse(firstMultiPredicate.equals(1));

        // null -> returns false
        assertFalse(firstMultiPredicate.equals(null));

        // different predicates -> returns false
        assertFalse(firstMultiPredicate.equals(secondMultiPredicate));
    }

    @Test
    public void test_allPredicatesMatch_returnsTrue() {

        ArrayList<Predicate<Order>> firstPredicateList = new ArrayList<>();
        ArrayList<Predicate<Order>> secondPredicateList = new ArrayList<>();

        firstPredicateList.add(firstPredicate);
        firstPredicateList.add(secondPredicate);
        firstPredicateList.add(thirdPredicate);
        firstPredicateList.add(fourthPredicate);

        secondPredicateList.add(firstPredicate);
        secondPredicateList.add(thirdPredicate);

        OrderMultiPredicate firstMultiPredicate = new OrderMultiPredicate(firstPredicateList);
        OrderMultiPredicate secondMultiPredicate = new OrderMultiPredicate(secondPredicateList);
        OrderMultiPredicate thirdMultiPredicate = new OrderMultiPredicate(new ArrayList<>());

        // 4 predicates in 1 multi predicate
        assertTrue(firstMultiPredicate.test(firstTestOrder));

        // 2 predicates in 1 multi predicate
        assertTrue(secondMultiPredicate.test(secondTestOrder));

        // no predicates
        assertTrue(thirdMultiPredicate.test(firstTestOrder));
        assertTrue(thirdMultiPredicate.test(secondTestOrder));
    }

    @Test
    public void test_oneOrMorePredicatesFail_returnsFalse() {

        ArrayList<Predicate<Order>> firstPredicateList = new ArrayList<>();

        firstPredicateList.add(thirdPredicate);
        firstPredicateList.add(fourthPredicate);

        OrderMultiPredicate multiPredicate = new OrderMultiPredicate(firstPredicateList);

        // fails date predicate but passes address -> returns false
        assertFalse(multiPredicate.test(secondTestOrder));
    }
}
