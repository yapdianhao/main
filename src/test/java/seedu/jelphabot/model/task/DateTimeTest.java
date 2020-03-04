package seedu.jelphabot.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.testutil.Assert.assertThrows;

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
        assertFalse(DateTime.isValidDateTime("May 1 2020 11 11")); // day and date are in the wrong order
        assertFalse(DateTime.isValidDateTime("May-1-2020")); // date with no time
        assertFalse(DateTime.isValidDateTime("11 11")); // time with no date
        assertFalse(DateTime.isValidDateTime("2020-1-Jan 11 00")); // incorrect format
        assertFalse(DateTime.isValidDateTime("Jan-1-2020 33 69")); // invalid time
        // valid addresses
        assertTrue(DateTime.isValidDateTime("May-1-2020 11 11")); // Date and time with hyphen
        // valid addresses for developers for ease of testing, to be deleted in the final product
        assertTrue(DateTime.isValidDateTime("1-May-2020 11 11")); //Day and month reversed
        assertTrue(DateTime.isValidDateTime("May/1/2020 22 00"));
        assertTrue(DateTime.isValidDateTime("1/1/2020 12 00"));
        assertTrue(DateTime.isValidDateTime("2 May 2020 20 20")); // Date and time with no hyphen
    }
}
