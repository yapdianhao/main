package seedu.jelphabot.model.task;

import static seedu.jelphabot.commons.util.DateUtil.getDueSomedayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueTodayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getOverduePredicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.index.Index;
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

    private final List<SubGroupTaskList> dueDateTaskLists = new ArrayList<>();
    private final NumberBinding sizeBinding;

    public GroupedByDateTaskList(ObservableList<Task> taskList, PinnedTaskList pinnedTasks) {
        dueDateTaskLists.add(pinnedTasks);
        dueDateTaskLists.add(new SubGroupTaskList("Overdue", taskList.filtered(isOverdue).filtered(isIncomplete)));
        dueDateTaskLists.add(new SubGroupTaskList("Due Today", taskList.filtered(isDueToday)));
        dueDateTaskLists.add(new SubGroupTaskList("isDueThisWeek", taskList.filtered(isDueThisWeek)));
        dueDateTaskLists.add(new SubGroupTaskList("isDueSomeday", taskList.filtered(isDueSomeday)));

        NumberBinding tempSize = Bindings.createIntegerBinding(() -> 0);
        for (SubGroupTaskList subList : dueDateTaskLists) {
            tempSize = tempSize.add(Bindings.size(subList.getList()));
        }
        sizeBinding = tempSize;
    }

    @Override
    public Category getCategory() {
        return Category.DATE;
    }

    @Override
    public int size() {
        return sizeBinding.intValue();
    }

    @Override
    public Iterator<SubGroupTaskList> iterator() {
        return dueDateTaskLists.iterator();
    }

    @Override
    public Task get(int id) {
        assert id < size();
        for (SubGroupTaskList sublist : dueDateTaskLists) {
            if (id < sublist.size()) {
                return sublist.get(id);
            } else {
                id -= sublist.size();
            }
        }
        return null;
    }

    @Override
    public Task get(Index index) {
        return get(index.getZeroBased());
    }
}
