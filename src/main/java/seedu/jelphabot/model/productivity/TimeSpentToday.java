package seedu.jelphabot.model.productivity;

import java.time.Duration;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.SortedTaskList;
import seedu.jelphabot.model.task.Task;

/**
 * Represents the user's productivity for the day
 */
public class TimeSpentToday {
    private ObservableList<Task> tasksDueToday;
    private ObservableList<Task> tasksDueThisWeek;

    public TimeSpentToday(SortedTaskList sortedTaskList) {
        this.tasksDueToday = sortedTaskList.getDueTodayTaskList();
        this.tasksDueThisWeek = sortedTaskList.getDueThisWeekTaskList();
    }

    private Duration getTimeSpent(ObservableList<Task> taskList) {
        Duration result = Duration.ZERO;
        for (Task task : taskList) {
            if (task.getDuration() != null) {
                result = result.plus(task.getDuration());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        Duration durationToday = getTimeSpent(tasksDueToday);
        Duration durationWeek = getTimeSpent(tasksDueThisWeek);

        return String.format("Time spent on tasks due today: %d hours, %d minutes and %d seconds.\n"
                                 + "Time spent on tasks due this week: %d hours, %d minutes and %d seconds.",
            durationToday.toHoursPart(), durationToday.toMinutesPart(), durationToday.toSecondsPart(),
            durationWeek.toHoursPart(), durationWeek.toMinutesPart(), durationWeek.toSecondsPart()
        );
    }
}
