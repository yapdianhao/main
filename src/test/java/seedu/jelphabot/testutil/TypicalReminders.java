package seedu.jelphabot.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.reminder.ReminderDay;
import seedu.jelphabot.model.reminder.ReminderHour;

/**
 * A utility class containing a list of {@code Reminder} objects to be used in tests.
 */
public class TypicalReminders {

    public static final Reminder ASSESSMENT_REMINDER =
        new ReminderBuilder().withIndex(1)
            .withReminderDay(new ReminderDay(1))
            .withReminderHour(new ReminderHour(1)).build();

    public static final Reminder BOOK_REPORT_REMINDER =
        new ReminderBuilder().withIndex(2)
            .withReminderDay(new ReminderDay(2))
            .withReminderHour(new ReminderHour(2)).build();

    private TypicalReminders() {
    }

    /**
     * Returns an {@code JelphaBot} with all the typical tasks.
     */
    public static JelphaBot getTypicalJelphaBot() {
        JelphaBot ab = new JelphaBot();
        for (Reminder reminder: getTypicalReminders()) {
            ab.addReminder(reminder);
        }
        return ab;
    }

    public static List<Reminder> getTypicalReminders() {
        return new ArrayList<>(Arrays.asList(ASSESSMENT_REMINDER, BOOK_REPORT_REMINDER));
    }

}
