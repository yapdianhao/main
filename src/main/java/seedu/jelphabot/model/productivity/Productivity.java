package seedu.jelphabot.model.productivity;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.GroupedByDateTaskList;
import seedu.jelphabot.model.task.Task;

/**
 * Represents the overall productivity of the user.
 */
public class Productivity {
    private final GroupedByDateTaskList groupedByDateTaskList;
    private final ObservableList<Task> taskList;
    private TasksCompleted tasksCompleted;
    private RunningTimer runningTimer;
    private TimeSpentToday timeSpentToday;

    public Productivity(GroupedByDateTaskList groupedByDateTaskList, ObservableList<Task> taskList) {
        this.groupedByDateTaskList = groupedByDateTaskList;
        this.taskList = taskList;
        this.tasksCompleted = new TasksCompleted(sortedTaskList);
        this.runningTimer = new RunningTimer(taskList);
        this.timeSpentToday = new TimeSpentToday(sortedTaskList);

    /**
     * Gets overall productivity of overdue tasks.
     */
    public String getProductivityForOverdueTasks() {
        System.out.println("overdue");
        int n = groupedByDateTaskList.getOverdueTaskList().size();
        StringBuilder response = new StringBuilder("There are ");
        response.append(n).append(" overdue tasks.");

        if (n > 3) {
            response.append(" ").append(MESSAGE_CRITICISM);
        } else if (n > 0) {
            response.append(" ").append(MESSAGE_ENCOURAGEMENT);
        } else {
            response.append(" ").append(MESSAGE_COMPLIMENT);
        }

        return response.toString();
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
