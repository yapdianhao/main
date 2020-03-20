package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.task.Task;

/**
 * Panel containing the list of tasks.
 * Tasks are further sorted into pinned, dueToday, dueThisWeek, dueSomeday
 */
public class SortedTaskListPanel extends UiPart<Region> {
    private static final String FXML = "SortedTaskListPanel.fxml";
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

    public SortedTaskListPanel(
        ObservableList<Task> pinnedTaskList,
        ObservableList<Task> overdueTaskList,
        ObservableList<Task> dueTodayTaskList,
        ObservableList<Task> dueThisWeekTaskList,
        ObservableList<Task> dueSomedayTaskList
    ) {
        super(FXML);

        pinnedTaskListView.setItems(pinnedTaskList);
        pinnedTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());
        pinnedTaskListView.prefHeightProperty().bind(Bindings.size(pinnedTaskList).multiply(PREF_CELL_HEIGHT));

        overdueTaskListView.setItems(overdueTaskList);
        overdueTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());
        overdueTaskListView.prefHeightProperty().bind(Bindings.size(overdueTaskList).multiply(PREF_CELL_HEIGHT));

        dueTodayTaskListView.setItems(dueTodayTaskList);
        dueTodayTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());
        dueTodayTaskListView.prefHeightProperty().bind(Bindings.size(dueTodayTaskList).multiply(PREF_CELL_HEIGHT));

        dueThisWeekTaskListView.setItems(dueThisWeekTaskList);
        dueThisWeekTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());
        dueThisWeekTaskListView.prefHeightProperty()
            .bind(Bindings.size(dueThisWeekTaskList).multiply(PREF_CELL_HEIGHT));

        dueSomedayTaskListView.setItems(dueSomedayTaskList);
        dueSomedayTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());
        dueSomedayTaskListView.prefHeightProperty().bind(Bindings.size(dueSomedayTaskList).multiply(PREF_CELL_HEIGHT));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class SortedTaskListViewCell extends ListCell<Task> {
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
