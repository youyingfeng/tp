package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedOrder.INVALID_FORMAT_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.SHOES;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;

public class JsonAdaptedOrderTest {
    private static final String EMPTY_STRING = "";
    private static final String WHITESPACE_STRING = " ";
    private static final String INVALID_DATE = "2020-02-20";
    private static final int INVALID_ID_NEGATIVE = -1;
    private static final int INVALID_ID_ZERO = 0;

    private static final int VALID_ORDER_ID = SHOES.getOrderId().getZeroBased();
    private static final int VALID_CLIENT_ID = SHOES.getClientId().getZeroBased();
    private static final String VALID_DESC = SHOES.getDescription();
    private static final String VALID_DELIVERY_DATE = SHOES.getDeliveryDateTime().toString();
    private static final String VALID_CREATION_DATE = SHOES.getCreationDateTime().toString();
    private static final String VALID_ADDRESS = SHOES.getAddress().value;

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(SHOES);
        assertEquals(SHOES, order.toModelType());
    }

    @Test
    public void toModelType_invalidOrderId_throwsIllegalValueException() {
        JsonAdaptedOrder orderNegativeOrderId = new JsonAdaptedOrder(INVALID_ID_NEGATIVE, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderZeroOrderId = new JsonAdaptedOrder(INVALID_ID_ZERO, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);

        String expectedMessage = String.format(INVALID_FORMAT_MESSAGE_FORMAT, "Order ID");

        assertThrows(IllegalValueException.class, expectedMessage, orderNegativeOrderId::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, orderZeroOrderId::toModelType);
    }

    @Test
    public void toModelType_invalidClientId_throwsIllegalValueException() {
        JsonAdaptedOrder orderNegativeClientId = new JsonAdaptedOrder(VALID_ORDER_ID, INVALID_ID_NEGATIVE, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderZeroClientId = new JsonAdaptedOrder(VALID_ORDER_ID, INVALID_ID_ZERO, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);

        String expectedMessage = String.format(INVALID_FORMAT_MESSAGE_FORMAT, "Client ID");

        assertThrows(IllegalValueException.class, expectedMessage, orderNegativeClientId::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, orderZeroClientId::toModelType);
    }

    @Test
    public void toModelType_invalidDesc_throwsIllegalValueException() {
        JsonAdaptedOrder orderEmptyDesc = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, EMPTY_STRING,
                VALID_ADDRESS, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderWhitespaceDesc = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, WHITESPACE_STRING,
                VALID_ADDRESS, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Description");
        assertThrows(IllegalValueException.class, expectedMessage, orderEmptyDesc::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, orderWhitespaceDesc::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedOrder orderEmptyAddress = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                EMPTY_STRING, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderWhitespaceAddress = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                WHITESPACE_STRING, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, orderEmptyAddress::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, orderWhitespaceAddress::toModelType);
    }

    @Test
    public void toModelType_invalidDeliveryDate_throwsIllegalValueException() {
        JsonAdaptedOrder orderEmptyDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, EMPTY_STRING, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderWhitespaceDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, WHITESPACE_STRING, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderInvalidDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, INVALID_DATE, VALID_CREATION_DATE, true);

        String missingFieldMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Delivery Date/Time");
        String wrongFormatMessage = String.format(INVALID_FORMAT_MESSAGE_FORMAT, "Delivery Date/Time");

        assertThrows(IllegalValueException.class, missingFieldMessage, orderEmptyDate::toModelType);
        assertThrows(IllegalValueException.class, missingFieldMessage, orderWhitespaceDate::toModelType);
        assertThrows(IllegalValueException.class, wrongFormatMessage, orderInvalidDate::toModelType);
    }

    @Test
    public void toModelType_invalidCreationDate_throwsIllegalValueException() {
        JsonAdaptedOrder orderEmptyDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, EMPTY_STRING, true);
        JsonAdaptedOrder orderWhitespaceDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, WHITESPACE_STRING, true);
        JsonAdaptedOrder orderInvalidDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, INVALID_DATE, true);

        String missingFieldMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Creation Date/Time");
        String wrongFormatMessage = String.format(INVALID_FORMAT_MESSAGE_FORMAT, "Creation Date/Time");

        assertThrows(IllegalValueException.class, missingFieldMessage, orderEmptyDate::toModelType);
        assertThrows(IllegalValueException.class, missingFieldMessage, orderWhitespaceDate::toModelType);
        assertThrows(IllegalValueException.class, wrongFormatMessage, orderInvalidDate::toModelType);
    }

    @Test
    public void toModelType_nullValues_throwsIllegalValueException() {
        JsonAdaptedOrder orderNullDescription = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, null,
                VALID_ADDRESS, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderNullDeliveryDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                null, VALID_DELIVERY_DATE, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderNullCreationDate = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, null, VALID_CREATION_DATE, true);
        JsonAdaptedOrder orderNullIsDone = new JsonAdaptedOrder(VALID_ORDER_ID, VALID_CLIENT_ID, VALID_DESC,
                VALID_ADDRESS, VALID_DELIVERY_DATE, null, true);

        String expectedDescriptionMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Description");
        String expectedAddressMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        String expectedDeliveryDateMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Delivery Date/Time");
        String expectedCreationDateMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Creation Date/Time");
    }
}
