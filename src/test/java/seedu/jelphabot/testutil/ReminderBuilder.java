package seedu.jelphabot.testutil;

import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.reminder.ReminderDay;
import seedu.jelphabot.model.reminder.ReminderHour;
/**
 * A utility class to help build Reminder objects.
 */
public class ReminderBuilder {

    private Index index;
    private ReminderDay reminderDay;
    private ReminderHour reminderHour;

    /**
     * Initializes the ReminderBuilder with the data of {@code reminderToCopy}.
     * @param toCopy
     */
    public ReminderBuilder(Reminder reminderToCopy) {
        index = reminderToCopy.getIndex();
        reminderDay = reminderToCopy.getDaysToRemind();
        reminderHour = reminderToCopy.getHoursToRemind();
    }

    /**
     * Sets the {@code Index} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withIndex(int index) {
        this.index = Index.fromOneBased(index);
        return this;
    }

    /**
     * Sets the {@code ReminderDay} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withReminderDay(ReminderDay reminderDay) {
        this.reminderDay = reminderDay;
        return this;
    }

    /**
     * Sets the {@code ReminderHour} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withReminderHour(ReminderHour reminderHour) {
        this.reminderHour = reminderHour;
        return this;
    }
    public Reminder build() {
        return new Reminder(index, reminderDay, reminderHour);
    }
}
