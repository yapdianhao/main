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

        // invalid DateTime
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("Feb-29-2019 11 00")); // illegal date
        assertFalse(DateTime.isValidDateTime("Jan-1-2020 33 69")); // illegal time
        assertFalse(DateTime.isValidDateTime("Jan-1-2020")); // date with no time
        assertFalse(DateTime.isValidDateTime("11 11")); // time with no date

        // invalid variations of MMM-d-yyyy HH mm / d-MMM-yyyy
        assertFalse(DateTime.isValidDateTime("2020-1-May 22 00")); // wrong format: yyyy-d-MM HH mm
        assertFalse(DateTime.isValidDateTime("05-1-2020 22 00")); // wrong format: MM-d-yyyy HH mm

        // invalid variations of MMM/d/yyyy HH mm / d/M/y
        assertFalse(DateTime.isValidDateTime("May1/2020 22 00")); // Missing / between month and day
        assertFalse(DateTime.isValidDateTime("1May/2020 22 00")); // Missing / between month and day
        assertFalse(DateTime.isValidDateTime("May/12020 22 00")); // Missing / between month and year
        assertFalse(DateTime.isValidDateTime("1/May2020 22 00")); // Missing / between month and year
        assertFalse(DateTime.isValidDateTime("May12020 22 00")); // Missing / between month, day and year
        assertFalse(DateTime.isValidDateTime("1May2020 22 00")); // Missing / between month, day and year

        //invalid variations of d MMM yyyy HH mm
        assertFalse(DateTime.isValidDateTime("May 2 2020 22 00")); // wrong format: MMM d yyyy HH mm
        assertFalse(DateTime.isValidDateTime("2020 2 May 22 00")); // wrong format: yyy d MMM
        assertFalse(DateTime.isValidDateTime("2 05 2020 22 00")); // wrong format: d MM yyyy

        // valid DateTime formats
        assertTrue(DateTime.isValidDateTime("May-1-2020 11 11")); // MMM-d-yyyy HH mm
        assertTrue(DateTime.isValidDateTime("1-May-2020 11 11")); // d-MMM-yyyy HH mm
        assertTrue(DateTime.isValidDateTime("May/1/2020 22 00")); // MMM/d/yyyy HH mm
        assertTrue(DateTime.isValidDateTime("1/1/2020 12 00")); // d/M/y HH mm
        assertTrue(DateTime.isValidDateTime("2 May 2020 20 20")); // d MMM yyyy HH mm
    }
}
