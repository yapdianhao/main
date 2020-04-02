package seedu.jelphabot.model.summary;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Gives an overview of the tasks that were completed today (within the day).
 */
public class TasksCompletedToday {
    private ObservableList<Task> tasksCompletedToday;

    public TasksCompletedToday(ObservableList<Task> tasksCompletedToday) {
        requireAllNonNull(tasksCompletedToday);
        this.tasksCompletedToday = tasksCompletedToday;
    }

    //TODO: implement method to get the tasks that are completed today
}
