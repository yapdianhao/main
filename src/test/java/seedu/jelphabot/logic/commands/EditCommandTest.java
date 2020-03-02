package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.testutil.EditTaskDescriptorBuilder;
import seedu.jelphabot.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and
 * RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder().build();
        EditCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new JelphaBot(model.getJelphaBot()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastPerson.getZeroBased());

        TaskBuilder personInList = new TaskBuilder(lastTask);
        Task editedTask = personInList.withDescription(VALID_DESCRIPTION_TUTORIAL).withTags(VALID_TAG_GRADED).build();

        EditCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_TUTORIAL)
                .withTags(VALID_TAG_GRADED).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new JelphaBot(model.getJelphaBot()), new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new JelphaBot(model.getJelphaBot()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_TUTORIAL).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new JelphaBot(model.getJelphaBot()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Task taskInList = model.getJelphaBot().getTaskList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list, but
     * smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJelphaBot().getTaskList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_TUTORIAL).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_ASSIGNMENT);

        // same values -> returns true
        EditCommand.EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_ASSIGNMENT);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        /* different index -> returns false */
        assertNotEquals(standardCommand, new EditCommand(INDEX_SECOND_PERSON, DESC_ASSIGNMENT));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST_PERSON, DESC_TUTORIAL));
    }

}
