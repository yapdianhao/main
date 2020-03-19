package seedu.jelphabot.model.task.predicates;

import java.util.Calendar;
import java.util.Date;

import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Task;

public class TaskDueBeforeDatePredicate implements FilterTaskByDatePredicate {

    private final Date date;

    // default constructor sets the date to today's date
    public TaskDueBeforeDatePredicate() {
        date = Calendar.getInstance().getTime();
    }

    public TaskDueBeforeDatePredicate(DateTime date) {
        this.date = date.getDate();
    }

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
        Date taskDate = task.getDateTime().getDate();
        System.out.println(taskDate);
        return taskDate.before(this.date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskDueBeforeDatePredicate) // instanceof handles null
                          && this.date.equals(((TaskDueBeforeDatePredicate) other).date);
    }
}
