package seedu.jelphabot.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.predicates.FilterTaskByDatePredicate;
import seedu.jelphabot.model.task.predicates.TaskDueAfterDatePredicate;
import seedu.jelphabot.model.task.predicates.TaskDueBeforeDatePredicate;

/**
 * A wrapper for UniqueTaskList that separates UniqueTaskList by categories.
 * Separation is done over @code{UniqueTaskList} through use of filters.
 * <p>
 * Supports a minimal set of list operations.
 */
public class SortedTaskList {

    private static final FilterTaskByDatePredicate isOverdue = new TaskDueBeforeDatePredicate();
    private static final FilterTaskByDatePredicate isDueToday = new TaskDueAfterDatePredicate();
    private static final FilterTaskByDatePredicate isDueThisWeek = new TaskDueAfterDatePredicate();
    private static final FilterTaskByDatePredicate isDueSomeday = new TaskDueAfterDatePredicate();

    private final ObservableList<Task> pinnedTaskList;
    private final ObservableList<Task> overdueTaskList;
    private final ObservableList<Task> dueTodayTaskList;
    private final ObservableList<Task> dueThisWeekTaskList;
    private final ObservableList<Task> dueSomedayTaskList;

    public SortedTaskList(ObservableList<Task> taskList) {
        pinnedTaskList = FXCollections.unmodifiableObservableList(taskList.filtered(null));
        overdueTaskList = FXCollections.unmodifiableObservableList(taskList.filtered(isOverdue));
        dueTodayTaskList = FXCollections.unmodifiableObservableList(taskList.filtered(isDueToday));
        dueThisWeekTaskList = FXCollections.unmodifiableObservableList(taskList.filtered(isDueThisWeek));
        dueSomedayTaskList = FXCollections.unmodifiableObservableList(taskList.filtered(isDueSomeday));
    }

    public ObservableList<Task> getPinnedTaskList() {
        return pinnedTaskList;
    }

    public ObservableList<Task> getOverdueTaskList() {
        return overdueTaskList;
    }

    public ObservableList<Task> getDueTodayTaskList() {
        return dueTodayTaskList;
    }

    public ObservableList<Task> getDueThisWeekTaskList() {
        return dueThisWeekTaskList;
    }

    public ObservableList<Task> getDueSomedayTaskList() {
        return dueSomedayTaskList;
    }
}
