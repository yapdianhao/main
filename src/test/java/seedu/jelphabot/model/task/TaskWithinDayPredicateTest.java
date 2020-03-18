package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.testutil.TaskBuilder;

public class TaskWithinDayPredicateTest {
    private static final Date TEST_DATE = new GregorianCalendar(2020, Calendar.MARCH, 18).getTime();
    private static final TaskWithinDayPredicate TEST_PREDICATE = new TaskWithinDayPredicate(TEST_DATE);

    @Test
    public void equals() {
        TaskWithinDayPredicate predicate = new TaskWithinDayPredicate();

        // same object returns true
        assertEquals(predicate, predicate);

        // different types returns false
        assertNotEquals(1, predicate);

        // null returns false
        assertNotEquals(null, predicate);
    }

    @Test
    public void test_taskDueDateWithinDay_returnsTrue() {
        // true if the task is due within the day
        Task testTask = new TaskBuilder().withDateTime("Mar-18-2020 22 00").build();
        assertTrue(TEST_PREDICATE.test(testTask));
    }

    @Test
    public void test_taskDueDateNotWithinDay_returnsFalse() {
        // false if the task is not due within the day
        Task testTask = new TaskBuilder().withDateTime("Mar-19-2020 22 00").build();
        assertFalse(TEST_PREDICATE.test(testTask));
    }
}
