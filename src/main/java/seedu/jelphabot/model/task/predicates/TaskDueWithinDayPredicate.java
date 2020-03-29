package seedu.jelphabot.model.task.predicates;

import static seedu.jelphabot.commons.util.DateUtil.dateToLocalDate;

import java.time.LocalDate;
import java.util.Date;

import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} falls within the given Date.
 */
public class TaskDueWithinDayPredicate implements FilterTaskByDatePredicate {
    private final LocalDate date;

    // default constructor sets the date to the instant the method was called
    public TaskDueWithinDayPredicate() {
        date = LocalDate.now();
    }

    public TaskDueWithinDayPredicate(Date date) {
        this.date = dateToLocalDate(date);
    }

    public TaskDueWithinDayPredicate(DateTime dateTime) {
        this.date = dateTime.getDate();
    }

    /**
     * Tests that a {@code Task}'s {@code DateTime} falls within the given Date.
     */
    @Override
    public boolean test(Task task) {
        LocalDate taskDate = task.getDateTime().getDate();
        return !taskDate.isAfter(date.plusDays(1))
                   && !taskDate.isBefore(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskDueWithinDayPredicate) // instanceof handles null
                        && this.date.equals(((TaskDueWithinDayPredicate) other).date);
    }
}
