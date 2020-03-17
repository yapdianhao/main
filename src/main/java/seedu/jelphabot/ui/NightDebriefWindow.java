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

public class NightDebriefWindow extends UiPart<Stage> {
    private static final String FXML = "NightDebriefWindow.fxml";

    private static final String NIGHT_DEBRIEF_STRING = "Goodbye! Before you go, "
                                                           + "here are the tasks that you have completed!";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage nightDebriefStage;
    private Logic logic;

    // Ui parts that are required to be in this Ui container
    private TaskListPanel taskListPanel;

    // resultDisplay box from MainWindow will be used to
    // display the message of the "night debrief" to the user
    // TODO: customise this box to be something other than resultDisplay
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    public NightDebriefWindow(Stage nightDebriefStage, Logic logic) {
        super(FXML, nightDebriefStage);

        logger.info("Initialising NightDebriefWindow");

        //set dependencies
        this.nightDebriefStage = nightDebriefStage;
        this.logic = logic;

        //configure the UI to custom dimensions
        setWindowDefaultSize(logic.getPopUpWindowGuiSettings());
    }

    void fillWindow() {
        // get the list of completed tasks
        ObservableList<Task> taskList = logic.getFilteredByCompleteTaskList();
        taskListPanel = new TaskListPanel(taskList);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        //initialise resultDisplay
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        resultDisplay.setFeedbackToUser(NIGHT_DEBRIEF_STRING);

    }

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    void show() {
        logger.info("Showing nightDebriefStage");
        nightDebriefStage.show();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        nightDebriefStage.setHeight(guiSettings.getWindowHeight());
        nightDebriefStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            nightDebriefStage.setX(guiSettings.getWindowCoordinates().getX());
            nightDebriefStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }
}
