package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.task.Task;

/**
 * The Summary Panel. Provides the basic application layout of the summary of tasks due today,
 */
public class SummaryPanel extends UiPart<Region> {
    // TODO: make summary panel look like a summary panel (text chunk vs list of tasks)
    // TODO: Shift summary tab to the first tab
    private static final String FXML = "SummaryPanel.fxml";

    private static final int PREF_CELL_HEIGHT = 210;
    private final Logger logger = LogsCenter.getLogger(SummaryPanel.class);

    @FXML
    private TabPane mainWindowTabPane;

    @FXML
    private ListView<Task> dueTodayTaskListView;

    @FXML
    private ListView<Task> completedTodayTaskListView;

    public SummaryPanel(ObservableList<Task> dueTodayTaskList,
        ObservableList<Task> completedTodayTaskList, TabPane tabPane) {
        super(FXML);
        logger.info("Initialising summary panel stage");
        this.mainWindowTabPane = tabPane;
        dueTodayTaskListView.setItems(dueTodayTaskList);
        dueTodayTaskListView.setCellFactory(ListView -> new SummaryTaskListViewCell());
        dueTodayTaskListView.prefHeightProperty().bind(Bindings.size(dueTodayTaskList).multiply(PREF_CELL_HEIGHT));

        completedTodayTaskListView.setItems(completedTodayTaskList);
        completedTodayTaskListView.setCellFactory(ListView -> new SummaryTaskListViewCell());
        completedTodayTaskListView.prefHeightProperty()
            .bind(Bindings.size(completedTodayTaskList).multiply(PREF_CELL_HEIGHT));
    }

    /**
     * Swtiches to the summary panel
     */
    public void show() {
        logger.info("Showing summary panel of application");
        mainWindowTabPane.getSelectionModel().select(3);
    }

    /**
     * Returns true if the summary panel is currently being shown
     * @return
     */
    public boolean isShowing() {
        return mainWindowTabPane.isPressed();
    }
}

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
 */
class SummaryTaskListViewCell extends ListCell<Task> {
    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
        }
    }
}
