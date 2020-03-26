package seedu.jelphabot.model.task.predicates;

import static seedu.jelphabot.commons.util.DateUtil.dateToLocalDate;
import static seedu.jelphabot.commons.util.DateUtil.dateToLocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Date date = dateTime.getDate();
        this.date = dateToLocalDate(date);
    }

    /**
     * Tests that a {@code Task}'s {@code DateTime} falls within the given Date.
     */
    @Override
    public boolean test(Task task) {
        LocalDateTime taskDate = dateToLocalDateTime(task.getDateTime().getDate());
        return taskDate.isAfter(date.atStartOfDay())
                   && taskDate.isBefore(date.plusDays(1).atStartOfDay());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskDueWithinDayPredicate) // instanceof handles null
                        && this.date.equals(((TaskDueWithinDayPredicate) other).date);
    }
}
