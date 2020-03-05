package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.jelphabot.testutil.TypicalTasks.JOB;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ASSIGNMENT.isSameTask(ASSIGNMENT));

        // null -> returns false
        assertFalse(ASSIGNMENT.isSameTask(null));

        // different module code -> returns false
        Task editedAssignment = new TaskBuilder().withModuleCode(VALID_MODULE_CODE_JOB).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));

        // different description -> returns false
        editedAssignment = new TaskBuilder(ASSIGNMENT).withDescription(VALID_DESC_JOB).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));

        // same description, different status -> returns true
        editedAssignment = new TaskBuilder(ASSIGNMENT).withStatus(Status.COMPLETE).build();
        assertTrue(ASSIGNMENT.isSameTask(editedAssignment));

        // same description, same datetime, same module code, different attributes -> returns true
        editedAssignment = new TaskBuilder(ASSIGNMENT).withPriority(Priority.HIGH).build();
        assertTrue(ASSIGNMENT.isSameTask(editedAssignment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task assignmentCopy = new TaskBuilder(ASSIGNMENT).build();
        assertEquals(ASSIGNMENT, assignmentCopy);

        // same object -> returns true
        assertEquals(ASSIGNMENT, ASSIGNMENT);

        // null -> returns false
        assertNotEquals(null, ASSIGNMENT);

        // different type -> returns false
        assertNotEquals(5, ASSIGNMENT);

        // different task -> returns false
        assertNotEquals(ASSIGNMENT, JOB);

        // different description -> returns false
        Task editedAssignment = new TaskBuilder(ASSIGNMENT).withDescription(VALID_DESC_JOB).build();
        assertNotEquals(ASSIGNMENT, editedAssignment);

        // different module code -> returns false
        editedAssignment = new TaskBuilder(ASSIGNMENT).withModuleCode(VALID_MODULE_CODE_JOB).build();
        assertNotEquals(ASSIGNMENT, editedAssignment);

        // different tags -> returns false
        editedAssignment = new TaskBuilder(ASSIGNMENT).withTags(VALID_TAG_PROJECT).build();
        assertNotEquals(ASSIGNMENT, editedAssignment);
    }
}
