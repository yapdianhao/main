package seedu.jelphabot.model.task.predicates;

import static seedu.jelphabot.commons.util.DateUtil.dateToLocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} falls after the given Date.
 */
public class TaskDueAfterDatePredicate implements FilterTaskByDatePredicate {

    private final LocalDateTime date;

    // default constructor sets the date to the instant the method was called
    public TaskDueAfterDatePredicate() {
        date = LocalDateTime.now();
    }

    public TaskDueAfterDatePredicate(LocalDate date) {
        this.date = date.atStartOfDay();
    }

    public TaskDueAfterDatePredicate(DateTime dateTime) {
        Date date = dateTime.getDate();
        this.date = dateToLocalDateTime(date);
    }

    /**
     * Composes two FilterTaskByDatePredicate to get a predicate that is the combination of the two.
     *
     * @param other another predicate that allows tasks to be filtered by date
     * @return a FilterTaskByDatePredicate that combines both predicates
     */
    public FilterTaskByDatePredicate and(FilterTaskByDatePredicate other) {
        return new FilterTaskByDatePredicate() {
            @Override
            public boolean test(Task task) {
                return TaskDueAfterDatePredicate.this.test(task) && other.test(task);
            }
        };
    }

    @Override
    public boolean test(Task task) {
        LocalDateTime taskDate = dateToLocalDateTime(task.getDateTime().getDate());
        return taskDate.isAfter(this.date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskDueAfterDatePredicate) // instanceof handles null
                          && this.date.equals(((TaskDueAfterDatePredicate) other).date);
    }
}
