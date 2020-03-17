package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.testutil.TaskBuilder;

public class TaskIncompletePredicateTest {

    @Test
    public void equals() {
        TaskIncompletePredicate predicate = new TaskIncompletePredicate();

        // same object returns true
        assertEquals(predicate, predicate);

        // different types returns false
        assertNotEquals(1, predicate);

        // null returns false
        assertNotEquals(null, predicate);
    }

    @Test
    public void test_taskStatusEqual_returnsTrue() {
        // true if the status of the Task is set to INCOMPLETE
        TaskIncompletePredicate predicate = new TaskIncompletePredicate();
        Task incompleteTask = new TaskBuilder().withStatus("INCOMPLETE").build();
        assertTrue(predicate.test(incompleteTask));
    }
}
