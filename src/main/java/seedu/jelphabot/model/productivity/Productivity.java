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
    private StatusToday statusToday;
    private RunningTimer runningTimer;

    public Productivity(SortedTaskList sortedTaskList, ObservableList<Task> taskList) {
        this.sortedTaskList = sortedTaskList;
        this.taskList = taskList;
        this.statusToday = new StatusToday(sortedTaskList);
        this.runningTimer = new RunningTimer(taskList);

    }

    public StatusToday getStatusToday() {
        return statusToday;
    }

    public RunningTimer getRunningTimer() {
        return runningTimer;
    }
}
