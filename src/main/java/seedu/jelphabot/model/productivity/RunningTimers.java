package seedu.jelphabot.model.productivity;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_NO_TIMERS;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Represents the timer that is currently running for the user's task.
 */
public class RunningTimers {
    private ObservableList<Task> taskList;

    public RunningTimers(ObservableList<Task> taskList) {
        requireNonNull(taskList);
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Timer is currently running for:\n");
        int n = taskList.size();
        int idx = -1;

        for (int i = 0; i < n; i++) {
            Task task = taskList.get(i);
            if (task.isBeingTimed()) {
                idx = i;
                result.append(String.format("Task %d: %s %s, DateTime: %s\n", (idx + 1), task.getModuleCode(),
                    task.getDescription(), task.getDateTime()));
            }
        }

        if (idx == -1) {
            return MESSAGE_NO_TIMERS;
        } else {
            return result.toString();
        }
    }
}
