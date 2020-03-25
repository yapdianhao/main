package seedu.jelphabot.model.task;

import java.util.Calendar;
import java.util.Date;

import seedu.jelphabot.model.task.predicates.TaskIsCompletedPredicate;
import seedu.jelphabot.model.task.predicates.TaskIsIncompletePredicate;

/**
 * Tests that a {@code Task}'s {@code DateTime} is due within a week from now.
 */
public class ReminderPredicate extends TaskIsCompletedPredicate {
    // private final Calendar calendar;
    private final Date date = Calendar.getInstance().getTime();

    @Override
    public boolean test(Task task) {
        Date taskDate = task.getDateTime().getDate();
        return super.test(task) && withinAWeek(taskDate);
    }

    @SuppressWarnings("deprecation")
    private boolean withinAWeek(Date date) {
        return (date.getDay() - this.date.getDay()) <= 7
                   && this.date.getMonth() == date.getMonth()
                   && this.date.getYear() == date.getYear();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIsIncompletePredicate); // instanceof handles null
    }
}
