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

public class MorningCallWindow extends UiPart<Stage> {
    private static final String FXML = "MorningCallWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage morningCallStage;
    private Logic logic;

    // Ui parts that are required to be in this Ui contioner
    private TaskListPanel taskListPanel;

    @FXML
    private StackPane taskListPanelPlaceholder;

    public MorningCallWindow(Stage morningCallStage, Logic logic) {

        super(FXML, morningCallStage);

        logger.info("Initialising morningCallWindow");
        // set dependencies
        this.morningCallStage = morningCallStage;
        this.logic = logic;
        // configure the UI
        // for now, set the size to be the same as MainWindow
        // TODO: configure settings to customize the size of the window, not follow MainWindow dims
        setWindowDefaultSize(logic.getGuiSettings());

        //setAcclerators();

    }

    void fillWindow() {
        // get the list of Incomplete tasks
        ObservableList<Task> taskList = logic.getFilteredByIncompleteTaskList();
        taskListPanel = new TaskListPanel(taskList);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
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
