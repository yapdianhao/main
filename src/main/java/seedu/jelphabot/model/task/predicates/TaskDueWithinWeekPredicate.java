package seedu.jelphabot.model.task.predicates;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} falls within a week of the given Date.
 */
// TODO mark for deletion
public class TaskDueWithinWeekPredicate implements Predicate<Task> {
    private final Calendar calendar;

    public TaskDueWithinWeekPredicate() {
        Calendar calendar = new Calendar.Builder().setInstant(new Date()).build();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        this.calendar = calendar;
    }

    @Override
    public boolean test(Task task) {
        Date taskDate = task.getDateTime().getDate();
        return isWithinWeek(taskDate);
    }

    private boolean isWithinWeek(Date date) {
        return date.before(calendar.getTime()) && date.after(new Date());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIsIncompletePredicate); // instanceof handles null
    }
}
