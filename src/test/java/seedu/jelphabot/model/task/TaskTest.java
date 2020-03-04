package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ASSESSMENT;
import static seedu.jelphabot.testutil.TypicalTasks.ASSIGNMENT;

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

        // different phone and module code -> returns false
        Task editedAlice = new TaskBuilder(ASSESSMENT).build();
        assertFalse(ASSESSMENT.isSameTask(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(ASSESSMENT).withDescription(VALID_DESCRIPTION_ASSIGNMENT).build();
        assertFalse(ASSESSMENT.isSameTask(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new TaskBuilder(ASSESSMENT).withModuleCode(VALID_MODULE_CODE_ASSIGNMENT)
                .withTags(VALID_TAG_GRADED).build();
        assertTrue(ASSESSMENT.isSameTask(editedAlice));

        // same name, same module code, different attributes -> returns true
        editedAlice = new TaskBuilder(ASSESSMENT).build();
        assertTrue(ASSESSMENT.isSameTask(editedAlice));

        // same name, same phone, same module code, different attributes -> returns true
        editedAlice = new TaskBuilder(ASSESSMENT).withTags(VALID_TAG_GRADED).build();
        assertTrue(ASSESSMENT.isSameTask(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(ASSESSMENT).build();
        assertEquals(ASSESSMENT, aliceCopy);

        // same object -> returns true
        assertEquals(ASSESSMENT, ASSESSMENT);

        // null -> returns false
        assertNotEquals(null, ASSESSMENT);

        // different type -> returns false
        assertNotEquals(5, ASSESSMENT);

        // different task -> returns false
        assertNotEquals(ASSESSMENT, ASSIGNMENT);

        // different description -> returns false
        Task editedAlice = new TaskBuilder(ASSESSMENT).withDescription(VALID_DESCRIPTION_ASSIGNMENT).build();
        assertNotEquals(ASSESSMENT, editedAlice);

        // different phone -> returns false
        // editedAlice = new TaskBuilder(ALICE).build();
        // assertNotEquals(ALICE, editedAlice);

        // different module code -> returns false
        editedAlice = new TaskBuilder(ASSESSMENT).withModuleCode(VALID_MODULE_CODE_ASSIGNMENT).build();
        assertNotEquals(ASSESSMENT, editedAlice);

        // different tags -> returns false
        editedAlice = new TaskBuilder(ASSESSMENT).withTags(VALID_TAG_GRADED).build();
        assertNotEquals(ASSESSMENT, editedAlice);
    }
}
