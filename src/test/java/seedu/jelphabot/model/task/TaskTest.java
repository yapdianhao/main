package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ALICE;
import static seedu.jelphabot.testutil.TypicalTasks.BOB;

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
        assertTrue(ALICE.isSameTask(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTask(null));

        // different phone and module code -> returns false
        Task editedAlice = new TaskBuilder(ALICE).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new TaskBuilder(ALICE).withModuleCode(VALID_MODULE_CODE_TUTORIAL)
                .withTags(VALID_TAG_GRADED).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // same name, same module code, different attributes -> returns true
        editedAlice = new TaskBuilder(ALICE).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // same name, same phone, same module code, different attributes -> returns true
        editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_GRADED).build();
        assertTrue(ALICE.isSameTask(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different task -> returns false
        assertNotEquals(ALICE, BOB);

        // different description -> returns false
        Task editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        // editedAlice = new TaskBuilder(ALICE).build();
        // assertNotEquals(ALICE, editedAlice);

        // different module code -> returns false
        editedAlice = new TaskBuilder(ALICE).withModuleCode(VALID_MODULE_CODE_TUTORIAL).build();
        assertNotEquals(ALICE, editedAlice);

        // different tags -> returns false
        editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_GRADED).build();
        assertNotEquals(ALICE, editedAlice);
    }
}
