package seedu.jelphabot.model.summary;
import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Gives an overview of the tasks that are due today.
 */
public class TasksDueToday {
    private ObservableList<Task> tasksDueToday;

    public TasksDueToday(ObservableList<Task> tasksDueToday) {
        requireAllNonNull(tasksDueToday);
        this.tasksDueToday = tasksDueToday;
    }

    //TODO: implement method to get string of the tasks due today
}
