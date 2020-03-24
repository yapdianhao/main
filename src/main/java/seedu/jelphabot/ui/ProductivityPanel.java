package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.productivity.Productivity;

/**
 * The Productivity Panel. Provides the basic application layout of productivity of tasks.
 */
public class ProductivityPanel extends UiPart<Region> {
    private static final String FXML = "ProductivityPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProductivityPanel.class);

    // @FXML
    // private Text productivityView;

    @FXML
    private HBox cardPane;
    @FXML
    private Text productivityToday;
    @FXML
    private Text runningTimers;
    @FXML
    private Text overdueTasks;
    @FXML
    private Text remainingTasks;
    @FXML
    private TabPane mainWindowTabPane;

    // TODO: insert piechart or progress bar.
    public ProductivityPanel(Productivity productivity, TabPane tabPane) {
        super(FXML);
        logger.info("Initialising productivity panel stage");
        this.mainWindowTabPane = tabPane;
        productivityToday.setText(productivity.getTodayStatus());
        runningTimers.setText(productivity.getTimerStatus());
        overdueTasks.setText(productivity.getProductivityForOverdueTasks());
        remainingTasks.setText(productivity.getRemainingTaskStatus());
        System.out.println("");
    }

    /**
     * Swtiches to the productivity panel.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing productivity panel of application.");
        mainWindowTabPane.getSelectionModel().select(2);
    }

    /**
     * Returns true if the productivity panel is currently being shown.
     */
    public boolean isShowing() {
        return mainWindowTabPane.isPressed();
    }
}
