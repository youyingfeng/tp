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
}
