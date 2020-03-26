package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.task.GroupedTaskList;
import seedu.jelphabot.model.task.Task;

/**
 * Panel containing the list of tasks.
 * Tasks are further sorted into pinned, dueToday, dueThisWeek, dueSomeday
 */
public class GroupedTaskListPanel extends UiPart<Region> {
    private static final String FXML = "GroupedTaskListPanel.fxml";
    private static final int PREF_CELL_HEIGHT = 105;

    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @javafx.fxml.FXML
    private ListView<Task> pinnedTaskListView;
    @javafx.fxml.FXML
    private ListView<Task> overdueTaskListView;
    @javafx.fxml.FXML
    private ListView<Task> dueTodayTaskListView;
    @javafx.fxml.FXML
    private ListView<Task> dueThisWeekTaskListView;
    @javafx.fxml.FXML
    private ListView<Task> dueSomedayTaskListView;
    // @javafx.fxml.FXML
    // private ListView<ObservableList<Task>> groupings;

    public GroupedTaskListPanel(
        ObservableList<Task> pinnedTaskList,
        GroupedTaskList groupedTaskList
    ) {
        super(FXML);

        populateListView(pinnedTaskListView, pinnedTaskList);

        for (ObservableList<Task> taskList : groupedTaskList) {
            populateListView(dueSomedayTaskListView, taskList);
            // TODO somehow feed in title and list view name
        }
    }

    private void populateListView(ListView<Task> listView, ObservableList<Task> tasks) {
        // TODO if listview doesnt contain any items hide that category
        listView.setItems(tasks);
        listView.setCellFactory(viewCell -> new GroupedTaskListViewCell());
        listView.prefHeightProperty().bind(Bindings.size(tasks).multiply(PREF_CELL_HEIGHT));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class GroupedTaskListViewCell extends ListCell<Task> {
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
}
