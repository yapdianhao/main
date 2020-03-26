package seedu.jelphabot.model.productivity;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskDueBeforeDatePredicate;

import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getDueTodayPredicate;

/**
 * Represents the overall productivity of the user.
 */
public class Productivity {
    private final ObservableList<Task> taskList;
    private TasksCompleted tasksCompleted;
    private RunningTimer runningTimer;
    private TimeSpentToday timeSpentToday;

    public Productivity(ObservableList<Task> taskList) {
        this.taskList = taskList;
        createProductivites();
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

    private void createProductivites() {
        ObservableList<Task> tasksDueThisWeek = taskList.filtered(getDueThisWeekPredicate());
        this.tasksCompleted = new TasksCompleted(tasksDueThisWeek, taskList.filtered(new TaskDueBeforeDatePredicate()));
        this.runningTimer = new RunningTimer(taskList);
        this.timeSpentToday = new TimeSpentToday(taskList.filtered(getDueTodayPredicate()), tasksDueThisWeek);
    }
}
