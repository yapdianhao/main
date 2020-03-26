package seedu.jelphabot.model.productivity;

import java.time.Duration;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

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

    private Duration getTimeSpent(ObservableList<Task> taskList) {
        Duration result = Duration.ZERO;
        for (Task task : taskList) {
            System.out.println("duration: " + task.getDuration());
            // TODO:
            // if (task.isBeingTimed()) {
            //     System.out.println("is being timed");
            //     result = result.plus(Duration.between(task.getStartTime(), LocalDateTime.now()));
            // }
            if (task.getDuration() != Duration.ZERO) {
                result = result.plus(task.getDuration());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        Duration durationToday = getTimeSpent(tasksDueToday);
        Duration durationWeek = durationToday.plus(getTimeSpent(tasksDueThisWeek));

        return String.format("Due today: %d hours, %d minutes and %d seconds.\n"
                                 + "\nDue this week: %d hours, %d minutes and %d seconds.",
            durationToday.toHoursPart(), durationToday.toMinutesPart(), durationToday.toSecondsPart(),
            durationWeek.toHoursPart(), durationWeek.toMinutesPart(), durationWeek.toSecondsPart()
        );
    }
}
