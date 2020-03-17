package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.logic.Logic;
import seedu.jelphabot.model.task.Task;

/**
 * The Productivity Panel. Provides the basic application layout of productivity of tasks.
 */
public class ProductivityPanel extends UiPart<Stage> {

    private static final String FXML = "ProductivityPanel.fxml";

    private static final String PRODUCTIVITY_STRING = "Hello! This is your productivity today!";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage productivityStage;
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

    // TODO: insert piechart or progress bar.
    public ProductivityPanel(Stage productivityStage, Logic logic) {

        super(FXML, productivityStage);

        logger.info("Initialising productivity panel stage");

        this.productivityStage = productivityStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        // setAccelerators();
    }

    /**
     * Fills up all the placeholders of this panel.
     */
    void fillPanel() {
        // get the list of Incomplete tasks
        ObservableList<Task> taskList = logic.getFilteredTaskList();
        taskListPanel = new TaskListPanel(taskList);

        // TODO: extract duration of each task in the list and input to pie chart for the day/week

        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
        PieChart.Data slice2 = new PieChart.Data("Phone", 67);
        PieChart.Data slice3 = new PieChart.Data("Tablet", 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        VBox vbox = new VBox(pieChart);

        Scene scene = new Scene(vbox, 400, 200);

        productivityStage.setScene(scene);
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        productivityStage.setHeight(guiSettings.getWindowHeight());
        productivityStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            productivityStage.setX(guiSettings.getWindowCoordinates().getX());
            productivityStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    void show() {
        logger.info("Showing productivityStage");
        productivityStage.show();
    }
}
