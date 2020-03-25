package seedu.jelphabot.model.productivity;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_COMPLIMENT;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_CRITICISM;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_ENCOURAGEMENT;

import java.time.Duration;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.SortedTaskList;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskIsIncompletePredicate;

// TODO: update sortedtasklist when task list changes. for now, changes not reflected in respective panes.
/**
 * Represents the user's productivity for the day and highlights overdue tasks if any.
 */
public class TasksCompleted {
    private ObservableList<Task> tasksDueToday;
    private ObservableList<Task> overdueTasks;

    public TasksCompleted(SortedTaskList sortedTaskList) {
        this.tasksDueToday = sortedTaskList.getDueTodayTaskList();
        this.overdueTasks = sortedTaskList.getOverdueTaskList();
    }

    public String getCompletionStatus() {
        int size = tasksDueToday.size();
        int completed = 0;

        for (Task task : tasksDueToday) {
            if (task.getStatus() == Status.COMPLETE) {
                completed++;
            }
        }

        String message = "";

        if (size > 0) {
            double percentage = completed / size;

            if (percentage > 0.7) {
                message = "Great work today!";
            } else if (percentage > 0.4) {
                message = "Not bad but let's do better.";
            } else {
                message = "Wow! It must feel great to have accomplished so little today!";
            }
        }

        return String.format("You completed %d out of %d tasks today! %s", completed, size, message);
    }

    /**
     * Returns the number of incomplete overdue tasks and provides criticism or encouragement depending on the number.
     */
    private String getOverdueStatus() {
        int n = overdueTasks.filtered(new TaskIsIncompletePredicate()).size();
        StringBuilder response = new StringBuilder("There are ");
        response.append(n).append(" overdue tasks that are incomplete.");

        if (n > 3) {
            response.append(" ").append(MESSAGE_CRITICISM);
        } else if (n > 0) {
            response.append(" ").append(MESSAGE_ENCOURAGEMENT);
        } else {
            response.append(" ").append(MESSAGE_COMPLIMENT);
        }

        return response.toString();
    }

    @Override
    public String toString() {
        return getCompletionStatus() + "\n" + getOverdueStatus();
    }
}
