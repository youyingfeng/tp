package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;

public class OrderClientIdPredicate implements Predicate<Order> {

    private Index clientId;

    public OrderClientIdPredicate(Index id) {
        this.clientId = id;
    }

    @Override
    public boolean test(Order order) {
        return clientId.getOneBased() == order.getClientId().getZeroBased();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof OrderClientIdPredicate // instanceof handles nulls
                           && clientId.equals(((OrderClientIdPredicate) other).clientId));
    }
}
