package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.logic.Logic;

public class MorningCallWindow extends UiPart<Stage> {
    private static final String FXML = "MorningCallWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage morningCallStage;
    private Logic logic;

    // Ui parts that are required to be in this Ui contioner
    private TaskListPanel taskListPanel;

    @FXML
    private StackPane taskListPanelPlaceholder;

    public MorningCallWindow(Stage morningCallStage) {
        super(FXML, morningCallStage);

        // set dependencies
        this.morningCallStage = morningCallStage;

        // configure the UI
        // for now, set the size to be the same as MainWindow
        // TODO: configure settings to customize the size of the window
        setWindowDefaultSize(logic.getGuiSettings());

        setAcclerators();

    }

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
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

}
