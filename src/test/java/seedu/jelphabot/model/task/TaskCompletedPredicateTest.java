package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.testutil.TaskBuilder;

public class TaskCompletedPredicateTest {

    @Test
    public void equals() {
        TaskCompletedPredicate predicate = new TaskCompletedPredicate();

        // same object returns true
        assertEquals(predicate, predicate);

        // different types returns false
        assertNotEquals(1, predicate);

        // null returns false
        assertNotEquals(null, predicate);
    }

    @Test
    public void test_taskStatusEqual_returnsTrue() {
        // true if the status of the Task is set to COMPLETED
        TaskCompletedPredicate predicate = new TaskCompletedPredicate();
        Task completedTask = new TaskBuilder().withStatus("COMPLETE").build();
        assertTrue(predicate.test(completedTask));
    }
}
