package seedu.jelphabot.model.task;

import static seedu.jelphabot.commons.util.DateUtil.getDueSomedayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueTodayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getOverduePredicate;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.predicates.FilterTaskByDatePredicate;
import seedu.jelphabot.model.task.predicates.TaskIsIncompletePredicate;

/**
 * A container for ObservableList&lt;Task&gt; that splits the TaskList into groups.
 * GroupedByDateTaskList groups Tasks by how close the due date is to the current date.
 * Separation is done over @code{ObservableList} through use of filters.
 * <p>
 */
public class GroupedByDateTaskList implements GroupedTaskList {

    private static final FilterTaskByDatePredicate isOverdue = getOverduePredicate();
    private static final FilterTaskByDatePredicate isDueToday = getDueTodayPredicate();
    private static final FilterTaskByDatePredicate isDueThisWeek = getDueThisWeekPredicate();
    private static final FilterTaskByDatePredicate isDueSomeday = getDueSomedayPredicate();
    private static final Predicate<Task> isIncomplete = new TaskIsIncompletePredicate();

    // TODO move pinnedTaskList out
    private final ObservableList<Task> pinnedTaskList;
    private final ObservableList<Task> overdueTaskList;
    private final ObservableList<Task> dueTodayTaskList;
    private final ObservableList<Task> dueThisWeekTaskList;
    private final ObservableList<Task> dueSomedayTaskList;

    public GroupedByDateTaskList(ObservableList<Task> taskList) {
        overdueTaskList = taskList.filtered(isOverdue).filtered(isIncomplete);
        dueTodayTaskList = taskList.filtered(isDueToday);
        dueThisWeekTaskList = taskList.filtered(isDueThisWeek);
        dueSomedayTaskList = taskList.filtered(isDueSomeday);
        pinnedTaskList = taskList.filtered(null);
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

    @Override
    public Iterator<ObservableList<Task>> iterator() {
        return List.of(overdueTaskList, dueTodayTaskList, dueThisWeekTaskList, dueSomedayTaskList).iterator();
    }
}
