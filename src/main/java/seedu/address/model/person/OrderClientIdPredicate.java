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
        int searchId = clientId.getOneBased();
        int orderId = order.getClientId().getZeroBased();
        return clientId.getOneBased() == order.getClientId().getZeroBased();
    }
}
