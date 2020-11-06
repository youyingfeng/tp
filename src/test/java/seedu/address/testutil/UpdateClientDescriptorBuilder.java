package seedu.address.testutil;

import seedu.address.logic.commands.UpdateClientCommand.UpdateClientDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class UpdateClientDescriptorBuilder {

    private UpdateClientDescriptor descriptor;

    public UpdateClientDescriptorBuilder() {
        descriptor = new UpdateClientDescriptor();
    }

    public UpdateClientDescriptorBuilder(UpdateClientDescriptor descriptor) {
        this.descriptor = new UpdateClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public UpdateClientDescriptorBuilder(Client client) {
        descriptor = new UpdateClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public UpdateClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public UpdateClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public UpdateClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public UpdateClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    // public EditPersonDescriptorBuilder withTags(String... tags) {
    //     Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
    //     descriptor.setTags(tagSet);
    //     return this;
    // }

    public UpdateClientDescriptor build() {
        return descriptor;
    }
}
