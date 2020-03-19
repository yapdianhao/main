package seedu.jelphabot.model.task.predicates;

import java.util.Calendar;
import java.util.Date;

import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} falls after the given Date.
 */
public class TaskDueAfterDatePredicate implements FilterTaskByDatePredicate {

    private final Date date;

    // default constructor sets the date to today's date
    public TaskDueAfterDatePredicate() {
        date = Calendar.getInstance().getTime();
    }

    public TaskDueAfterDatePredicate(DateTime date) {
        this.date = date.getDate();
    }

    public TaskDueAfterDatePredicate(Date date) {
        this.date = date;
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
        Date taskDate = task.getDateTime().getDate();
        System.out.println(taskDate);
        return taskDate.after(this.date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskDueAfterDatePredicate) // instanceof handles null
                          && this.date.equals(((TaskDueAfterDatePredicate) other).date);
    }
}
