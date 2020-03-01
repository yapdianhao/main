package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.jelphabot.testutil.EditPersonDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditTaskDescriptor descriptorWithSameValues = new EditCommand.EditTaskDescriptor(DESC_AMY);
        assertEquals(DESC_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_AMY, DESC_AMY);

        // null -> returns false
        assertNotEquals(null, DESC_AMY);

        // different types -> returns false
        assertNotEquals(5, DESC_AMY);

        // different values -> returns false
        assertNotEquals(DESC_AMY, DESC_BOB);

        // different name -> returns false
        EditTaskDescriptor editedAmy =
                new EditPersonDescriptorBuilder(DESC_AMY).withDescription(VALID_NAME_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different module code -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withModuleCode(VALID_MODULE_CODE_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(DESC_AMY, editedAmy);
    }
}
