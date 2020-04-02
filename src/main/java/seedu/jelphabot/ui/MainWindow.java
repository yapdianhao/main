package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.commons.util.DateUtil;
import seedu.jelphabot.logic.Logic;
import seedu.jelphabot.logic.commands.CommandResult;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.logic.parser.exceptions.ParseException;
import seedu.jelphabot.model.calendar.CalendarDate;
import seedu.jelphabot.model.productivity.Productivity;
import seedu.jelphabot.model.productivity.ProductivityList;
import seedu.jelphabot.model.task.GroupedTaskList;
import seedu.jelphabot.model.task.GroupedTaskList.Category;

/**
 * The Main Window. Provides the basic application layout containing a menu bar
 * and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static CalendarPanel calendarPanel;
    private static boolean firstStart = true;
    private static final String WELCOME_STRING = "Welcome to JelphaBot! Here are the tasks that you have due today!\n"
                                                     + "To go back to the list of your tasks, type list!";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private GroupedTaskListPanel taskListPanel;
    private CalendarTaskListPanel calendarTaskListPanel;
    private ProductivityPanel productivityPanel;
    private ResultDisplay resultDisplay;
    private SummaryPanel summaryPanel;
    private HelpWindow helpWindow;

    private Productivity productivity;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private TabPane mainWindowTabPane;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane calendarTaskListPanelPlaceholder;

    @FXML
    private StackPane calendarPanelPlaceholder;

    @FXML
    private StackPane productivityPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane summaryPanelPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666 is fixed in later version of
         * SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will not
         * work when the focus is in them because the key event is consumed by the
         * TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is in
         * CommandBox or ResultDisplay.
         */
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
        taskListPanel = new GroupedTaskListPanel(logic.getGroupedTaskList(Category.DATE));
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        //update getFilteredCalendarTaskList
        calendarTaskListPanel = new CalendarTaskListPanel(logic.getFilteredCalendarTaskList());
        logic.updateFilteredCalendarTaskList(DateUtil.getDueTodayPredicate());
        calendarTaskListPanelPlaceholder.getChildren().add(calendarTaskListPanel.getRoot());

        calendarPanel = new CalendarPanel(CalendarDate.getCurrent(), mainWindowTabPane);
        calendarPanelPlaceholder.getChildren().add(calendarPanel.getRoot());

        summaryPanel = new SummaryPanel(logic.getFilteredByIncompleteDueTodayTaskList(),
            logic.getFilteredByCompletedTodayTaskList(), mainWindowTabPane
        );
        summaryPanelPlaceholder.getChildren().add(summaryPanel.getRoot());

        ProductivityList productivityList = logic.getProductivityList();
        productivityList.addProductivity(new Productivity(logic.getFilteredTaskList(), true, true, true));
        productivityPanel = new ProductivityPanel(productivityList.asUnmodifiableObservableList(), mainWindowTabPane);
        productivityPanelPlaceholder.getChildren().add(productivityPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        // StatusBarFooter statusBarFooter = new
        // StatusBarFooter(logic.getJelphaBotFilePath());
        // statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

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
            (int) primaryStage.getX(), (int) primaryStage.getY()
        );
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Switches view to productivity panel.
     */
    @FXML
    private void handleProductivity() {
        if (!productivityPanel.isShowing()) {
            productivityPanel.show();
        }
        // TODO: add case when alr on panel.
    }

    /**
     * Switches view to calendar panel.
     */
    @FXML
    private void handleCalendar() {
        if (!calendarPanel.isShowing()) {
            calendarPanel.show();
        }
    }

    /**
     * Switches view to summary panel.
     */
    @FXML
    public void handleSummary() {
        if (!summaryPanel.isShowing()) {
            summaryPanel.show();
        }

        if (firstStart) {
            resultDisplay.setFeedbackToUser(WELCOME_STRING);
            firstStart = false;
        }
    }

    /**
     * Switches view to Task List panel.
     */
    @FXML
    private void handleTaskList(GroupedTaskList.Category sublistCategory) {
        mainWindowTabPane.getSelectionModel().select(0);
        GroupedTaskList groupedTasks = logic.getGroupedTaskList(sublistCategory);
        taskListPanel = new GroupedTaskListPanel(groupedTasks);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

    }

    public GroupedTaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    public ProductivityPanel getProductivityPanel() {
        return productivityPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.jelphabot.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            } else if (commandResult.isExit()) {
                handleExit();
            }

            switch (commandResult.getTabSwitch()) {
            case CALENDAR:
                handleCalendar();
                break;
            case PRODUCTIVITY:
                handleProductivity();
                break;
            case SUMMARY:
                handleSummary();
                break;
            case TASK_LIST_DATE:
                handleTaskList(GroupedTaskList.Category.DATE);
                break;
            case TASK_LIST_MODULE:
                handleTaskList(Category.MODULE);
                break;
            case STAY_ON_CURRENT:
            default:
                // do nothing
                break;
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
