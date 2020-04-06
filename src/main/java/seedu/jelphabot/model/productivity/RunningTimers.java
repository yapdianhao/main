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
        boolean hasTimers = false;

        for (Task t : taskList) {
            if (t.isBeingTimed()) {
                hasTimers = true;
                result.append(String.format("Task: %s %s, Deadline: %s\n", t.getModuleCode(),
                    t.getDescription(), t.getDateTime()));
            }
        }

        if (!hasTimers) {
            return MESSAGE_NO_TIMERS;
        } else {
            return result.toString();
        }
    }
}
