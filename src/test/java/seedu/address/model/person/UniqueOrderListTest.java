import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BRACELET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BRACELET;
import seedu.address.model.person.Order;
import seedu.address.model.person.UniqueOrderList;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.BRACELET;
import static seedu.address.testutil.TypicalOrders.GLASSES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.OrderNotFoundException;
import seedu.address.model.person.exceptions.DuplicateOrderException;
import seedu.address.testutil.OrderBuilder;

public class UniqueOrderListTest {

    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();

    @Test
    public void contains_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(uniqueOrderList.contains(BRACELET));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        uniqueOrderList.add(BRACELET);
        assertTrue(uniqueOrderList.contains(BRACELET));
    }

    @Test
    public void contains_orderWithSameIdentityFieldsInList_returnsTrue() {
        uniqueOrderList.add(BRACELET);
        Order editedBracelet = new OrderBuilder(BRACELET).withAddress(VALID_ADDRESS_BRACELET)
                .withDate(VALID_DATE_BRACELET).build();
        assertTrue(uniqueOrderList.contains(editedBracelet));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
    }

    @Test
    public void add_duplicateOrder_throwsDuplicateOrderException() {
        uniqueOrderList.add(BRACELET);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.add(BRACELET));
    }

    @Test
    public void setOrder_nullTargetOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(null, BRACELET));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(BRACELET, null));
    }

    @Test
    public void setOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.setOrder(BRACELET, BRACELET));
    }

    @Test
    public void setOrder_editedOrderIsSameOrder_success() {
        uniqueOrderList.add(BRACELET);
        uniqueOrderList.setOrder(BRACELET, BRACELET);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(BRACELET);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasSameIdentity_success() {
        uniqueOrderList.add(BRACELET);
        Order editedBracelet = new OrderBuilder(BRACELET).withAddress(VALID_ADDRESS_BRACELET)
                .build();
        uniqueOrderList.setOrder(BRACELET, editedBracelet);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(editedBracelet);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasDifferentIdentity_success() {
        uniqueOrderList.add(BRACELET);
        uniqueOrderList.setOrder(BRACELET, GLASSES);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(GLASSES);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasNonUniqueIdentity_throwsDuplicateOrderException() {
        uniqueOrderList.add(BRACELET);
        uniqueOrderList.add(GLASSES);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.setOrder(BRACELET, GLASSES));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.remove(BRACELET));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        uniqueOrderList.add(BRACELET);
        uniqueOrderList.remove(BRACELET);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_nullUniqueOrderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((UniqueOrderList) null));
    }

    @Test
    public void setOrders_uniqueOrderList_replacesOwnListWithProvidedUniqueOrderList() {
        uniqueOrderList.add(BRACELET);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(GLASSES);
        uniqueOrderList.setOrders(expectedUniqueOrderList);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((List<Order>) null));
    }

    @Test
    public void setOrders_list_replacesOwnListWithProvidedList() {
        uniqueOrderList.add(BRACELET);
        List<Order> orderList = Collections.singletonList(GLASSES);
        uniqueOrderList.setOrders(orderList);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(GLASSES);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_listWithDuplicateOrders_throwsDuplicateOrderException() {
        List<Order> listWithDuplicateOrders = Arrays.asList(BRACELET, BRACELET);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.setOrders(listWithDuplicateOrders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueOrderList.asUnmodifiableObservableList().remove(0));
    }
}
