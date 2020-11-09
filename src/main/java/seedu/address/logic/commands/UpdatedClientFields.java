package seedu.address.logic.commands;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class UpdatedClientFields {
    private Index clientId;
    private Name name;
    private Address address;
    private Email email;
    private Phone phone;

    /**
     * Constructor for UpdatedClientFields object. This object is used to contain the fields (if any) to be passed on
     * to the UpdateClientCommand object.
     */
    public UpdatedClientFields() { }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setClientId(Index clientId) {
        this.clientId = clientId;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Index> getClientId() {
        return Optional.ofNullable(clientId);
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }
}
