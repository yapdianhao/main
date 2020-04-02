package seedu.jelphabot.model.summary;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Represents a Summary for the user.
 */
public class Summary {
    private final ObservableList<Task> dueTodayTaskList;
    private final ObservableList<Task> completedTodayTaskList;

    private TasksDueToday tasksDueToday;
    private TasksCompletedToday tasksCompletedToday;

    public Summary(ObservableList<Task> dueTodayTaskList, ObservableList<Task> completedTodayTaskList) {
        requireNonNull(dueTodayTaskList);
        requireNonNull(completedTodayTaskList);
        this.dueTodayTaskList = dueTodayTaskList;
        this.completedTodayTaskList = completedTodayTaskList;
    }

    public TasksDueToday getTasksDueToday() {
        return tasksDueToday;
    }

    public TasksCompletedToday getTasksCompletedToday() {
        return tasksCompletedToday;
    }

    /**
     * populates the respective Summary objects.
     */
    private void populateSummary() {
        this.tasksDueToday = new TasksDueToday(dueTodayTaskList);
        this.tasksCompletedToday = new TasksCompletedToday(completedTodayTaskList);
    }
}

