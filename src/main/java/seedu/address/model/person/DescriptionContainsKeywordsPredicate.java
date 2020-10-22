package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                       .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getDescription(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                                   && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords));
    }
}
