package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    void isValidDateTime() {
        // null datetime
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid addresses
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("Feb-29-2019 11 00")); // illegal date
        assertFalse(DateTime.isValidDateTime("May 1 2020 11 11")); // date and time with no hyphen
        assertFalse(DateTime.isValidDateTime("May-1-2020")); // date with no time
        assertFalse(DateTime.isValidDateTime("11 11")); // time with no date

        // valid addresses
        assertTrue(DateTime.isValidDateTime("May-1-2020 11 11")); // Date and time with hyphen
    }
}
