package seedu.jelphabot.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

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

    public static boolean isValidReminderDay(int dayToTest) {
        return 0 <= dayToTest && dayToTest <= 7;
    }

    public boolean equals(Object other) {
        return other == this ||
                   (other instanceof ReminderDay && reminderDay == ((ReminderDay) other).reminderDay);
    }
}
