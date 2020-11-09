package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT_ZEROBASED;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Order;

public class UpdateOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void executeTestUpdateAll() {
        try {
            UpdatedOrderFields fieldsToUpdate = new UpdatedOrderFields();
            fieldsToUpdate.setOrderId(Index.fromZeroBased(2));
            fieldsToUpdate.setClientId(Index.fromZeroBased(1));
            fieldsToUpdate.setDescription("new order description");
            fieldsToUpdate.setAddress(ParserUtil.parseAddress("new order address"));
            fieldsToUpdate.setDateTime(ParserUtil.parseDate("2020-11-11 1111"));
            UpdateOrderCommand commandToExecute = new UpdateOrderCommand(fieldsToUpdate);

            ObservableList<Order> orderList = model.getFilteredOrderList();
            Order initialOrder = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Order order : orderList) {
                if (order.getOrderId().getZeroBased() == targetId) {
                    initialOrder = order;
                    break;
                }
            }

            assert !Objects.isNull(initialOrder);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Order> newOrderList = model.getFilteredOrderList();
            Order newOrder = null;

            for (Order order : newOrderList) {
                if (order.getOrderId().getZeroBased() == targetId) {
                    newOrder = order;
                    break;
                }
            }

            assert !Objects.isNull(newOrder);

            int initialOrderId = initialOrder.getOrderId().getZeroBased();
            int newOrderId = newOrder.getOrderId().getZeroBased();
            assertEquals(initialOrderId, newOrderId);

            int initialClientId = initialOrder.getClientId().getZeroBased();
            int newClientId = newOrder.getClientId().getZeroBased();
            assertNotEquals(initialClientId, newClientId);

            String initialDescription = initialOrder.getDescription();
            String newDescription = newOrder.getDescription();
            assertNotEquals(initialDescription, newDescription);

            String initialAddress = initialOrder.getAddress().toString();
            String newAddress = newOrder.getAddress().toString();
            assertNotEquals(initialAddress, newAddress);

            LocalDateTime initialDate = initialOrder.getDeliveryDateTime();
            LocalDateTime newDate = newOrder.getDeliveryDateTime();
            assertNotEquals(initialDate, newDate);


        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void executeTestUpdateAddress() {
        try {
            UpdatedOrderFields fieldsToUpdate = new UpdatedOrderFields();
            fieldsToUpdate.setOrderId(Index.fromZeroBased(2));
            fieldsToUpdate.setAddress(ParserUtil.parseAddress("new order address"));
            UpdateOrderCommand commandToExecute = new UpdateOrderCommand(fieldsToUpdate);

            ObservableList<Order> orderList = model.getFilteredOrderList();
            Order initialOrder = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Order order : orderList) {
                if (order.getOrderId().getZeroBased() == targetId) {
                    initialOrder = order;
                    break;
                }
            }

            assert !Objects.isNull(initialOrder);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Order> newOrderList = model.getFilteredOrderList();
            Order newOrder = null;

            for (Order order : newOrderList) {
                if (order.getOrderId().getZeroBased() == targetId) {
                    newOrder = order;
                    break;
                }
            }

            assert !Objects.isNull(newOrder);

            int initialOrderId = initialOrder.getOrderId().getZeroBased();
            int newOrderId = newOrder.getOrderId().getZeroBased();
            assertEquals(initialOrderId, newOrderId);

            int initialClientId = initialOrder.getClientId().getZeroBased();
            int newClientId = newOrder.getClientId().getZeroBased();
            assertEquals(initialClientId, newClientId);

            String initialDescription = initialOrder.getDescription();
            String newDescription = newOrder.getDescription();
            assertEquals(initialDescription, newDescription);

            String initialAddress = initialOrder.getAddress().toString();
            String newAddress = newOrder.getAddress().toString();
            assertNotEquals(initialAddress, newAddress);

            LocalDateTime initialDate = initialOrder.getDeliveryDateTime();
            LocalDateTime newDate = newOrder.getDeliveryDateTime();
            assertEquals(initialDate, newDate);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void executeTestUpdateDate() {
        try {
            UpdatedOrderFields fieldsToUpdate = new UpdatedOrderFields();
            fieldsToUpdate.setOrderId(Index.fromZeroBased(2));
            fieldsToUpdate.setDateTime(ParserUtil.parseDate("2020-11-11 1111"));
            UpdateOrderCommand commandToExecute = new UpdateOrderCommand(fieldsToUpdate);

            ObservableList<Order> orderList = model.getFilteredOrderList();
            Order initialOrder = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Order order : orderList) {
                if (order.getOrderId().getZeroBased() == targetId) {
                    initialOrder = order;
                    break;
                }
            }

            assert !Objects.isNull(initialOrder);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Order> newOrderList = model.getFilteredOrderList();
            Order newOrder = null;

            for (Order order : newOrderList) {
                if (order.getOrderId().getZeroBased() == targetId) {
                    newOrder = order;
                    break;
                }
            }

            assert !Objects.isNull(newOrder);

            int initialOrderId = initialOrder.getOrderId().getZeroBased();
            int newOrderId = newOrder.getOrderId().getZeroBased();
            assertEquals(initialOrderId, newOrderId);

            int initialClientId = initialOrder.getClientId().getZeroBased();
            int newClientId = newOrder.getClientId().getZeroBased();
            assertEquals(initialClientId, newClientId);

            String initialDescription = initialOrder.getDescription();
            String newDescription = newOrder.getDescription();
            assertEquals(initialDescription, newDescription);

            String initialAddress = initialOrder.getAddress().toString();
            String newAddress = newOrder.getAddress().toString();
            assertEquals(initialAddress, newAddress);

            LocalDateTime initialDate = initialOrder.getDeliveryDateTime();
            LocalDateTime newDate = newOrder.getDeliveryDateTime();
            assertNotEquals(initialDate, newDate);


        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void executeTestUpdateDescription() {
        UpdatedOrderFields fieldsToUpdate = new UpdatedOrderFields();
        fieldsToUpdate.setOrderId(Index.fromZeroBased(2));
        fieldsToUpdate.setDescription("new order description");
        UpdateOrderCommand commandToExecute = new UpdateOrderCommand(fieldsToUpdate);

        ObservableList<Order> orderList = model.getFilteredOrderList();
        Order initialOrder = null;

        int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

        for (Order order : orderList) {
            if (order.getOrderId().getZeroBased() == targetId) {
                initialOrder = order;
                break;
            }
        }

        assert !Objects.isNull(initialOrder);

        try {
            commandToExecute.execute(model, commandHistory);
        } catch (CommandException e) {
            assert false;
        }

        ObservableList<Order> newOrderList = model.getFilteredOrderList();
        Order newOrder = null;

        for (Order order : newOrderList) {
            if (order.getOrderId().getZeroBased() == targetId) {
                newOrder = order;
                break;
            }
        }

        assert !Objects.isNull(newOrder);

        int initialOrderId = initialOrder.getOrderId().getZeroBased();
        int newOrderId = newOrder.getOrderId().getZeroBased();
        assertEquals(initialOrderId, newOrderId);

        int initialClientId = initialOrder.getClientId().getZeroBased();
        int newClientId = newOrder.getClientId().getZeroBased();
        assertEquals(initialClientId, newClientId);

        String initialDescription = initialOrder.getDescription();
        String newDescription = newOrder.getDescription();
        assertNotEquals(initialDescription, newDescription);

        String initialAddress = initialOrder.getAddress().toString();
        String newAddress = newOrder.getAddress().toString();
        assertEquals(initialAddress, newAddress);

        LocalDateTime initialDate = initialOrder.getDeliveryDateTime();
        LocalDateTime newDate = newOrder.getDeliveryDateTime();
        assertEquals(initialDate, newDate);
    }

    @Test
    public void executeTestUpdateClientId() {
        UpdatedOrderFields fieldsToUpdate = new UpdatedOrderFields();
        fieldsToUpdate.setOrderId(Index.fromZeroBased(2));
        fieldsToUpdate.setClientId(Index.fromZeroBased(1));
        UpdateOrderCommand commandToExecute = new UpdateOrderCommand(fieldsToUpdate);

        ObservableList<Order> orderList = model.getFilteredOrderList();
        Order initialOrder = null;

        int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

        for (Order order : orderList) {
            if (order.getOrderId().getZeroBased() == targetId) {
                initialOrder = order;
                break;
            }
        }

        assert !Objects.isNull(initialOrder);

        try {
            commandToExecute.execute(model, commandHistory);
        } catch (CommandException e) {
            assert false;
        }

        ObservableList<Order> newOrderList = model.getFilteredOrderList();
        Order newOrder = null;

        for (Order order : newOrderList) {
            if (order.getOrderId().getZeroBased() == targetId) {
                newOrder = order;
                break;
            }
        }

        assert !Objects.isNull(newOrder);

        int initialOrderId = initialOrder.getOrderId().getZeroBased();
        int newOrderId = newOrder.getOrderId().getZeroBased();
        assertEquals(initialOrderId, newOrderId);

        int initialClientId = initialOrder.getClientId().getZeroBased();
        int newClientId = newOrder.getClientId().getZeroBased();
        assertNotEquals(initialClientId, newClientId);

        String initialDescription = initialOrder.getDescription();
        String newDescription = newOrder.getDescription();
        assertEquals(initialDescription, newDescription);

        String initialAddress = initialOrder.getAddress().toString();
        String newAddress = newOrder.getAddress().toString();
        assertEquals(initialAddress, newAddress);

        LocalDateTime initialDate = initialOrder.getDeliveryDateTime();
        LocalDateTime newDate = newOrder.getDeliveryDateTime();
        assertEquals(initialDate, newDate);
    }
}
