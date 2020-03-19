package seedu.jelphabot.model.task;

import static seedu.jelphabot.commons.util.DateUtil.getDueSomedayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueTodayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getOverduePredicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.predicates.FilterTaskByDatePredicate;

/**
 * A wrapper for UniqueTaskList that separates UniqueTaskList by categories.
 * Separation is done over @code{UniqueTaskList} through use of filters.
 * <p>
 * Supports a minimal set of list operations.
 */
public class SortedTaskList {

    private static final FilterTaskByDatePredicate isOverdue = getOverduePredicate();
    private static final FilterTaskByDatePredicate isDueToday = getDueTodayPredicate();
    private static final FilterTaskByDatePredicate isDueThisWeek = getDueThisWeekPredicate();
    private static final FilterTaskByDatePredicate isDueSomeday = getDueSomedayPredicate();

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
