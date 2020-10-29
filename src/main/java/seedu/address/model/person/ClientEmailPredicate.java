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
}
