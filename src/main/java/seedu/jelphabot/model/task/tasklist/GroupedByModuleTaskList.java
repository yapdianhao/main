package seedu.jelphabot.model.task.tasklist;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.TaskNotFoundException;

/**
 * A container for ObservableList&lt;Task&gt; that splits the TaskList into groups.
 * GroupedByDateTaskList groups Tasks by their module codes.
 * Separation is done over @code{ObservableList} through use of filters.
 * <p>
 */
public class GroupedByModuleTaskList implements GroupedTaskList {
    private final ObservableList<SubgroupTaskList> moduleCodeTaskLists = FXCollections.observableArrayList();
    private final NumberBinding sizeBinding;

    public GroupedByModuleTaskList(ObservableList<Task> taskList, PinnedTaskList pinnedTasks) {
        requireAllNonNull(taskList);
        moduleCodeTaskLists.add(pinnedTasks);
        NumberBinding tempSize = Bindings.createIntegerBinding(pinnedTasks::size);
        for (ModuleCode code : getUniqueModuleSet(taskList)) {
            SubgroupTaskList moduleCodeSubList =
                new SubgroupTaskList(code.toString(), taskList.filtered(hasModuleCode(code)), tempSize);
            moduleCodeTaskLists.add(moduleCodeSubList);
            moduleCodeSubList.addListener(makeDeleteOnEmptyListener(moduleCodeSubList));
            tempSize = tempSize.add(moduleCodeSubList.sizeBinding());
        }
        this.sizeBinding = tempSize;
    }

    private static HashSet<ModuleCode> getUniqueModuleSet(List<Task> taskList) {
        HashSet<ModuleCode> moduleSet = new HashSet<>();
        for (Task task : taskList) {
            moduleSet.add(task.getModuleCode());
        }
        return moduleSet;
    }

    /**
     * @param moduleCodeSubList the SubgroupTaskList to be removed
     * @return a listener which removes the respective SubgroupTaskList when the inner ObservableList is empty.
     */
    private ListChangeListener<Task> makeDeleteOnEmptyListener(SubgroupTaskList moduleCodeSubList) {
        return change -> {
            if (moduleCodeSubList.isEmpty()) {
                GroupedByModuleTaskList.this.moduleCodeTaskLists.remove(moduleCodeSubList);
            }
        };
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
    public ObservableList<SubgroupTaskList> getList() {
        return moduleCodeTaskLists;
    }

    @Override
    public int size() {
        return sizeBinding.intValue();
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
}

