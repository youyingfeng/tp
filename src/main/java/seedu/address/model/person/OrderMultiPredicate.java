package seedu.address.model.person;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Wrapper predicate that tests {@code Order} and only returns true if all predicates in the
 * list of predicates return true when testing the {@code Order}
 */
public class OrderMultiPredicate implements Predicate<Order> {

    private final ArrayList<Predicate<Order>> toBeTested;

    public OrderMultiPredicate(ArrayList<Predicate<Order>> predicates) {
        toBeTested = predicates;
    }

    @Override
    public boolean test(Order order) {
        for (Predicate<Order> predicate : toBeTested) {
            if (predicate.test(order) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof OrderMultiPredicate // instanceof handles nulls
                           && toBeTested.equals(((OrderMultiPredicate) other).toBeTested));
    }
}
