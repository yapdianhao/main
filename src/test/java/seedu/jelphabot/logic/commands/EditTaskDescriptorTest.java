package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.jelphabot.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditTaskDescriptor descriptorWithSameValues = new EditCommand.EditTaskDescriptor(DESC_ASSIGNMENT);
        assertEquals(DESC_ASSIGNMENT, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_ASSIGNMENT, DESC_ASSIGNMENT);

        // null -> returns false
        assertNotEquals(null, DESC_ASSIGNMENT);

        // different types -> returns false
        assertNotEquals(5, DESC_ASSIGNMENT);

        // different values -> returns false
        assertNotEquals(DESC_ASSIGNMENT, DESC_TUTORIAL);

        // different name -> returns false
        EditTaskDescriptor editedAmy =
                new EditTaskDescriptorBuilder(DESC_ASSIGNMENT).withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        assertNotEquals(DESC_ASSIGNMENT, editedAmy);

        // different phone -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_ASSIGNMENT).build();
        assertNotEquals(DESC_ASSIGNMENT, editedAmy);

        // different module code -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_ASSIGNMENT).withModuleCode(VALID_MODULE_CODE_TUTORIAL).build();
        assertNotEquals(DESC_ASSIGNMENT, editedAmy);

        // different tags -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_ASSIGNMENT).withTags(VALID_TAG_GRADED).build();
        assertNotEquals(DESC_ASSIGNMENT, editedAmy);
    }
}
