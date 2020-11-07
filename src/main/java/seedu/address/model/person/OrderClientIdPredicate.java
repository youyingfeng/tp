package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;

/**
 * Tests that an {@code Order}'s {@code clientId} matches any of the keywords given.
 */
public class OrderClientIdPredicate implements Predicate<Order> {

    private Index clientId;

    public OrderClientIdPredicate(Index id) {
        this.clientId = id;
    }

    @Override
    public boolean test(Order order) {
        return clientId.getZeroBased() == order.getClientId().getZeroBased();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof OrderClientIdPredicate // instanceof handles nulls
                           && clientId.equals(((OrderClientIdPredicate) other).clientId));
    }
}
