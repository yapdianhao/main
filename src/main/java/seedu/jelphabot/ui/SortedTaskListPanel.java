package seedu.jelphabot.ui;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;
import seedu.jelphabot.model.task.predicates.TaskDueWithinWeekPredicate;

/**
 * Panel containing the list of tasks.
 * Tasks are further sorted into pinned, dueToday, dueThisWeek, dueSomeday
 */
public class SortedTaskListPanel extends TaskListPanel {
    private static final String FXML = "SortedTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @javafx.fxml.FXML
    private ListView<Task> pinnedTaskListView;
    @javafx.fxml.FXML
    private ListView<Task> dueTodayTaskListView;
    @javafx.fxml.FXML
    private ListView<Task> dueThisWeekTaskListView;
    @javafx.fxml.FXML
    private ListView<Task> dueSomedayTaskListView;

    public SortedTaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        pinnedTaskListView.setItems(taskList);
        pinnedTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());

        Predicate<Task> isDueToday = new TaskDueWithinDayPredicate();
        Predicate<Task> isDueThisWeek = new TaskDueWithinWeekPredicate();
        // TODO feed in a few tasklists instead of doing it here, split in ModelManager and feed in through MainWindow
        dueTodayTaskListView.setItems(taskList.filtered(isDueToday));
        dueTodayTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());

        dueThisWeekTaskListView.setItems(taskList.filtered(isDueThisWeek.and(isDueToday.negate())));
        dueThisWeekTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());

        dueSomedayTaskListView.setItems(taskList.filtered(isDueToday.and(isDueThisWeek.negate())));
        dueSomedayTaskListView.setCellFactory(listView -> new SortedTaskListPanel.SortedTaskListViewCell());
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
