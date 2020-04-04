package seedu.jelphabot.model.task.tasklist;

import javafx.beans.binding.NumberBinding;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Wrapper class for a ObservableList of pinned Tasks.
 */
public class PinnedTaskList extends SubgroupTaskList {
    public PinnedTaskList(ObservableList<Task> taskList, NumberBinding startIndex) {
        super("Pinned", taskList, startIndex);
    }

}
