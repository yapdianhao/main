package seedu.jelphabot.model.task;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

/**
 * A container for ObservableList&lt;Task&gt; that splits the TaskList into groups.
 * GroupedByDateTaskList groups Tasks by their module codes.
 * Separation is done over @code{ObservableList} through use of filters.
 * <p>
 */
public class GroupedByModuleTaskList implements GroupedTaskList {
    private final List<ObservableList<Task>> moduleCodeTaskList = new  ArrayList<>();

    public GroupedByModuleTaskList(ObservableList<Task> taskList) {
        requireAllNonNull(taskList);
        HashSet<ModuleCode> moduleCodes = new HashSet<>();
        for (Task task : taskList) {
            moduleCodes.add(task.getModuleCode());
        }
        for (ModuleCode code : moduleCodes) {
            moduleCodeTaskList.add(taskList.filtered(hasModuleCode(code)));
        }
    }

    private Predicate<Task> hasModuleCode(ModuleCode moduleCode) {
        return new Predicate<Task>() {
            @Override
            public boolean test(Task task) {
                return task.getModuleCode().equals(moduleCode);
            }
        };
    }

    @Override
    public Iterator<ObservableList<Task>> iterator() {
        return moduleCodeTaskList.iterator();
    }

    @Override
    public Iterator<String> getGroupNames() {
        Iterator<ObservableList<Task>> it = moduleCodeTaskList.iterator();
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                return it.next().toString();
            }
        };
    }
}
