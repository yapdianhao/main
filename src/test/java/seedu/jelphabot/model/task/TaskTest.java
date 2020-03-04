package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_SCHOOL;
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

        // different phone and module code -> returns false
        Task editedAlice = new TaskBuilder(ASSIGNMENT).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(ASSIGNMENT).withDescription(VALID_DESC_JOB).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new TaskBuilder(ASSIGNMENT).withModuleCode(VALID_MODULE_CODE_JOB)
                .withTags(VALID_TAG_SCHOOL).build();
        assertTrue(ASSIGNMENT.isSameTask(editedAlice));

        // same name, same module code, different attributes -> returns true
        editedAlice = new TaskBuilder(ASSIGNMENT).build();
        assertTrue(ASSIGNMENT.isSameTask(editedAlice));

        // same name, same phone, same module code, different attributes -> returns true
        editedAlice = new TaskBuilder(ASSIGNMENT).withTags(VALID_TAG_SCHOOL).build();
        assertTrue(ASSIGNMENT.isSameTask(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(ASSIGNMENT).build();
        assertEquals(ASSIGNMENT, aliceCopy);

        // same object -> returns true
        assertEquals(ASSIGNMENT, ASSIGNMENT);

        // null -> returns false
        assertNotEquals(null, ASSIGNMENT);

        // different type -> returns false
        assertNotEquals(5, ASSIGNMENT);

        // different task -> returns false
        assertNotEquals(ASSIGNMENT, JOB);

        // different description -> returns false
        Task editedAlice = new TaskBuilder(ASSIGNMENT).withDescription(VALID_DESC_JOB).build();
        assertNotEquals(ASSIGNMENT, editedAlice);

        // different phone -> returns false
        // editedAlice = new TaskBuilder(ALICE).build();
        // assertNotEquals(ALICE, editedAlice);

        // different module code -> returns false
        editedAlice = new TaskBuilder(ASSIGNMENT).withModuleCode(VALID_MODULE_CODE_JOB).build();
        assertNotEquals(ASSIGNMENT, editedAlice);

        // different tags -> returns false
        editedAlice = new TaskBuilder(ASSIGNMENT).withTags(VALID_TAG_SCHOOL).build();
        assertNotEquals(ASSIGNMENT, editedAlice);
    }
}
