package seedu.jelphabot.model.task.tasklist;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;

/**
 * A container for ObservableList&lt;Task&gt; that splits the TaskList into groups.
 * GroupedByDateTaskList groups Tasks by their module codes.
 * Separation is done over @code{ObservableList} through use of filters.
 * <p>
 */
public class GroupedByModuleTaskList extends GroupedTaskList {
    private final ObservableSet<ModuleCode> moduleCodes = FXCollections.observableSet();

    public GroupedByModuleTaskList(ObservableList<Task> tasks, PinnedTaskList pinnedTaskList) {
        super(pinnedTaskList, tasks);
        this.moduleCodes.addAll(getUniqueModuleSet(tasks));
        for (ModuleCode code : moduleCodes) {
            addSublist(code);
        }
        this.moduleCodes.addListener(new ModuleCodeChangeListener());
        this.tasks.addListener(new TaskListChangeListener());
    }

    protected GroupedByModuleTaskList(PinnedTaskList pinnedTaskList) {
        super(pinnedTaskList, FXCollections.observableArrayList());
        this.moduleCodes.addListener(new ModuleCodeChangeListener());
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

    /**
     * Adds a new sublist with the given ModuleCode as header.
     * The sublist must not already exist in the list.
     *
     * @param moduleCode the value of the sublist header.
     */
    private void addSublist(ModuleCode moduleCode) {
        subgroupTaskLists.add(
            new SubgroupTaskList(moduleCode.toString(), this.tasks.filtered(hasModuleCode(moduleCode)),
                subsequentElementStartIndex()
            ));
    }

    /**
     * Returns true if the list contains a sublist with the same module code as the given argument.
     */
    public boolean containsSublist(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return subgroupTaskLists.stream().anyMatch(sublist -> sublist.getGroupName().equals(moduleCode.toString()));
    }

    /* === Methods used for testing. Application classes should not call these methods as Tasks are intended
    to be modified through UniqueTaskList. === */
    @Override
    protected void setTasks(List<Task> taskList) {
        this.tasks.setAll(taskList);

        moduleCodes.clear();
        moduleCodes.addAll(getUniqueModuleSet(tasks));
        for (ModuleCode code : moduleCodes) {
            addSublist(code);
        }
    }
    // === End of Methods used for testing ===

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
                subgroupTaskLists.removeIf(sublist -> sublist.getGroupName().equals(removedCode.toString()));
            }
        }
    }
}
