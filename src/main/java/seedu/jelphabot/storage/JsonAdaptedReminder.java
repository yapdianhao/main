package seedu.jelphabot.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.reminder.ReminderDay;
import seedu.jelphabot.model.reminder.ReminderHour;

class JsonAdaptedReminder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reminder %s's field is missing!";

    private final Index index;
    private final ReminderDay reminderDay;
    private final ReminderHour reminderHour;

    @JsonCreator
    public JsonAdaptedReminder (
        @JsonProperty("index") Index index,
        @JsonProperty("reminderDay") ReminderDay reminderDay,
        @JsonProperty("reminderHour") ReminderHour reminderHour
    ) {
        this.index = index;
        this.reminderDay = reminderDay;
        this.reminderHour = reminderHour;
    }

    public JsonAdaptedReminder(Reminder reminder) {
        this.index = reminder.getIndex();
        this.reminderDay = reminder.getDaysToRemind();
        this.reminderHour = reminder.getHoursToRemind();
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
        if (!ReminderDay.isValidReminderDay(reminderDay.getReminderDay())) {
            throw new IllegalValueException(ReminderDay.MESSAGE_CONSTRAINTS);
        }
        if (reminderHour == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, ReminderHour.class.getSimpleName()));
        }
        if (!ReminderHour.isValidReminderHour(reminderHour.getReminderHour())) {
            throw new IllegalValueException(
                String.format(ReminderHour.MESSAGE_CONSTRAINTS));
        }
        return new Reminder(index, reminderDay, reminderHour);
    }
}