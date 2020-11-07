package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.DEFAULT_DATE_TIME_FORMATTER;
import static seedu.address.testutil.TypicalOrders.SHOES;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;


public class OrderTest {
    @Test
    public void isSameOrder() {
        // identity
        assertTrue(SHOES.isSameOrder(SHOES));

        // null -> return false
        assertFalse(SHOES.isSameOrder(null));

        // edited values -> return false
        Order editedShoes = new OrderBuilder(SHOES).withDescription("kappa").build();
        assertFalse(SHOES.isSameOrder(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withOrderId("10").build();
        assertFalse(SHOES.isSameOrder(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withClientId("10").build();
        assertFalse(SHOES.isSameOrder(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withAddress("kappa").build();
        assertFalse(SHOES.isSameOrder(editedShoes));

        editedShoes = new OrderBuilder(SHOES)
                .withDeliveryDate(LocalDateTime.now().format(DEFAULT_DATE_TIME_FORMATTER))
                .build();
        assertFalse(SHOES.isSameOrder(editedShoes));

        editedShoes = new OrderBuilder(SHOES)
                .withCreationDate(LocalDateTime.now().format(DEFAULT_DATE_TIME_FORMATTER))
                .build();
        assertFalse(SHOES.isSameOrder(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withCompletionStatus(!SHOES.isDone()).build();
        assertFalse(SHOES.isSameOrder(editedShoes));
    }

    @Test
    public void equals() {
        Order shoesCopy = new OrderBuilder(SHOES).build();
        assertTrue(SHOES.equals(shoesCopy));

        assertTrue(SHOES.equals(SHOES));

        assertFalse(SHOES.equals(null));

        assertFalse(SHOES.equals(5));

        Order editedShoes = new OrderBuilder(SHOES).withDescription("kappa").build();
        assertFalse(SHOES.equals(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withOrderId("10").build();
        assertFalse(SHOES.equals(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withClientId("10").build();
        assertFalse(SHOES.equals(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withAddress("kappa").build();
        assertFalse(SHOES.equals(editedShoes));

        editedShoes = new OrderBuilder(SHOES)
                .withDeliveryDate(LocalDateTime.now().format(DEFAULT_DATE_TIME_FORMATTER))
                .build();
        assertFalse(SHOES.equals(editedShoes));

        editedShoes = new OrderBuilder(SHOES)
                .withCreationDate(LocalDateTime.now().format(DEFAULT_DATE_TIME_FORMATTER)).build();
        assertFalse(SHOES.equals(editedShoes));

        editedShoes = new OrderBuilder(SHOES).withCompletionStatus(!SHOES.isDone()).build();
        assertFalse(SHOES.equals(editedShoes));
    }
}
