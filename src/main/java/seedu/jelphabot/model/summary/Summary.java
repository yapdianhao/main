package seedu.jelphabot.model.summary;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskCompletedWithinDayPredicate;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;

/**
 * Represents a Summary for the user.
 */
public class Summary {
    private final ObservableList<Task> taskList;

    private TasksDueToday tasksDueToday;
    private TasksCompletedToday tasksCompletedToday;

    public Summary(ObservableList<Task> taskList) {
        requireNonNull(taskList);
        this.taskList = taskList;
        populateSummary();
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
        ObservableList<Task> dueTodayTaskList = taskList.filtered(new TaskDueWithinDayPredicate());
        ObservableList<Task> completedTodayTaskList = taskList.filtered(new TaskCompletedWithinDayPredicate());
        this.tasksDueToday = new TasksDueToday(dueTodayTaskList);
        this.tasksCompletedToday = new TasksCompletedToday(completedTodayTaskList);
    }
}

