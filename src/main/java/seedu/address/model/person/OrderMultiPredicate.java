package seedu.address.model.person;

import java.util.ArrayList;
import java.util.function.Predicate;

public class OrderMultiPredicate implements Predicate<Order> {

    private ArrayList<Predicate<Order>> toBeTested;

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
}
