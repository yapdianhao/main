package seedu.jelphabot.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

/**
 * Represent the hours before the task dues in JelphaBot.
 */
public class ReminderHour {

    public static final String MESSAGE_CONSTRAINTS = "Reminder hour should be within 24 hours from now";

    private final int reminderHour;

    public ReminderHour(int reminderHour) {
        requireNonNull(reminderHour);
        checkArgument(isValidReminderHour(reminderHour), MESSAGE_CONSTRAINTS);
        this.reminderHour = reminderHour;
    }

    public static boolean isValidReminderHour(int hourToTest) {
        return 0 <= hourToTest && hourToTest <= 24;
    }

    public int getReminderHour() {
        return this.reminderHour;
    }

    /**
     * Returns a boolean to determine whether two reminders share the same hour.
     * @param other
     * @return boolean
     */
    public boolean equals(Object other) {
        return other == this
                   || (other instanceof ReminderHour && reminderHour == ((ReminderHour) other).reminderHour);
    }
}
