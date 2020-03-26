package seedu.jelphabot.model.productivity;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_COMPLIMENT;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_CRITICISM;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_ENCOURAGEMENT;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.predicates.TaskIsIncompletePredicate;

/**
 * Highlights overdue tasks if any, and mentions number of tasks completed.
 */
public class TasksCompleted {
    private ObservableList<Task> tasksDueToday;
    private ObservableList<Task> tasksDueThisWeek;
    private ObservableList<Task> overdueTasks;
    private double percentage;

    public TasksCompleted(ObservableList<Task> tasksDueToday, ObservableList<Task> tasksDueThisWeek,
        ObservableList<Task> overdueTasks) {
        this.tasksDueToday = tasksDueToday;
        this.tasksDueThisWeek = tasksDueThisWeek;
        this.overdueTasks = overdueTasks;
        this.percentage = 0;
    }

    public String getCompletionStatus() {
        double size = tasksDueThisWeek.size() + tasksDueToday.size();
        double completed = 0;

        for (Task task : tasksDueThisWeek) {
            if (task.getStatus() == Status.COMPLETE) {
                completed++;
            }
        }

        for (Task task : tasksDueToday) {
            if (task.getStatus() == Status.COMPLETE) {
                completed++;
            }
        }

        String message = "";

        if (size > 0) {
            double percentage = completed / size;
            this.percentage = percentage;

            if (percentage > 0.7) {
                message = "Great work today!";
            } else if (percentage > 0.4) {
                message = "Not bad but let's do better.";
            } else {
                message = "Wow! It must feel great to have accomplished so little today!";
            }
        }

        return String.format("You completed %.0f out of %.0f tasks that are due this week!\n%s", completed, size, message);
    }

    /**
     * Returns the number of incomplete overdue tasks and provides criticism or encouragement depending on the number.
     */
    private String getOverdueStatus() {
        int n = overdueTasks.filtered(new TaskIsIncompletePredicate()).size();
        StringBuilder response = new StringBuilder("There ");

        if (n > 1) {
            response.append("are ").append(n).append(" overdue tasks that are incomplete.");
        } else {
            response.append("is ").append(n).append(" overdue task that is incomplete.");
        }

        if (n > 3) {
            response.append("\n").append(MESSAGE_CRITICISM);
        } else if (n > 0) {
            response.append("\n").append(MESSAGE_ENCOURAGEMENT);
        } else {
            response.append("\n").append(MESSAGE_COMPLIMENT);
        }

        return response.toString();
    }

    public double getPercentage() {
        return this.percentage;
    }

    @Override
    public String toString() {
        return getCompletionStatus() + "\n\n" + getOverdueStatus();
    }
}
