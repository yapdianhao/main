package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.logic.Logic;
import seedu.jelphabot.model.task.Task;

/**
 * The MorningCall Window that is shown upon the app startup. Follows the
 * styling specified in MainWindow.
 */
public class MorningCallWindow extends UiPart<Stage> {

    private static final String FXML = "MorningCallWindow.fxml";

    private static final String MORNING_CALL_STRING = "Hello! Here are your incomplete tasks so far!";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage morningCallStage;
    private Logic logic;

    // Ui parts that are required to be in this Ui container
    private TaskListPanel taskListPanel;
    // resultDisplay box from MainWindow will be used to
    // display the message of the "morning call" to user
    // TODO: customise this box to be something other than resultDisplay
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    public MorningCallWindow(Stage morningCallStage, Logic logic) {

        super(FXML, morningCallStage);

        logger.info("Initialising morningCallWindow");
        // set dependencies
        this.morningCallStage = morningCallStage;
        this.logic = logic;
        // configure the UI to custom dimensions
        setWindowDefaultSize(logic.getPopUpWindowGuiSettings());

        //setAcclerators();

    }

    /**
     * Fills the placeholders of this window
     */
    void fillWindow() {
        // get the list of Incomplete tasks
        ObservableList<Task> taskList = logic.getFilteredByIncompleteDueTodayTaskList();
        taskListPanel = new TaskListPanel(taskList);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        // initialise resultDisplay
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        resultDisplay.setFeedbackToUser(MORNING_CALL_STRING);
    }

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    void show() {
        logger.info("Showing morningCallStage");
        morningCallStage.show();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        morningCallStage.setHeight(guiSettings.getWindowHeight());
        morningCallStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            morningCallStage.setX(guiSettings.getWindowCoordinates().getX());
            morningCallStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    @FXML
    private void closeButtonAction() {
        logger.info("Close Button pressed. Closing morningCallWindow");
        morningCallStage.close();
    }

}
