package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
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
}
