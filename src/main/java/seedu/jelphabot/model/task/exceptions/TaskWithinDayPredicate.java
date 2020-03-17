package seedu.jelphabot.model.task.exceptions;

import java.util.Date;
import java.util.function.Predicate;

import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.TaskIncompletePredicate;

/**
 * Tests that a {@code Task}'s {@code DateTime} falls within the given Date.
 */
public class TaskWithinDayPredicate implements Predicate<Task> {
    // private final Calendar calendar;
    private final Date date;

    public TaskWithinDayPredicate(Date date) {
        // this.calendar = new Calendar.Builder().setInstant(date).build();
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        Date taskDate = task.getDateTime().getDate();
        return isSameDay(taskDate);
    }

    private boolean isSameDay(Date date) {
        return this.date.getDay() == date.getDay()
                   && this.date.getMonth() == date.getMonth()
                   && this.date.getYear() == date.getYear();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIncompletePredicate); // instanceof handles null
    }
}
