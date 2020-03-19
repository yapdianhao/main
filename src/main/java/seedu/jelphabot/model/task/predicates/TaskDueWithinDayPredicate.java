<<<<<<< HEAD:src/main/java/seedu/jelphabot/model/task/TaskWithinDayPredicate.java
package seedu.jelphabot.model.task;
=======
package seedu.jelphabot.model.task.predicates;
>>>>>>> 885e5b4d13214f7d7e39d1501550dd15df436118:src/main/java/seedu/jelphabot/model/task/predicates/TaskDueWithinDayPredicate.java

import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

<<<<<<< HEAD:src/main/java/seedu/jelphabot/model/task/TaskWithinDayPredicate.java
=======
import seedu.jelphabot.model.task.Task;

>>>>>>> 885e5b4d13214f7d7e39d1501550dd15df436118:src/main/java/seedu/jelphabot/model/task/predicates/TaskDueWithinDayPredicate.java
/**
 * Tests that a {@code Task}'s {@code DateTime} falls within the given Date.
 */
public class TaskDueWithinDayPredicate implements Predicate<Task> {
    // private final Calendar calendar;
    private final Date date;

<<<<<<< HEAD:src/main/java/seedu/jelphabot/model/task/TaskWithinDayPredicate.java
    // default constructor sets the date to today's date
    public TaskWithinDayPredicate() {
        date = Calendar.getInstance().getTime();
    }
    public TaskWithinDayPredicate(Date date) {
=======
    public TaskDueWithinDayPredicate(Date date) {
>>>>>>> 885e5b4d13214f7d7e39d1501550dd15df436118:src/main/java/seedu/jelphabot/model/task/predicates/TaskDueWithinDayPredicate.java
        // this.calendar = new Calendar.Builder().setInstant(date).build();
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        Date taskDate = task.getDateTime().getDate();
        return isSameDay(taskDate);
    }

    private boolean isSameDay(Date date) {
        return this.date.getDay() == date.getDay()
                   && this.date.getMonth() == date.getMonth()
                   && this.date.getYear() == date.getYear();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIsIncompletePredicate); // instanceof handles null
    }
}
