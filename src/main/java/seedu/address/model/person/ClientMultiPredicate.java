package seedu.address.model.person;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ClientMultiPredicate implements Predicate<Client> {

    private ArrayList<Predicate<Client>> toBeTested;

    public ClientMultiPredicate(ArrayList<Predicate<Client>> predicates) {
        toBeTested = predicates;
    }

    @Override
    public boolean test(Client client) {
        for (Predicate<Client> predicate : toBeTested) {
            if (predicate.test(client) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof ClientMultiPredicate // instanceof handles nulls
                           && toBeTested.equals(((ClientMultiPredicate) other).toBeTested));
    }
}
