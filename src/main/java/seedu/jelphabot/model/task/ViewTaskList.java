package seedu.jelphabot.model.task;

import seedu.jelphabot.commons.core.index.Index;

/**
 * Represents a wrapper class for a list of tasks that are displayed in Ui.
 */
public interface ViewTaskList {
    Task get(int id);
    Task get(Index index);
    int size();
}
