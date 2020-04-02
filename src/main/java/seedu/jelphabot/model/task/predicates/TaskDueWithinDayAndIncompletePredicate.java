package seedu.jelphabot.model.task.predicates;

import java.time.LocalDate;

import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} is due within the day and is incomplete
 */
public class TaskDueWithinDayAndIncompletePredicate extends TaskDueWithinDayPredicate {
    @Override
    public boolean test(Task task) {
        LocalDate taskDate = task.getDateTime().getDate();
        Status status = task.getStatus();
        return !taskDate.isAfter(super.getDate())
                   && !taskDate.isBefore(super.getDate())
                   && (status != Status.COMPLETE);
    }
}
