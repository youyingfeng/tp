package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;

public class UpdatedOrderFieldsTest {
    private final UpdatedOrderFields fields = new UpdatedOrderFields();
    private final LocalDateTime validDateTime = LocalDateTime.of(LocalDate.of(2020, 11, 11),
            LocalTime.of(00, 00));
    private final String validDescription = "Copier Paper";
    private final Address validAddress = new Address("123, Jurong West Ave 6, #08-111");
    private Index validClientId;
    private Index validOrderId;

    {
        try {
            validClientId = ParserUtil.parseClientIndex("1");
            validOrderId = ParserUtil.parseOrderIndex("1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validGetAndSetDateTime() {
        fields.setDateTime(validDateTime);
        LocalDateTime obtainedDateTime = fields.getDateTime().get();
        assertEquals(obtainedDateTime, validDateTime);
    }

    @Test
    public void validGetAndSetDescription() {
        fields.setDescription(validDescription);
        String obtainedDescription = fields.getDescription().get();
        assertEquals(obtainedDescription, validDescription);
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
    public void setValidOrderId() {
        fields.setOrderId(validOrderId);
        Index obtainedOrderId = fields.getOrderId().get();
        assertEquals(validOrderId, obtainedOrderId);
    }
}
