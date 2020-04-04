package seedu.jelphabot.model.task.tasklist;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.jelphabot.commons.util.DateUtil.getDueSomedayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueTodayPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getOverduePredicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.DuplicateTaskException;
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

    private final PinnedTaskList pinnedTaskList;
    /**
     * underlying task list
     */
    private final ObservableList<Task> tasks;

    private final ObservableList<SubgroupTaskList> dueDateTaskLists = FXCollections.observableArrayList();

    public GroupedByDateTaskList(ObservableList<Task> tasks, PinnedTaskList pinnedTasks) {
        requireAllNonNull(tasks);
        requireNonNull(pinnedTaskList);
        this.pinnedTaskList = pinnedTaskList;
        this.tasks = tasks;

        dueDateTaskLists.add(pinnedTasks);
        IntegerBinding tempSize = pinnedTasks.sizeBinding();

        SubgroupTaskList overdueTaskList =
            new SubgroupTaskList("Overdue", tasks.filtered(isOverdue), tempSize);
        dueDateTaskLists.add(overdueTaskList);
        tempSize = (IntegerBinding) tempSize.add(overdueTaskList.sizeBinding());

        SubgroupTaskList dueTodayTaskList =
            new SubgroupTaskList("Due Today", tasks.filtered(isDueToday), tempSize);
        dueDateTaskLists.add(dueTodayTaskList);
        tempSize = (IntegerBinding) tempSize.add(dueTodayTaskList.sizeBinding());

        SubgroupTaskList dueThisWeekTaskList =
            new SubgroupTaskList("Due This Week", tasks.filtered(isDueThisWeek), tempSize);
        dueDateTaskLists.add(dueThisWeekTaskList);
        tempSize = (IntegerBinding) tempSize.add(dueThisWeekTaskList.sizeBinding());

        SubgroupTaskList dueSomedayTaskList =
            new SubgroupTaskList("Due Someday", tasks.filtered(isDueSomeday), tempSize);
        dueDateTaskLists.add(dueSomedayTaskList);
        tempSize = (IntegerBinding) tempSize.add(dueSomedayTaskList.sizeBinding());
        this.sizeBinding = tempSize;
    }


        /* === Methods used for testing. Application classes should not call these methods as Tasks are intended
        to be modified through UniqueTaskList. === */

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    protected void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        tasks.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    protected void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = tasks.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        tasks.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    protected void remove(Task target) {
        requireNonNull(target);
        if (!contains(target)) {
            throw new TaskNotFoundException();
        }
        tasks.remove(target);
    }

    /**
     * Removes all tasks from the list.
     */
    protected void clear() {
        this.pinnedTaskList.clear();
        this.tasks.clear();
        this.dueDateTaskLists.clear();
    }

    /**
     * Sets the inner dueDateTaskLists according to the contents of taskList.
     * Since taskList is retrieved from UniqueTaskList, it is assumed that there are no duplicate tasks.
     *
     * @param taskList the updated list of tasks.
     */
    protected void setTasks(List<Task> taskList) {
        this.tasks.setAll(taskList);

        moduleCodes.clear();
        moduleCodes.addAll(getUniqueModuleSet(tasks));
        for (ModuleCode code : moduleCodes) {
            addSublist(code);
        }
    }

    protected void setTasks(GroupedByModuleTaskList replacement) {
        requireNonNull(replacement);
        dueDateTaskLists.setAll(replacement.getSublists());
        setTasks(replacement.tasks);
    }
    // === End of Methods used for testing ===

    @Override
    public Category getCategory() {
        return Category.DATE;
    }

    @Override
    public ObservableList<SubgroupTaskList> getSublists() {
        return dueDateTaskLists;
    }

    @Override
    public int size() {
        return sizeBinding.intValue();
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
