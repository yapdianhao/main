package seedu.jelphabot.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Status} is INCOMPLETE.
 */
public class TaskIncompletePredicate implements Predicate<Task> {
    @Override
    public boolean test(Task task) {
        return task.getStatus() == Status.INCOMPLETE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskIncompletePredicate); // instanceof handles null
    }
}
