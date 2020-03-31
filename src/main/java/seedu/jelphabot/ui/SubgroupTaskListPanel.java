package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.task.Task;

/**
 * Panel containing one grouped list of tasks.
 * Tasks are further sorted into pinned, dueToday, dueThisWeek, dueSomeday
 */
public class SubgroupTaskListPanel extends UiPart<Region> {
    private static final String FXML = "SubgroupTaskListPanel.fxml";
    private static final int PREF_CELL_HEIGHT = 100;

    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private TitledPane grouping;

    @FXML
    private ListView<Task> groupingList;

    // TODO display index dynamcally
    int startIndex;

    // TODO do not display if list is empty
    public SubgroupTaskListPanel(String title, ObservableList<Task> tasks, int startIndex) {
        super(FXML);
        this.startIndex = startIndex;

        grouping.setText(title);
        groupingList.setCellFactory(viewCell -> new SubgroupTaskListViewCell());
        groupingList.prefHeightProperty().bind(Bindings.size(tasks).multiply(PREF_CELL_HEIGHT));
        groupingList.setItems(tasks);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code GroupedTaskCard}.
     */
    class SubgroupTaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GroupedTaskCard(task, startIndex++).getRoot());
            }
        }
    }
}

