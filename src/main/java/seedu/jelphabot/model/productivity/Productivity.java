package seedu.jelphabot.model.productivity;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.SortedTaskList;
import seedu.jelphabot.model.task.Task;

/**
 * Represents the overall productivity of the user.
 */
public class Productivity {
    private final SortedTaskList sortedTaskList;
    private final ObservableList<Task> taskList;
    private TasksCompleted tasksCompleted;
    private RunningTimer runningTimer;
    private TimeSpentToday timeSpentToday;

    public Productivity(SortedTaskList sortedTaskList, ObservableList<Task> taskList) {
        this.sortedTaskList = sortedTaskList;
        this.taskList = taskList;
        this.tasksCompleted = new TasksCompleted(sortedTaskList);
        this.runningTimer = new RunningTimer(taskList);
        this.timeSpentToday = new TimeSpentToday(sortedTaskList);

    }

    public TasksCompleted getTasksCompleted() {
        return tasksCompleted;
    }

    public RunningTimer getRunningTimer() {
        return runningTimer;
    }

    public TimeSpentToday getTimeSpentToday() {
        return timeSpentToday;
    }
}
