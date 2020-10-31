package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;
import seedu.address.model.person.UniqueOrderList;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson and .isSameOrder comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueOrderList orders;

    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        orders = new UniqueOrderList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Client> clients) {
        this.persons.setPersons(clients);
    }

    /**
     * Replaces the contents of the order list with {@code orders}.
     * {@code orders} must not contain duplicate orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setOrders(newData.getOrderList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return persons.contains(client);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addClient(Client p) {
        if (p.getClientId() == 0) {
            ObservableList<Client> tempClientList = this.getPersonList();
            if (tempClientList.size() == 0) {
                p.setClientId(1);
            } else {
                int newIndex = tempClientList.get(tempClientList.size() - 1).getClientId() + 1;
                p.setClientId(newIndex);
            }
        }

        persons.add(p);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        persons.setPerson(target, editedClient);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeClient(Client key) {
        int clientId = key.getClientId();
        assert clientId > 0;

        ObservableList<Order> tempOrderList = this.getOrderList();
        List<Order> ordersToDelete = new ArrayList<>();


        // loop through to find out orders linked to client
        for (int i = 0; i < tempOrderList.size(); i++) {
            Order order = tempOrderList.get(i);
            requireNonNull(order);
            Index clientLinkedId = order.getClientId();

            if (clientLinkedId.getZeroBased() == clientId) {
                ordersToDelete.add(order);
            }
        }

        // remove orders that are linked to client
        for (Order order : ordersToDelete) {
            removeOrder(order);
        }

        persons.remove(key);
        indicateModified();
    }

    // order-level operations
    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addOrder(Order o) {
        requireNonNull(o);
        if (o.getOrderId().getZeroBased() == 0) {
            ObservableList<Order> tempOrderList = this.getOrderList();
            if (tempOrderList.size() == 0) {
                o.setOrderId(1);
            } else {
                int newOrderId = tempOrderList.get(tempOrderList.size() - 1).getOrderId().getZeroBased() + 1;
                o.setOrderId(newOrderId);
            }
        }
        orders.add(o);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(target, editedOrder);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeOrder(Order key) {
        orders.remove(key);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && orders.equals(((AddressBook) other).orders));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
        // todo : implementation for orders hashcode
    }
}
