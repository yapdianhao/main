package seedu.jelphabot.model.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReminderHourTest {

    @Test
    public void isValidReminderDay() {

        // invalid ReminderDay
        assertFalse(ReminderHour.isValidReminderHour(-1)); // negative hours
        assertFalse(ReminderHour.isValidReminderHour(30)); // 1 day 6 hours

        // valid ReminderDay
        assertTrue(ReminderHour.isValidReminderHour(1)); // valid hours
    }
}
