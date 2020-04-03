package seedu.jelphabot.model.task;

import javafx.beans.binding.NumberBinding;
import javafx.collections.ObservableList;

/**
 * Wrapper class for a ObservableList of pinned Tasks.
 */
public class PinnedTaskList extends SubgroupTaskList {
    public PinnedTaskList(ObservableList<Task> taskList, NumberBinding startIndex) {
        super("Pinned", taskList, startIndex);
    }
}
