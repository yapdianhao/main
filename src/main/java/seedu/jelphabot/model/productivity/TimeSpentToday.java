package seedu.jelphabot.model.productivity;

import java.time.Duration;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.TimeSpent;

/**
 * Represents the user's productivity for the day
 */
public class TimeSpentToday {
    private ObservableList<Task> tasksDueToday;
    private ObservableList<Task> tasksDueThisWeek;

    public TimeSpentToday(ObservableList<Task> tasksDueToday, ObservableList<Task> tasksDueThisWeek) {
        this.tasksDueToday = tasksDueToday;
        this.tasksDueThisWeek = tasksDueThisWeek;
    }

    private TimeSpent getTimeSpent(ObservableList<Task> taskList) {
        TimeSpent result = new TimeSpent(Duration.ZERO);
        for (Task task : taskList) {
            result.addTime(task.getTimeSpent());
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("Due today: %s.\n\nDue in next 7 days: %s.", getTimeSpent(tasksDueToday),
            getTimeSpent(tasksDueThisWeek));
    }
}
