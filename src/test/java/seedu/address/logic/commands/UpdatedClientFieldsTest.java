package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class UpdatedClientFieldsTest {
    private final UpdatedClientFields fields = new UpdatedClientFields();
    private final Address validAddress = new Address("123, Jurong West Ave 6, #08-111");
    private Index validClientId;
    private Email validEmail;
    private Phone validPhone;
    private Name validName;

    {
        try {
            validEmail = ParserUtil.parseEmail("validEmail@example.com");
            validPhone = ParserUtil.parsePhone("87654321");
            validClientId = ParserUtil.parseClientIndex("1");
            validName = ParserUtil.parseName("valid name");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validGetAndSetAddress() {
        fields.setAddress(validAddress);
        Address obtainedAddress = fields.getAddress().get();
        assertEquals(validAddress, obtainedAddress);
    }

    @Test
    public void validGetAndSetClientId() {
        fields.setClientId(validClientId);
        Index obtainedClientId = fields.getClientId().get();
        assertEquals(validClientId, obtainedClientId);
    }

    @Test
    public void validGetAndSetEmail() {
        fields.setEmail(validEmail);
        Email obtainedEmail = fields.getEmail().get();
        assertEquals(validEmail, obtainedEmail);
    }

    @Test
    public void validGetAndSetPhone() {
        fields.setPhone(validPhone);
        Phone obtainedPhone = fields.getPhone().get();
        assertEquals(obtainedPhone, validPhone);
    }

    @Test
    public void validGetAndSetName() {
        fields.setName(validName);
        Name obtainedName = fields.getName().get();
        assertEquals(validName, obtainedName);
    }
}
