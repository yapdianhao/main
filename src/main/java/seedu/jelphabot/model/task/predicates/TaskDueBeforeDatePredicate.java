package seedu.jelphabot.model.task.predicates;

import static seedu.jelphabot.commons.util.DateUtil.dateToLocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} falls before the given Date.
 */
public class TaskDueBeforeDatePredicate implements FilterTaskByDatePredicate {

    private final LocalDateTime date;

    // default constructor sets the date to today's date
    public TaskDueBeforeDatePredicate() {
        date = LocalDate.now().atStartOfDay();
    }

    public TaskDueBeforeDatePredicate(LocalDate date) {
        this.date = date.atStartOfDay();
    }

    /**
     * Support for methods that use Java7 Date.
     *
     * @param dateTime the internal model representation of DateTime.
     */
    public TaskDueBeforeDatePredicate(DateTime dateTime) {
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
                return TaskDueBeforeDatePredicate.this.test(task) && other.test(task);
            }
        };
    }

    @Override
    public boolean test(Task task) {
        LocalDateTime taskDate = dateToLocalDateTime(task.getDateTime().getDate());
        return taskDate.isBefore(this.date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskDueBeforeDatePredicate) // instanceof handles null
                          && this.date.equals(((TaskDueBeforeDatePredicate) other).date);
    }
}
