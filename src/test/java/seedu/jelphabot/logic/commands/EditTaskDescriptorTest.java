package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_LAB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_SCHOOL;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.jelphabot.testutil.EditPersonDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditTaskDescriptor descriptorWithSameValues = new EditCommand.EditTaskDescriptor(DESC_LAB);
        assertEquals(DESC_LAB, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_LAB, DESC_LAB);

        // null -> returns false
        assertNotEquals(null, DESC_LAB);

        // different types -> returns false
        assertNotEquals(5, DESC_LAB);

        // different values -> returns false
        assertNotEquals(DESC_LAB, DESC_JOB);

        // different description -> returns false
        EditTaskDescriptor editedAssignment =
                new EditPersonDescriptorBuilder(DESC_LAB).withDescription(VALID_DESC_JOB).build();
        assertNotEquals(DESC_LAB, editedAssignment);

        // different module code -> returns false
        editedAssignment = new EditPersonDescriptorBuilder(DESC_LAB).withModuleCode(VALID_MODULE_CODE_JOB).build();
        assertNotEquals(DESC_LAB, editedAssignment);

        // different tags -> returns false
        editedAssignment = new EditPersonDescriptorBuilder(DESC_LAB).withTags(VALID_TAG_SCHOOL).build();
        assertNotEquals(DESC_LAB, editedAssignment);
    }
}
