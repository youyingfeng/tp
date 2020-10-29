package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;

/**
 * Tests that an {@code Order}'s {@code orderId} matches any of the keywords given.
 */
public class OrderIdContainsKeywordsPredicate implements Predicate<Order> {
    private final Index keyword;

    public OrderIdContainsKeywordsPredicate(Index keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Order order) {
        Index id = order.getOrderId();
        return keyword.equals(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderIdContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((OrderIdContainsKeywordsPredicate) other).keyword)); // state check
    }
}
