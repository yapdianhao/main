package seedu.jelphabot.model.productivity;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_COMPLIMENT;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_CRITICISM;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_ENCOURAGEMENT;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.SortedTaskList;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskIsIncompletePredicate;

// TODO: update sortedtasklist when task list changes. for now, changes not reflected in respective panes.
/**
 * Highlights overdue tasks if any, and mentions number of tasks completed.
 */
public class TasksCompleted {
    private ObservableList<Task> tasksDueThisWeek;
    private ObservableList<Task> overdueTasks;

    public TasksCompleted(SortedTaskList sortedTaskList) {
        this.tasksDueThisWeek = sortedTaskList.getDueThisWeekTaskList();
        this.overdueTasks = sortedTaskList.getOverdueTaskList();
    }

    public String getCompletionStatus() {
        int size = tasksDueThisWeek.size();
        int completed = 0;

        for (Task task : tasksDueThisWeek) {
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

        return String.format("You completed %d out of %d tasks that are due this week!\n%s", completed, size, message);
    }

    /**
     * Returns the number of incomplete overdue tasks and provides criticism or encouragement depending on the number.
     */
    private String getOverdueStatus() {
        int n = overdueTasks.filtered(new TaskIsIncompletePredicate()).size();
        StringBuilder response = new StringBuilder("There ");

        if (n > 1) {
            response.append("are ");
        } else {
            response.append("is ");
        }
        response.append(n).append(" overdue tasks that are incomplete.");

        if (n > 3) {
            response.append("\n").append(MESSAGE_CRITICISM);
        } else if (n > 0) {
            response.append("\n").append(MESSAGE_ENCOURAGEMENT);
        } else {
            response.append("\n").append(MESSAGE_COMPLIMENT);
        }

        return response.toString();
    }

    @Override
    public String toString() {
        return getCompletionStatus() + "\n\n" + getOverdueStatus();
    }
}
