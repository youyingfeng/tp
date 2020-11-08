package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_PHONE;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Updates the fields of an existing client in client list of the address book.
 */
public class UpdateClientCommand extends Command {

    public static final String COMMAND_WORD = "update-client";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the client identified "
            + "by the index number provided.\n"
            + "Parameters: INDEX (must be a positive integer representing a client in LogOnce; must not be empty) "
            + UPDATE_CLIENT_PREFIX_NAME + "NAME "
            + UPDATE_CLIENT_PREFIX_ADDRESS + "ADDRESS "
            + UPDATE_CLIENT_PREFIX_EMAIL + "EMAIL "
            + UPDATE_CLIENT_PREFIX_PHONE + "PHONE"
            + "Example: " + COMMAND_WORD + " 1 " + CLIENT_PREFIX_NAME + "Peter";

    public static final String MESSAGE_UPDATE_CLIENT_SUCCESS = "Updated Client: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to edit must be provided.";

    private final Index targetIndex;
    private Name name;
    private Address address;
    private Email email;
    private Phone phone;
    private final UpdateClientDescriptor updateClientDescriptor;

    /**
     * @param targetIndex index of the client the user would like to modify
     * @param name (if applicable) modified name of the client
     * @param address (if applicable) modified address of the client
     * @param email (if applicable) modified email of the client
     * @param phone (if applicable) modified phone of the client
     */
    public UpdateClientCommand(Index targetIndex, Name name, Address address, Email email, Phone phone) {
        this.targetIndex = targetIndex;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;

        UpdateClientDescriptor updateClientDescriptor = new UpdateClientDescriptor();
        updateClientDescriptor.setName(name);
        updateClientDescriptor.setAddress(address);
        updateClientDescriptor.setEmail(email);
        updateClientDescriptor.setPhone(phone);
        this.updateClientDescriptor = updateClientDescriptor;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getUnfilteredPersonList();

        if (targetIndex.getZeroBased() > lastShownList.get(lastShownList.size() - 1).getClientId()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToUpdate = null;

        for (Client client : lastShownList) {
            if (client.getClientId() == targetIndex.getZeroBased()) {
                clientToUpdate = client;
                break;
            }
        }

        if (clientToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        if (Objects.isNull(name)) {
            name = clientToUpdate.getName();
        }
        if (Objects.isNull(address)) {
            address = clientToUpdate.getAddress();
        }
        if (Objects.isNull(email)) {
            email = clientToUpdate.getEmail();
        }
        if (Objects.isNull(phone)) {
            phone = clientToUpdate.getPhone();
        }

        List<Index> orderList = clientToUpdate.getOrders();
        Client newClient = new Client(name, phone, email, address, targetIndex, orderList);
        model.setPerson(clientToUpdate, newClient);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_UPDATE_CLIENT_SUCCESS, clientToUpdate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateClientCommand // instanceof handles nulls
                && targetIndex.equals(((UpdateClientCommand) other).targetIndex)); // state check
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class UpdateClientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;

        public UpdateClientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdateClientDescriptor(UpdateClientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateClientDescriptor)) {
                return false;
            }

            // state check
            UpdateClientDescriptor e = (UpdateClientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress());
        }
    }
}
