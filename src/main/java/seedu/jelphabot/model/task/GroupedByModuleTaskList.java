package seedu.jelphabot.model.task;

import java.util.Iterator;

import javafx.collections.ObservableList;

/**
 * TODO skeleton class
 * A container for ObservableList<Task> that splits the TaskList into groups.
 * GroupedByDateTaskList groups Tasks by their module codes.
 * Separation is done over @code{ObservableList} through use of filters.
 * <p>
 */
public class GroupedByModuleTaskList implements GroupedTaskList {
    public GroupedByModuleTaskList(ObservableList<Task> tasks) {
    }

    @Override
    public Iterator<ObservableList<Task>> iterator() {
        return null;
    }
}
