package seedu.jelphabot.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

/**
 * Represents the days before the task dues in JelphaBot.
 */
public class ReminderDay {

    public static final String MESSAGE_CONSTRAINTS = "Reminder date should be within one week from now";

    private final int reminderDay;

    public ReminderDay(int reminderDay) {
        requireNonNull(reminderDay);
        checkArgument(isValidReminderDay(reminderDay), MESSAGE_CONSTRAINTS);
        this.reminderDay = reminderDay;
    }

    public int getReminderDay() {
        return this.reminderDay;
    }

    /**
     * Returns a boolean to determine whether the days to remind is within 7 days.
     * @param dayToTest
     * @return boolean
     */
    public static boolean isValidReminderDay(int dayToTest) {
        return 0 <= dayToTest && dayToTest <= 7;
    }

    public boolean equals(Object other) {
        return other == this
                   || (other instanceof ReminderDay && reminderDay ==
                                                           ((ReminderDay) other).reminderDay);
    }
}
