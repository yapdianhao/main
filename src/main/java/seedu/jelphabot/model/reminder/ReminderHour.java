package seedu.jelphabot.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

public class ReminderHour {

    public static final String MESSAGE_CONSTRAINTS = "Reminder hour should be within 24 hours from now";

    public final int reminderHour;

    public ReminderHour(int reminderHour) {
        requireNonNull(reminderHour);
        checkArgument(isValidReminderHour(reminderHour), MESSAGE_CONSTRAINTS);
        this.reminderHour = reminderHour;
    }

    public static boolean isValidReminderHour(int hourToTest) {
        return 0 <= hourToTest && hourToTest <= 24;
    }

    public boolean equals(Object other) {
        return other == this ||
                   (other instanceof ReminderHour && reminderHour == ((ReminderHour) other).reminderHour);
    }
}
