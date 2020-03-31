package seedu.jelphabot.model.productivity;

import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getOverduePredicate;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;

/**
 * Represents the overall productivity of the user.
 */
public class Productivity {
    private final ObservableList<Task> taskList;
    private TasksCompleted tasksCompleted;
    private RunningTimers runningTimers;
    private TimeSpentToday timeSpentToday;

    public Productivity(ObservableList<Task> taskList) {
        this.taskList = taskList;
        createProductivites();
    }

    public TasksCompleted getTasksCompleted() {
        return tasksCompleted;
    }

    public RunningTimers getRunningTimers() {
        return runningTimers;
    }

    public TimeSpentToday getTimeSpentToday() {
        return timeSpentToday;
    }

    /**
     * Creates the respective productivity objects.
     */
    private void createProductivites() {
        ObservableList<Task> tasksDueToday = taskList.filtered(new TaskDueWithinDayPredicate());
        ObservableList<Task> tasksDueThisWeek = taskList.filtered(getDueThisWeekPredicate());
        this.tasksCompleted = new TasksCompleted(tasksDueToday, tasksDueThisWeek,
            taskList.filtered(getOverduePredicate()));
        this.runningTimers = new RunningTimers(taskList);
        this.timeSpentToday = new TimeSpentToday(tasksDueToday, tasksDueThisWeek);
    }
}
