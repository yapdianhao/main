package seedu.jelphabot.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.reminder.ReminderDay;
import seedu.jelphabot.model.reminder.ReminderHour;

class JsonAdaptedReminder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reminder %s's field is missing!";

    private final String index;
    private final String reminderDay;
    private final String reminderHour;

    @JsonCreator
    public JsonAdaptedReminder (
        @JsonProperty("index") int index,
        @JsonProperty("reminderDay") int reminderDay,
        @JsonProperty("reminderHour") int reminderHour
    ) {
        this.index = index + "";
        this.reminderDay = reminderDay + "";
        this.reminderHour = reminderHour + "";
    }

    public JsonAdaptedReminder(Reminder reminder) {
        this.index = reminder.getIndex().getZeroBased() + "";
        this.reminderDay = reminder.getDaysToRemind().getReminderDay() + "";
        this.reminderHour = reminder.getHoursToRemind().getReminderHour() + "";
    }

    public Reminder toModelType() throws IllegalValueException {
        if (index == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Index.class.getSimpleName()));
        }
        if (reminderDay == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, ReminderDay.class.getSimpleName()));
        }
        if (!ReminderDay.isValidReminderDay(Integer.parseInt(reminderDay))) {
            throw new IllegalValueException(ReminderDay.MESSAGE_CONSTRAINTS);
        }
        if (reminderHour == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, ReminderHour.class.getSimpleName()));
        }
        if (!ReminderHour.isValidReminderHour(Integer.parseInt(reminderHour))) {
            throw new IllegalValueException(
                String.format(ReminderHour.MESSAGE_CONSTRAINTS));
        }
        return new Reminder(
            Index.fromZeroBased(Integer.parseInt(index)),
            new ReminderDay(Integer.parseInt(reminderDay)),
            new ReminderHour(Integer.parseInt(reminderHour)));
    }
}
