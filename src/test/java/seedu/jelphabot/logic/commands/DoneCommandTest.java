package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.jelphabot.logic.commands.DoneCommand.createDoneTask;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.Task;

public class DoneCommandTest {
    private Model model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMarkDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);
        Task doneTask = createDoneTask(taskToMarkDone);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_TASK_COMPLETE_SUCCESS, doneTask);

        ModelManager expectedModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
        expectedModel.setTask(taskToMarkDone, doneTask);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToMarkDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);
        Task doneTask = createDoneTask(taskToMarkDone);

        String expectedMessage = String.format(DoneCommand.MESSAGE_MARK_TASK_COMPLETE_SUCCESS, doneTask);

        Model expectedModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
        expectedModel.setTask(taskToMarkDone, doneTask);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of the task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJelphaBot().getTaskList().size());

        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_taskAlreadyCompletedUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task doneTask = createDoneTask(firstTask);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);
        Model newModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
        newModel.setTask(firstTask, doneTask);

        assertCommandFailure(doneCommand, newModel, DoneCommand.MESSAGE_TASK_ALREADY_MARKED_COMPLETE);

    }

    @Test
    public void execute_taskAlreadyCompletedFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInList = model.getJelphaBot().getTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task doneTask = createDoneTask(taskInList);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);
        Model newModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
        newModel.setTask(taskInList, doneTask);

        assertCommandFailure(doneCommand, newModel, DoneCommand.MESSAGE_TASK_ALREADY_MARKED_COMPLETE);
    }

    @Test
    public void equals() {
        DoneCommand firstDoneCommand = new DoneCommand(INDEX_FIRST_TASK);
        DoneCommand secondDoneCommand = new DoneCommand(INDEX_SECOND_TASK);

        // same object returns true
        assertTrue(firstDoneCommand.equals(firstDoneCommand));

        // same values returns true
        DoneCommand firstDoneCommandCopy = new DoneCommand(INDEX_FIRST_TASK);
        assertTrue(firstDoneCommand.equals(firstDoneCommandCopy));

        // different types returns false
        assertFalse(firstDoneCommand.equals(1));

        // null returns false
        assertFalse(firstDoneCommand.equals(null));

        // different commands returns false
        assertFalse(firstDoneCommand.equals(secondDoneCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
