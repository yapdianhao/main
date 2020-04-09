package seedu.jelphabot.model.productivity;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.DateUtil.getDueThisWeekPredicate;
import static seedu.jelphabot.commons.util.DateUtil.getOverduePredicate;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;

// TODO: if time spent is 0, don't show in card.
/**
 * Represents the overall productivity of the user.
 */
public class Productivity {

    private final ObservableList<Task> taskList;
    private TasksCompleted tasksCompleted;
    private RunningTimers runningTimers;
    private TimeSpentToday timeSpentToday;

    // booleans to decide which sub-productivity needs to be re-rendered.
    // TODO: remove. It's useless LOL wth u doing jel
    //  instead, make methods for each.
    private boolean hasNewOrEditedTasks;
    private boolean hasNewTimer;
    private boolean hasChangeInTimeSpent;

    public Productivity(ObservableList<Task> taskList, boolean hasNewOrEditedTasks, boolean hasNewTimer,
        boolean hasChangeInTimeSpent) {
        requireNonNull(taskList);
        this.taskList = taskList;
        this.hasNewOrEditedTasks = hasNewOrEditedTasks;
        this.hasNewTimer = hasNewTimer;
        this.hasChangeInTimeSpent = hasChangeInTimeSpent;

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

        if (tasksCompleted == null || hasNewOrEditedTasks) {
            this.tasksCompleted = new TasksCompleted(tasksDueToday, tasksDueThisWeek,
                taskList.filtered(getOverduePredicate()));
        }

        if (runningTimers == null || hasNewTimer) {
            this.runningTimers = new RunningTimers(taskList);
        }

        if (timeSpentToday == null || hasChangeInTimeSpent) {
            this.timeSpentToday = new TimeSpentToday(tasksDueToday, tasksDueThisWeek);
        }
    }
}
