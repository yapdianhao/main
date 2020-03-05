package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DATETIME_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_PRIORITY_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_STATUS_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ASSESSMENT;
import static seedu.jelphabot.testutil.TypicalTasks.TUTORIAL;

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
        assertTrue(ASSESSMENT.isSameTask(ASSESSMENT));

        // null -> returns false
        assertFalse(ASSESSMENT.isSameTask(null));

        // different description -> returns false
        Task editedAssignment = new TaskBuilder(ASSESSMENT).withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        assertFalse(ASSESSMENT.isSameTask(editedAssignment));

        // different dateTime  -> returns false
        editedAssignment = new TaskBuilder(ASSESSMENT).withDateTime(VALID_DATETIME_TUTORIAL).build();
        assertFalse(ASSESSMENT.isSameTask(editedAssignment));

        // different module code -> returns false
        editedAssignment = new TaskBuilder().withModuleCode(VALID_MODULE_CODE_TUTORIAL).build();
        assertFalse(ASSESSMENT.isSameTask(editedAssignment));

        // same description, same datetime, same module code, different attributes -> returns true
        editedAssignment = new TaskBuilder(ASSESSMENT).withPriority(VALID_PRIORITY_TUTORIAL).build();
        assertTrue(ASSESSMENT.isSameTask(editedAssignment));

        // same description, same datetime, same module code, different attributes -> returns true
        editedAssignment = new TaskBuilder(ASSESSMENT).withStatus(VALID_STATUS_TUTORIAL).build();
        assertTrue(ASSESSMENT.isSameTask(editedAssignment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task assignmentCopy = new TaskBuilder(ASSESSMENT).build();
        assertEquals(ASSESSMENT, assignmentCopy);

        // same object -> returns true
        assertEquals(ASSESSMENT, ASSESSMENT);

        // null -> returns false
        assertNotEquals(null, ASSESSMENT);

        // different type -> returns false
        assertNotEquals(5, ASSESSMENT);

        // different task -> returns false
        assertNotEquals(ASSESSMENT, TUTORIAL);

        // different description -> returns false
        Task editedAssignment = new TaskBuilder(ASSESSMENT).withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        assertNotEquals(ASSESSMENT, editedAssignment);

        // different dateTime  -> returns false
        editedAssignment = new TaskBuilder(ASSESSMENT).withDateTime(VALID_DATETIME_TUTORIAL).build();
        assertNotEquals(ASSESSMENT, editedAssignment);

        // different module code -> returns false
        editedAssignment = new TaskBuilder(ASSESSMENT).withModuleCode(VALID_MODULE_CODE_TUTORIAL).build();
        assertNotEquals(ASSESSMENT, editedAssignment);

        // different priority -> returns false
        editedAssignment = new TaskBuilder(ASSESSMENT).withPriority(VALID_PRIORITY_TUTORIAL).build();
        assertNotEquals(ASSESSMENT, editedAssignment);

        // different status -> returns false
        editedAssignment = new TaskBuilder(ASSESSMENT).withStatus(VALID_STATUS_TUTORIAL).build();
        assertNotEquals(ASSESSMENT, editedAssignment);

        // different tags -> returns false
        editedAssignment = new TaskBuilder(ASSESSMENT).withTags(VALID_TAG_PROJECT).build();
        assertNotEquals(ASSESSMENT, editedAssignment);
    }
}
