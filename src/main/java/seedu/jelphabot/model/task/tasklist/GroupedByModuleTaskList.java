package seedu.jelphabot.model.task.tasklist;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.DuplicateTaskException;
import seedu.jelphabot.model.task.exceptions.TaskNotFoundException;

/**
 * A container for ObservableList&lt;Task&gt; that splits the TaskList into groups.
 * GroupedByDateTaskList groups Tasks by their module codes.
 * Separation is done over @code{ObservableList} through use of filters.
 * <p>
 */
public class GroupedByModuleTaskList implements GroupedTaskList {
    private final PinnedTaskList pinnedTaskList;
    /**
     * underlying task list
     */
    private final ObservableList<Task> tasks;

    private final ObservableSet<ModuleCode> moduleCodes = FXCollections.observableSet();
    private final ObservableList<SubgroupTaskList> moduleCodeTaskLists = FXCollections.observableArrayList();

    public GroupedByModuleTaskList(ObservableList<Task> tasks, PinnedTaskList pinnedTaskList) {
        requireAllNonNull(tasks);
        this.pinnedTaskList = pinnedTaskList;
        this.tasks = tasks;
        this.moduleCodes.addAll(getUniqueModuleSet(tasks));
        for (ModuleCode code : moduleCodes) {
            addSublist(code);
        }
        this.moduleCodes.addListener(new ModuleCodeChangeListener());
        this.tasks.addListener(new TaskListChangeListener());
    }

    public GroupedByModuleTaskList(PinnedTaskList pinnedTaskList) {
        this.pinnedTaskList = pinnedTaskList;
        this.moduleCodes.addListener(new ModuleCodeChangeListener());
        this.tasks = FXCollections.observableArrayList();
        this.tasks.addListener(new TaskListChangeListener());
    }

    private static Set<ModuleCode> getUniqueModuleSet(List<Task> taskList) {
        Set<ModuleCode> moduleSet = new HashSet<>();
        for (Task task : taskList) {
            moduleSet.add(task.getModuleCode());
        }
        return moduleSet;
    }

    /**
     * Sets the inner moduleCodeTaskLists according to the contents of taskList.
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
        moduleCodeTaskLists.setAll(replacement.getLists());
        setTasks(replacement.tasks);
    }

    /**
     * @param moduleCode The ModuleCode to be tested
     * @return a predicate which tests Tasks for the parameter module code.
     */
    private Predicate<Task> hasModuleCode(ModuleCode moduleCode) {
        return task -> task.getModuleCode().equals(moduleCode);
    }

    @Override
    public Category getCategory() {
        return Category.MODULE;
    }

    @Override
    public ObservableList<SubgroupTaskList> getLists() {
        return moduleCodeTaskLists;
    }

    @Override
    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    protected void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        ModuleCode moduleCode = toAdd.getModuleCode();
        moduleCodes.add(moduleCode);
        tasks.add(toAdd);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    private void addSublist(ModuleCode moduleCode) {
        IntegerBinding latestBinding = moduleCodeTaskLists.isEmpty()
                                           ? Bindings.createIntegerBinding(() -> 0)
                                           : moduleCodeTaskLists.get(moduleCodeTaskLists.size() - 1)
                                                 .subsequentElementStartIndex();
        moduleCodeTaskLists.add(
            new SubgroupTaskList(moduleCode.toString(), this.tasks.filtered(hasModuleCode(moduleCode)),
                latestBinding
            ));
    }

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return tasks.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Returns true if the list contains a sublist with the same module code as the given argument.
     */
    public boolean containsSublist(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return moduleCodeTaskLists.stream().anyMatch(sublist -> sublist.getGroupName().equals(moduleCode.toString()));
    }

    /**
     * Removes all tasks from the list.
     */
    protected void clear() {
        this.pinnedTaskList.clear();
        this.tasks.clear();
        this.moduleCodeTaskLists.clear();
    }

    @Override
    public Iterator<Task> iterator() {
        List<Task> tasks = new ArrayList<>();
        for (SubgroupTaskList sublist : moduleCodeTaskLists) {
            tasks.addAll(sublist.getList());
        }
        return tasks.iterator();
    }

    @Override
    public Task get(int id) throws TaskNotFoundException {
        assert id < size();
        for (SubgroupTaskList sublist : moduleCodeTaskLists) {
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof GroupedByModuleTaskList // instanceof handles nulls
                           && tasks.equals(((GroupedByModuleTaskList) other).tasks));
    }

    /**
     * A Listener that handles the updating of moduleCodes when the underlying list of Tasks is updated.
     */
    private class TaskListChangeListener implements ListChangeListener<Task> {
        @Override
        public void onChanged(Change<? extends Task> change) {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Task task : change.getAddedSubList()) {
                        moduleCodes.add(task.getModuleCode());
                    }
                }
                if (change.wasRemoved()) {
                    for (Task task : change.getRemoved()) {
                        if (tasks.stream().noneMatch(toCheck -> toCheck.getModuleCode() == task.getModuleCode())) {
                            moduleCodes.remove(task.getModuleCode());
                        }
                    }
                }
            }
        }
    }

    /**
     * A Listener that handles the updating of SubgroupTaskList when the underlying set of ModuleCodes is updated.
     */
    private class ModuleCodeChangeListener
        implements SetChangeListener<ModuleCode> {
        @Override
        public void onChanged(Change<? extends ModuleCode> change) {
            if (change.wasAdded()) {
                ModuleCode addedCode = change.getElementAdded();
                if (!containsSublist(addedCode)) {
                    addSublist(addedCode);
                }
            }
            if (change.wasRemoved()) {
                ModuleCode removedCode = change.getElementRemoved();
                moduleCodeTaskLists.removeIf(sublist -> sublist.getGroupName().equals(removedCode.toString()));
            }
        }
    }
}
