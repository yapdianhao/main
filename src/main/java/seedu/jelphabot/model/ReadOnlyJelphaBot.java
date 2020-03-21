package seedu.jelphabot.model;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.reminder.Reminder;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyJelphaBot {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    ObservableList<Reminder> getReminderList();

}
