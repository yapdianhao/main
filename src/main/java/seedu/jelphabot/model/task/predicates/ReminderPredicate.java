//@@ author yapdianhao
package seedu.jelphabot.model.task.predicates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code DateTime} is due within a week from now.
 */
public class ReminderPredicate extends TaskIsIncompletePredicate {

    private final LocalDate currDate = LocalDate.now();
    private final LocalTime currTime = LocalTime.now();
    private final LocalDateTime currDateTime = LocalDateTime.now();
    private final Logger logger = LogsCenter.getLogger(ReminderPredicate.class);

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
            if (reminder.getIndex().getZeroBased() == reminderKey) {
                correspondingReminder = reminder;
                break;
            }
        }
        if (correspondingReminder == null) {
            return false;
        } else {
            return shouldBeReminded(task, correspondingReminder);
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
        //logger.info("" + taskDateTime);
        //logger.info("shouldBeReminded " + taskDateTime.minusDays(reminder.getDaysToRemind().getReminderDay())
        //                                     .isAfter(currDateTime));
        //logger.info("shouldBeReminded1 " + taskDateTime
        //                                       .minusHours(reminder.getHoursToRemind()
        //                                                       .getReminderHour())
        //                                      .isAfter(currDateTime));
        //logger.info("shouldBeReminded2 " + taskDateTime
        //                                       .minusDays(reminder.getDaysToRemind()
        //                                                      .getReminderDay())
        //                                       .isBefore(currDateTime));
        if (taskDateTime.isBefore(currDateTime)) {
            return true;
        }
        if (taskDateTime.minusDays(reminder.getDaysToRemind().getReminderDay())
                .isAfter(currDateTime)) {
            return true;
        } else if (taskDateTime.minusDays(reminder.getDaysToRemind().getReminderDay()).isBefore(currDateTime)) {
            return taskDateTime.minusHours(reminder.getHoursToRemind().getReminderHour()).isAfter(currDateTime);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof TaskIsIncompletePredicate); // instanceof handles null
    }
}
