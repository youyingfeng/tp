package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


public class OrderAddressPredicate implements Predicate<Order> {

    private final List<String> keywords;

    public OrderAddressPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                       .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getAddress().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof OrderAddressPredicate // instanceof handles nulls
                           && keywords.equals(((OrderAddressPredicate) other).keywords));
    }
}
