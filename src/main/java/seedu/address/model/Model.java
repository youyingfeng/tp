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
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Client> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Client client);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Client target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Client client);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Client> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Client> predicate);

    /** Returns an unmodifiable view of the unfiltered person list */
    ObservableList<Client> getUnfilteredPersonList();

    /**
     * Returns true if a order with the same identity as {@code order} exists in the order book.
     */
    boolean hasOrder(Order client);

    /**
     * Deletes the given order.
     * The order must exist in the order book
     */
    void deleteOrder(Order target);

    /**
     * Adds the given order.
     * {@code order} must not already exist in the order book.
     */
    void addOrder(Order order);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the order book.
     * The person identity of {@code editedOrder} must not be the same as another existing order in the order book.
     */
    void setOrder(Order target, Order editedOrder);

    /** Returns an unmodifiable view of the filtered order list */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /** Returns an unmodifiable view of the unfiltered order list */
    ObservableList<Order> getUnfilteredOrderList();

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();
}
