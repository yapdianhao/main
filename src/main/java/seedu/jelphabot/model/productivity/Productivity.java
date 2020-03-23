package seedu.jelphabot.model.productivity;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_COMPLIMENT;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_CRITICISM;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_CURRENT_TIMERS;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_ENCOURAGEMENT;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_NO_TIMERS;

import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.SortedTaskList;
import seedu.jelphabot.model.task.Task;


/**
 * Represents the overall productivity of the user in a day.
 */
public class Productivity {
    private final SortedTaskList sortedTaskList;
    private final ObservableList<Task> taskList;

    public Productivity(SortedTaskList sortedTaskList, ObservableList<Task> taskList) {
        this.sortedTaskList = sortedTaskList;
        this.taskList = taskList;
    }

    /**
     * Gets overall productivity of overdue tasks.
     */
    public String getProductivityForOverdueTasks() {
        System.out.println("overdue");
        int n = sortedTaskList.getOverdueTaskList().size();
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

    /**
     * Gets the status of running timers if any.
     */
    public String getTimerStatus() {
        System.out.println("timer");
        Optional<Task> taskBeingTimed = Optional.empty();

        for (Task task : taskList) {
            if (task.isBeingTimed()) {
                taskBeingTimed = Optional.of(task);
            }
        }

        if (taskBeingTimed.isEmpty()) {
            return MESSAGE_NO_TIMERS;
        } else {
            Task tmp = taskBeingTimed.get();
            return String.format(MESSAGE_CURRENT_TIMERS, tmp.getModuleCode(), tmp.getDescription(), tmp.getDateTime());
        }
    }

    public String getTodayStatus() {
        // TODO: list out time taken per task + overall productivity that day.
        //  i.e. how many hours spent on tasks so far -> need some way to know what time it is and calculated fast.
        //  List total time taken for all tasks at the top. e.g. 'You spent ___ hours and ___ minutes on work today!"
        //  List avg time spent per task (divide by incomplete tasks only)
        return "placeholder1";
    }

    public String getRemainingTaskStatus() {
        // TODO: show total remaining tasks and show only the task with earliest deadline in full
        return "placeholder2";
    }
}
