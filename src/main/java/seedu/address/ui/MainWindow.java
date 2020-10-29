package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;
import seedu.address.ui.events.LogOnceEvent;
import seedu.address.ui.events.LogOnceEventHandler;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    private static final String FXML = "NewMainWindow.fxml";
    private static MainWindow mainWindow = null;
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private OrderListPanel orderListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private ErrorWindow errorWindow;
    private NewClientForm newClientForm;
    private NewOrderForm newOrderForm;

    // Stores the state of the view
    private Page currentPage;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private Button helpMenuButton;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private HBox resultDisplayPlaceholder;

    @FXML
    private HBox statusbarPlaceholder;

    @FXML
    private Label listTitle;

    @FXML
    private VBox extraInfoPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    private MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        setAccelerators();
        setEventHandlers();

        helpWindow = new HelpWindow();
        errorWindow = new ErrorWindow();
        newClientForm = new NewClientForm(this);
        newOrderForm = new NewOrderForm(this);

        currentPage = Page.CLIENTS;
    }

    public static MainWindow setInstance(Stage primaryStage, Logic logic) {
        mainWindow = new MainWindow(primaryStage, logic);
        return mainWindow;
    }

    public static MainWindow getInstance() {
        assert mainWindow != null;
        return mainWindow;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setButtonAccelerator(helpMenuButton, KeyCombination.valueOf("F1"));
    }

    private void setButtonAccelerator(Button button, KeyCombination keyCombination) {
        assert(button != null);

        Scene scene = button.getScene();
        assert(scene != null);

        scene.getAccelerators().put(keyCombination,
                new Runnable() {
                    @FXML
                    public void run() {
                        button.fire();
                    }
                });
    }

    void setEventHandlers() {
        this.getRoot().addEventHandler(LogOnceEvent.LOGONCE_EVENT_TYPE, new LogOnceEventHandler() {
            @Override
            public void onDisplayOrderEvent(Order order) {
                showOrderInfo(order);
            }

            @Override
            public void onDisplayClientEvent(Client client) {
                showClientInfo(client);
            }

            @Override
            public void onDeletionEvent(String command) {
                extraInfoPlaceholder.getChildren().clear();
                try {
                    executeCommand(command);
                } catch (CommandException | ParseException e) {
                    // there is no need to do anything as error handling has already been performed
                    // inside the method
                    // hence this catch block is empty
                }
            }
        });
    }

    void showOrderInfo(Order order) {
        extraInfoPlaceholder.getChildren().clear();
        extraInfoPlaceholder.getChildren().add(new OrderInfoDisplay(order).getRoot());
    }

    void showClientInfo(Client client) {
        extraInfoPlaceholder.getChildren().clear();
        extraInfoPlaceholder.getChildren().add(new ClientInfoDisplay(client).getRoot());
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        orderListPanel = new OrderListPanel(logic.getFilteredOrderList());

        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        // AnchorPane.setLeftAnchor(resultDisplay.getRoot(), 10.0);
        resultDisplayPlaceholder.setAlignment(Pos.CENTER_LEFT);

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        // AnchorPane.setRightAnchor(statusBarFooter.getRoot(), 10.0);
        statusbarPlaceholder.setAlignment(Pos.CENTER_RIGHT);

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Changes the view to display the list of Clients.
     */
    @FXML
    public void handleClients() {
        if (currentPage != Page.CLIENTS) {
            // Only execute if clients are not already displayed
            currentPage = Page.CLIENTS;
            listTitle.setText(" Clients");
            personListPanel = new PersonListPanel(logic.getFilteredPersonList());
            personListPanelPlaceholder.getChildren().clear();
            personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        } else {
            listTitle.setText(" Clients");

            personListPanel = new PersonListPanel(logic.getUnfilteredPersonList());
            personListPanelPlaceholder.getChildren().removeAll();
            personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        }
    }

    /**
     * Changes the view to display the list of Orders.
     */
    @FXML
    public void handleOrders() {
        if (currentPage != Page.ORDERS) {
            // Only execute if orders are not already displayed
            currentPage = Page.ORDERS;
            listTitle.setText(" Orders");
            orderListPanel = new OrderListPanel(logic.getFilteredOrderList());
            personListPanelPlaceholder.getChildren().clear();
            personListPanelPlaceholder.getChildren().add(orderListPanel.getRoot());
        } else {
            listTitle.setText(" Orders");

            orderListPanel = new OrderListPanel(logic.getUnfilteredOrderList());
            personListPanelPlaceholder.getChildren().removeAll();
            personListPanelPlaceholder.getChildren().add(orderListPanel.getRoot());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    private void handleError() {
        if (!errorWindow.isShowing()) {
            errorWindow.show();
        } else {
            errorWindow.focus();
        }
    }

    @FXML
    private void handleAdd() {
        if (currentPage == Page.CLIENTS) {
            System.out.println("kak");
            extraInfoPlaceholder.getChildren().clear();
            extraInfoPlaceholder.getChildren().add(newClientForm.getRoot());
        } else if (currentPage == Page.ORDERS) {
            System.out.println("kek");
            extraInfoPlaceholder.getChildren().clear();
            extraInfoPlaceholder.getChildren().add(newOrderForm.getRoot());
        } else {
            extraInfoPlaceholder.getChildren().removeAll();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            // TODO: set a popup message that can be easily closed by pressing enter
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser("Invalid command: " + commandText);
            errorWindow.setErrorText(e.getMessage());
            this.handleError();
            throw e;
        }
    }

    enum Page {
        CLIENTS, ORDERS;
    }
}
