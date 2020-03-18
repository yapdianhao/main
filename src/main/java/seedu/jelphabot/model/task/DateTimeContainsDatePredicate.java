package seedu.jelphabot.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code DateTime} matches the date given.
 */
public class DateTimeContainsDatePredicate implements Predicate<Task> {
    private final String date;

    public DateTimeContainsDatePredicate(String date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        String taskDateTime = task.getDateTime().value;
        String taskDate = taskDateTime.split(" ")[0];
        return date.equals(taskDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof DateTimeContainsDatePredicate // instanceof handles nulls
                           && date.equals(((DateTimeContainsDatePredicate) other).date)); // state check
    }
}
