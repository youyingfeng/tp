package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.UPDATE_CLIENT_PREFIX_PHONE;

import java.util.List;
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

    private final Index clientId;
    private final UpdatedClientFields fieldsToUpdate;

    /**
     * @param fieldsToUpdate contains all the fields to update for the respective Client object.
     */
    public UpdateClientCommand(UpdatedClientFields fieldsToUpdate) {
        this.fieldsToUpdate = fieldsToUpdate;
        this.clientId = fieldsToUpdate.getClientId().get();
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getUnfilteredPersonList();

        if (clientId.getZeroBased() > lastShownList.get(lastShownList.size() - 1).getClientId()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToUpdate = null;

        for (Client client : lastShownList) {
            if (client.getClientId() == clientId.getZeroBased()) {
                clientToUpdate = client;
                break;
            }
        }

        if (clientToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Name name;
        if (fieldsToUpdate.getName().isEmpty()) {
            name = clientToUpdate.getName();
        } else {
            name = fieldsToUpdate.getName().get();
        }

        Address address;
        if (fieldsToUpdate.getAddress().isEmpty()) {
            address = clientToUpdate.getAddress();
        } else {
            address = fieldsToUpdate.getAddress().get();
        }

        Email email;
        if (fieldsToUpdate.getEmail().isEmpty()) {
            email = clientToUpdate.getEmail();
        } else {
            email = fieldsToUpdate.getEmail().get();
        }

        Phone phone;
        if (fieldsToUpdate.getPhone().isEmpty()) {
            phone = clientToUpdate.getPhone();
        } else {
            phone = fieldsToUpdate.getPhone().get();
        }

        List<Index> orderList = clientToUpdate.getOrders();
        Client newClient = new Client(name, phone, email, address, clientId, orderList);
        model.setPerson(clientToUpdate, newClient);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_UPDATE_CLIENT_SUCCESS, clientToUpdate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateClientCommand // instanceof handles nulls
                && clientId.equals(((UpdateClientCommand) other).clientId)); // state check
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
