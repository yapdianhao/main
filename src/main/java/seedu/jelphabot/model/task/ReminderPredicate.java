package seedu.jelphabot.model.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.task.predicates.TaskIsCompletedPredicate;
import seedu.jelphabot.model.task.predicates.TaskIsIncompletePredicate;

/**
 * Tests that a {@code Task}'s {@code DateTime} is due within a week from now.
 */
public class ReminderPredicate extends TaskIsCompletedPredicate {

    private final LocalDate currDate = LocalDate.now();
    private final LocalTime currTime = LocalTime.now();
    private final LocalDateTime currDateTime = LocalDateTime.now();

    private final List<Task> taskList;
    private final List<Reminder> reminderList;

    public ReminderPredicate(List<Task> taskList, List<Reminder> reminderList) {
        this.taskList = taskList;
        this.reminderList = reminderList;
    }

    @Override
    public boolean test(Task task) {
        int reminderKey = taskList.indexOf(task);
        Reminder correspondingReminder = null;
        for (Reminder reminder : reminderList) {
            if (reminder.getIndex().getOneBased() == reminderKey) {
                correspondingReminder = reminder;
                break;
            }
        }
        if (correspondingReminder == null) {
            return false;
        } else {
            return super.test(task) && shouldBeReminded(task, correspondingReminder);
        }
    }

    /**
     * A task should be reminded if it is before the specified day by the reminder, or within the specified hour.
     * @param task
     * @param reminder
     * @return boolean
     */
    public boolean shouldBeReminded(Task task, Reminder reminder) {
        LocalDateTime taskDateTime = task.getDateTime().getDateTime();
        if (taskDateTime.minusDays(reminder.getDaysToRemind().getReminderDay())
                .isAfter(currDateTime)) {
            return true;
        } else if (taskDateTime.minusDays((long) reminder.getDaysToRemind().getReminderDay()).isEqual(currDateTime)) {
            if (taskDateTime.getHour() - reminder.getHoursToRemind().getReminderHour() <= currTime.getHour()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIsIncompletePredicate); // instanceof handles null
    }
}
