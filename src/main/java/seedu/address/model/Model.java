package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;

/**
 * The API of the Model component.
 */
public abstract class Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    public abstract void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    public abstract ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    public abstract GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    public abstract void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    public abstract Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    public abstract void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    public abstract void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    public abstract ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public abstract boolean hasPerson(Client client);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    public abstract void deletePerson(Client target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    public abstract void addPerson(Client client);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public abstract void setPerson(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered person list */
    public abstract ObservableList<Client> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    public abstract void updateFilteredPersonList(Predicate<Client> predicate);

    /**
     * Returns true if a order with the same identity as {@code order} exists in the order book.
     */
    public abstract boolean hasOrder(Order client);

    /**
     * Deletes the given order.
     * The order must exist in the order book
     */
    public abstract void deleteOrder(Order target);

    /**
     * Adds the given order.
     * {@code order} must not already exist in the order book.
     */
    public abstract void addOrder(Order order);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the order book.
     * The person identity of {@code editedOrder} must not be the same as another existing order in the order book.
     */
    public abstract void setOrder(Order target, Order editedOrder);

    /** Returns an unmodifiable view of the filtered order list */
    public abstract ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    public abstract void updateFilteredOrderList(Predicate<Order> predicate);
}
