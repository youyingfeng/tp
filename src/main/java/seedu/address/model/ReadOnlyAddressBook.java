package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook extends Observable {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getPersonList();

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();
}
