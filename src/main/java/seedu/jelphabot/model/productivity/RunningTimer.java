package seedu.jelphabot.model.productivity;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_CURRENT_TIMERS;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_NO_TIMERS;

import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Represents the timer that is currently running for the user's task.
 */
public class RunningTimer {
    private ObservableList<Task> taskList;

    public RunningTimer(ObservableList<Task> taskList) {
        requireNonNull(taskList);
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        Optional<Task> taskBeingTimed = Optional.empty();
        int n = taskList.size();
        int idx = -1;

        for (int i = 0; i < n; i++) {
            Task task = taskList.get(i);
            if (task.isBeingTimed()) {
                idx = i;
                taskBeingTimed = Optional.of(task);
                break;
            }
        }

        if (taskBeingTimed.isEmpty()) {
            return MESSAGE_NO_TIMERS;
        } else {
            Task tmp = taskBeingTimed.get();
            return String.format(MESSAGE_CURRENT_TIMERS, (idx + 1), tmp.getModuleCode(), tmp.getDescription(),
                tmp.getDateTime());
        }
    }
}
