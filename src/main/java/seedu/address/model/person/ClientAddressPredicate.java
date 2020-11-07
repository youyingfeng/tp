package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Client}'s {@code Address} matches any of the keywords given.
 */
public class ClientAddressPredicate implements Predicate<Client> {

    private final List<String> keywords;

    public ClientAddressPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                       .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getAddress().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof ClientAddressPredicate // instanceof handles nulls
                           && keywords.equals(((ClientAddressPredicate) other).keywords));
    }
}
