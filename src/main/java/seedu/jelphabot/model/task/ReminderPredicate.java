package seedu.jelphabot.model.task;

import java.time.LocalDate;

import seedu.jelphabot.model.task.predicates.TaskIsCompletedPredicate;
import seedu.jelphabot.model.task.predicates.TaskIsIncompletePredicate;

/**
 * Tests that a {@code Task}'s {@code DateTime} is due within a week from now.
 */
public class ReminderPredicate extends TaskIsCompletedPredicate {
    // private final Calendar calendar;
    private final LocalDate date = LocalDate.now();

    @Override
    public boolean test(Task task) {
        LocalDate taskDate = task.getDateTime().getDate();
        return super.test(task) && withinAWeek(taskDate);
    }

    @SuppressWarnings("deprecation")
    private boolean withinAWeek(LocalDate taskDate) {
        return !taskDate.isAfter(date.plusDays(7))
                   && !taskDate.isBefore(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIsIncompletePredicate); // instanceof handles null
    }
}
