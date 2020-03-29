package seedu.jelphabot.testutil;

import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.reminder.ReminderDay;
import seedu.jelphabot.model.reminder.ReminderHour;

/**
 * A utility class to help build Reminder objects.
 */
public class ReminderBuilder {

    public static final int DEFAULT_INDEX = 1;
    public static final int DEFAULT_REMINDERDAY = 1;
    public static final int DEFAULT_REMINDERHOUR = 1;

    private Index index;
    private ReminderDay reminderDay;
    private ReminderHour reminderHour;

    public ReminderBuilder() {
        index = Index.fromOneBased(DEFAULT_INDEX);
        reminderDay = new ReminderDay(DEFAULT_REMINDERDAY);
        reminderHour = new ReminderHour(DEFAULT_REMINDERHOUR);
    }

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
