package seedu.jelphabot.model.task;

import javafx.collections.ObservableList;

/**
 * Wrapper class for a ObservableList of pinned Tasks.
 */
public class PinnedTaskList extends SubGroupTaskList {
    public PinnedTaskList(ObservableList<Task> taskList) {
        super("Pinned", taskList);
    }
}
