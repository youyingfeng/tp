package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT_ZEROBASED;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
import seedu.address.model.person.Client;

public class UpdateClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void executeTestUpdateAll() {
        try {
            UpdatedClientFields fieldsToUpdate = new UpdatedClientFields();
            fieldsToUpdate.setClientId(Index.fromZeroBased(2));
            fieldsToUpdate.setName(ParserUtil.parseName("Kelly"));
            fieldsToUpdate.setPhone(ParserUtil.parsePhone("12345678"));
            fieldsToUpdate.setEmail(ParserUtil.parseEmail("newEmail@email.com"));
            fieldsToUpdate.setAddress(ParserUtil.parseAddress("new address of client"));
            UpdateClientCommand commandToExecute = new UpdateClientCommand(fieldsToUpdate);

            ObservableList<Client> clientList = model.getFilteredPersonList();
            Client initialClient = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Client client : clientList) {
                if (client.getClientId() == targetId) {
                    initialClient = client;
                    break;
                }
            }

            assert !Objects.isNull(initialClient);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Client> newClientList = model.getFilteredPersonList();
            Client newClient = null;

            for (Client client : newClientList) {
                if (client.getClientId() == targetId) {
                    newClient = client;
                    break;
                }
            }

            assert !Objects.isNull(newClient);

            int initialClientClientId = initialClient.getClientId();
            int newClientClientId = newClient.getClientId();
            assertEquals(initialClientClientId, newClientClientId);

            String newClientName = newClient.getName().toString();
            String initialClientName = initialClient.getName().toString();
            assertNotEquals(newClientName, initialClientName);

            String initialClientAddress = initialClient.getAddress().toString();
            String newClientAddress = newClient.getAddress().toString();
            assertNotEquals(initialClientAddress, newClientAddress);

            String initialClientEmail = initialClient.getEmail().toString();
            String newClientEmail = newClient.getEmail().toString();
            assertNotEquals(initialClientEmail, newClientEmail);

            String initialClientPhone = initialClient.getPhone().toString();
            String newClientPhone = newClient.getPhone().toString();
            assertNotEquals(initialClientPhone, newClientPhone);

        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void executeTestUpdateName() {
        try {
            UpdatedClientFields fieldsToUpdate = new UpdatedClientFields();
            fieldsToUpdate.setClientId(Index.fromZeroBased(2));
            fieldsToUpdate.setName(ParserUtil.parseName("Kelly"));
            UpdateClientCommand commandToExecute = new UpdateClientCommand(fieldsToUpdate);

            ObservableList<Client> clientList = model.getFilteredPersonList();
            Client initialClient = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Client client : clientList) {
                if (client.getClientId() == targetId) {
                    initialClient = client;
                    break;
                }
            }

            assert !Objects.isNull(initialClient);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Client> newClientList = model.getFilteredPersonList();
            Client newClient = null;

            for (Client client : newClientList) {
                if (client.getClientId() == targetId) {
                    newClient = client;
                    break;
                }
            }

            assert !Objects.isNull(newClient);

            int initialClientClientId = initialClient.getClientId();
            int newClientClientId = newClient.getClientId();
            assertEquals(initialClientClientId, newClientClientId);

            String newClientName = newClient.getName().toString();
            String initialClientName = initialClient.getName().toString();
            assertNotEquals(newClientName, initialClientName);

            String initialClientAddress = initialClient.getAddress().toString();
            String newClientAddress = newClient.getAddress().toString();
            assertEquals(initialClientAddress, newClientAddress);

            String initialClientEmail = initialClient.getEmail().toString();
            String newClientEmail = newClient.getEmail().toString();
            assertEquals(initialClientEmail, newClientEmail);

            String initialClientPhone = initialClient.getPhone().toString();
            String newClientPhone = newClient.getPhone().toString();
            assertEquals(initialClientPhone, newClientPhone);

        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void executeTestUpdatePhone() {
        try {
            UpdatedClientFields fieldsToUpdate = new UpdatedClientFields();
            fieldsToUpdate.setClientId(Index.fromZeroBased(2));
            fieldsToUpdate.setPhone(ParserUtil.parsePhone("12345678"));
            UpdateClientCommand commandToExecute = new UpdateClientCommand(fieldsToUpdate);

            ObservableList<Client> clientList = model.getFilteredPersonList();
            Client initialClient = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Client client : clientList) {
                if (client.getClientId() == targetId) {
                    initialClient = client;
                    break;
                }
            }

            assert !Objects.isNull(initialClient);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Client> newClientList = model.getFilteredPersonList();
            Client newClient = null;

            for (Client client : newClientList) {
                if (client.getClientId() == targetId) {
                    newClient = client;
                    break;
                }
            }

            assert !Objects.isNull(newClient);

            int initialClientClientId = initialClient.getClientId();
            int newClientClientId = newClient.getClientId();
            assertEquals(initialClientClientId, newClientClientId);

            String newClientName = newClient.getName().toString();
            String initialClientName = initialClient.getName().toString();
            assertEquals(newClientName, initialClientName);

            String initialClientAddress = initialClient.getAddress().toString();
            String newClientAddress = newClient.getAddress().toString();
            assertEquals(initialClientAddress, newClientAddress);

            String initialClientEmail = initialClient.getEmail().toString();
            String newClientEmail = newClient.getEmail().toString();
            assertEquals(initialClientEmail, newClientEmail);

            String initialClientPhone = initialClient.getPhone().toString();
            String newClientPhone = newClient.getPhone().toString();
            assertNotEquals(initialClientPhone, newClientPhone);

        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void executeTestUpdateEmail() {
        try {
            UpdatedClientFields fieldsToUpdate = new UpdatedClientFields();
            fieldsToUpdate.setClientId(Index.fromZeroBased(2));
            fieldsToUpdate.setEmail(ParserUtil.parseEmail("newEmail@email.com"));
            UpdateClientCommand commandToExecute = new UpdateClientCommand(fieldsToUpdate);

            ObservableList<Client> clientList = model.getFilteredPersonList();
            Client initialClient = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Client client : clientList) {
                if (client.getClientId() == targetId) {
                    initialClient = client;
                    break;
                }
            }

            assert !Objects.isNull(initialClient);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Client> newClientList = model.getFilteredPersonList();
            Client newClient = null;

            for (Client client : newClientList) {
                if (client.getClientId() == targetId) {
                    newClient = client;
                    break;
                }
            }

            assert !Objects.isNull(newClient);

            int initialClientClientId = initialClient.getClientId();
            int newClientClientId = newClient.getClientId();
            assertEquals(initialClientClientId, newClientClientId);

            String newClientName = newClient.getName().toString();
            String initialClientName = initialClient.getName().toString();
            assertEquals(newClientName, initialClientName);

            String initialClientAddress = initialClient.getAddress().toString();
            String newClientAddress = newClient.getAddress().toString();
            assertEquals(initialClientAddress, newClientAddress);

            String initialClientEmail = initialClient.getEmail().toString();
            String newClientEmail = newClient.getEmail().toString();
            assertNotEquals(initialClientEmail, newClientEmail);

            String initialClientPhone = initialClient.getPhone().toString();
            String newClientPhone = newClient.getPhone().toString();
            assertEquals(initialClientPhone, newClientPhone);

        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void executeTestUpdateAddress() {
        try {
            UpdatedClientFields fieldsToUpdate = new UpdatedClientFields();
            fieldsToUpdate.setClientId(Index.fromZeroBased(2));
            fieldsToUpdate.setAddress(ParserUtil.parseAddress("new address of client"));
            UpdateClientCommand commandToExecute = new UpdateClientCommand(fieldsToUpdate);

            ObservableList<Client> clientList = model.getFilteredPersonList();
            Client initialClient = null;

            int targetId = INDEX_SECOND_CLIENT_ZEROBASED.getZeroBased();

            for (Client client : clientList) {
                if (client.getClientId() == targetId) {
                    initialClient = client;
                    break;
                }
            }

            assert !Objects.isNull(initialClient);

            try {
                commandToExecute.execute(model, commandHistory);
            } catch (CommandException e) {
                assert false;
            }

            ObservableList<Client> newClientList = model.getFilteredPersonList();
            Client newClient = null;

            for (Client client : newClientList) {
                if (client.getClientId() == targetId) {
                    newClient = client;
                    break;
                }
            }

            assert !Objects.isNull(newClient);

            int initialClientClientId = initialClient.getClientId();
            int newClientClientId = newClient.getClientId();
            assertEquals(initialClientClientId, newClientClientId);

            String newClientName = newClient.getName().toString();
            String initialClientName = initialClient.getName().toString();
            assertEquals(newClientName, initialClientName);

            String initialClientAddress = initialClient.getAddress().toString();
            String newClientAddress = newClient.getAddress().toString();
            assertNotEquals(initialClientAddress, newClientAddress);

            String initialClientEmail = initialClient.getEmail().toString();
            String newClientEmail = newClient.getEmail().toString();
            assertEquals(initialClientEmail, newClientEmail);

            String initialClientPhone = initialClient.getPhone().toString();
            String newClientPhone = newClient.getPhone().toString();
            assertEquals(initialClientPhone, newClientPhone);

        } catch (ParseException e) {
            assert false;
        }
    }
}
