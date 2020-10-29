package seedu.address.model.person;

import java.util.function.Predicate;

public class ClientEmailPredicate implements Predicate<Client> {

    private final Email email;

    public ClientEmailPredicate(Email email) {
        this.email = email;
    }

    @Override
    public boolean test(Client client) {
        return email.equals(client.getEmail());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof ClientEmailPredicate // instanceof handles nulls
                           && email.equals(((ClientEmailPredicate) other).email));
    }
}
