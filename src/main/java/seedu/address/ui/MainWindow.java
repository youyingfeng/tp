package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "NewMainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private OrderListPanel orderListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private ErrorWindow errorWindow;

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

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
        errorWindow = new ErrorWindow();
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

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */

        // basically this is to allow for kb shortcuts
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
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
        if (!listTitle.getText().equals(" Clients")) {
            // Only execute if clients are not already displayed
            listTitle.setText(" Clients");

            personListPanel = new PersonListPanel(logic.getFilteredPersonList());
            personListPanelPlaceholder.getChildren().removeAll();
            personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        } else {
            listTitle.setText(" Kappa");
        }
    }

    /**
     * Changes the view to display the list of Orders.
     */
    @FXML
    public void handleOrders() {
        if (!listTitle.getText().equals(" Orders")) {
            // Only execute if orders are not already dislayed
            listTitle.setText(" Orders");

            orderListPanel = new OrderListPanel(logic.getFilteredOrderList());
            personListPanelPlaceholder.getChildren().removeAll();
            personListPanelPlaceholder.getChildren().add(orderListPanel.getRoot());
        } else {
            listTitle.setText(" Kappa");
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

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
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
}
