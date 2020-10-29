package seedu.address.model.person;

import java.util.function.Predicate;

public class ClientPhonePredicate implements Predicate<Client> {

    private final Phone phone;

    public ClientPhonePredicate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Client client) {
        return phone.equals(client.getPhone());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof ClientPhonePredicate // instanceof handles nulls
                           && phone.equals(((ClientPhonePredicate) other).phone));
    }
}
