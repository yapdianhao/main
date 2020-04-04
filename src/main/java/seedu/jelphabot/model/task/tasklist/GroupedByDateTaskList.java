package seedu.jelphabot.model.task.tasklist;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.jelphabot.commons.util.DateUtil.getDueSomedayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueTodayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getOverduePredicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.binding.NumberBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.TaskNotFoundException;

/**
 * A container for ObservableList&lt;Task&gt; that splits the TaskList into groups.
 * GroupedByDateTaskList groups Tasks by how close the due date is to the current date.
 * Separation is done over @code{ObservableList} through use of filters.
 * <p>
 */
public class GroupedByDateTaskList implements GroupedTaskList {

    private static final Predicate<Task> isOverdue = getOverduePredicate();
    private static final Predicate<Task> isDueToday = getDueTodayPredicate();
    private static final Predicate<Task> isDueThisWeek = getDueThisWeekPredicate();
    private static final Predicate<Task> isDueSomeday = getDueSomedayPredicate();
    // private static final Predicate<Task> isIncomplete = new TaskIsIncompletePredicate();

    private final ObservableList<SubgroupTaskList> dueDateTaskLists = FXCollections.observableArrayList();
    private final NumberBinding sizeBinding;

    public GroupedByDateTaskList(ObservableList<Task> taskList, PinnedTaskList pinnedTasks) {
        requireAllNonNull(taskList);
        dueDateTaskLists.add(pinnedTasks);
        NumberBinding tempSize = pinnedTasks.sizeBinding();

        SubgroupTaskList overdueTaskList =
            new SubgroupTaskList("Overdue", taskList.filtered(isOverdue), tempSize);
        dueDateTaskLists.add(overdueTaskList);
        tempSize = tempSize.add(overdueTaskList.sizeBinding());

        SubgroupTaskList dueTodayTaskList =
            new SubgroupTaskList("Due Today", taskList.filtered(isDueToday), tempSize);
        dueDateTaskLists.add(dueTodayTaskList);
        tempSize = tempSize.add(dueTodayTaskList.sizeBinding());

        SubgroupTaskList dueThisWeekTaskList =
            new SubgroupTaskList("Due This Week", taskList.filtered(isDueThisWeek), tempSize);
        dueDateTaskLists.add(dueThisWeekTaskList);
        tempSize = tempSize.add(dueThisWeekTaskList.sizeBinding());

        SubgroupTaskList dueSomedayTaskList =
            new SubgroupTaskList("Due Someday", taskList.filtered(isDueSomeday), tempSize);
        dueDateTaskLists.add(dueSomedayTaskList);
        tempSize = tempSize.add(dueSomedayTaskList.sizeBinding());
        this.sizeBinding = tempSize;
    }

    @Override
    public Category getCategory() {
        return Category.DATE;
    }

    @Override
    public ObservableList<SubgroupTaskList> getLists() {
        return dueDateTaskLists;
    }

    @Override
    public int size() {
        return sizeBinding.intValue();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Task> iterator() {
        List<Task> tasks = new ArrayList<>();
        for (SubgroupTaskList sublist : dueDateTaskLists) {
            tasks.addAll(sublist.getList());
        }
        return tasks.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Task task) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Task> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Task get(int id) throws TaskNotFoundException {
        for (SubgroupTaskList sublist : dueDateTaskLists) {
            if (id < sublist.size()) {
                return sublist.get(id);
            } else {
                id -= sublist.size();
            }
        }
        throw new TaskNotFoundException();
    }

    @Override
    public Task get(Index index) {
        return get(index.getZeroBased());
    }
}
