package seedu.jelphabot.model.task.predicates;

import static seedu.jelphabot.commons.util.DateUtil.dateToLocalDateTime;
import static seedu.jelphabot.commons.util.DateUtil.getDateToday;
import static seedu.jelphabot.commons.util.DateUtil.getDateTomorrow;

import java.time.LocalDateTime;
import java.util.Date;

import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} falls within the given Date.
 */
public class TaskDueWithinDayPredicate implements FilterTaskByDatePredicate {
    private final LocalDateTime date;

    // default constructor sets the date to the instant the method was called
    public TaskDueWithinDayPredicate() {
        date = LocalDateTime.now();
    }

    public TaskDueWithinDayPredicate(Date date) {
        this.date = dateToLocalDateTime(date);
    }

    /**
     * Tests that a {@code Task}'s {@code DateTime} falls within the given Date.
     */
    @Override
    public boolean test(Task task) {
        LocalDateTime taskDate = dateToLocalDateTime(task.getDateTime().getDate());
        return taskDate.isAfter(getDateToday().atStartOfDay())
                   && taskDate.isBefore(getDateTomorrow().atStartOfDay());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIsIncompletePredicate); // instanceof handles null
    }
}
