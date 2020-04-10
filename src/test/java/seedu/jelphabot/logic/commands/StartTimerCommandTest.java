package seedu.jelphabot.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.Task;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and
 * RedoCommand) and unit tests for {@code StartTimerCommand}.
 */
class StartTimerCommandTest {

    private Model model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());

    // TODO: fix test
    // @Test
    // public void execute_validIndexUnfilteredList_success() {
    //     Task taskToStart = model.getLastShownList().get(INDEX_FIRST_TASK.getZeroBased());
    //     StartTimerCommand StartTimerCommand = new StartTimerCommand(INDEX_FIRST_TASK);
    //     Task startedTask = model.getLastShownList().get(INDEX_FIRST_TASK.getZeroBased());
    //     startedTask.startTimer();
    //
    //     String expectedMessage = String.format(StartTimerCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getZeroBased(),
    //         taskToStart.getModuleCode().toString(), taskToStart.getDescription().toString());
    //
    //     ModelManager expectedModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
    //     expectedModel.setTask(taskToStart, startedTask);
    //
    //     assertCommandSuccess(StartTimerCommand, model, expectedMessage, expectedModel);
    // }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getLastShownList().size() + 1);
        StartTimerCommand StartTimerCommand = new StartTimerCommand(outOfBoundIndex);

        assertCommandFailure(StartTimerCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    // TODO: fix test
    // @Test
    // public void execute_validIndexFilteredList_success() {
    //     showTaskAtIndex(model, INDEX_FIRST_TASK);
    //
    //     Task taskToStart = model.getLastShownList().get(INDEX_FIRST_TASK.getZeroBased());
    //     StartTimerCommand StartTimerCommand = new StartTimerCommand(INDEX_FIRST_TASK);
    //     Task startedTask = model.getLastShownList().get(INDEX_FIRST_TASK.getZeroBased());
    //     startedTask.startTimer();
    //
    //     String expectedMessage = String.format(StartTimerCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getZeroBased(),
    //         taskToStart.getModuleCode().toString(), taskToStart.getDescription().toString());
    //
    //     Model expectedModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
    //     expectedModel.setTask(taskToStart, startedTask);
    //     showNoTask(expectedModel);
    //
    //     assertCommandSuccess(StartTimerCommand, model, expectedMessage, expectedModel);
    // }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_THIRD_TASK;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJelphaBot().getTaskList().size());

        StartTimerCommand StartTimerCommand = new StartTimerCommand(outOfBoundIndex);

        assertCommandFailure(StartTimerCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        StartTimerCommand startFirstTimerCommand = new StartTimerCommand(INDEX_FIRST_TASK);
        StartTimerCommand startSecondTimerCommand = new StartTimerCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(startFirstTimerCommand.equals(startFirstTimerCommand));

        // same values -> returns true
        StartTimerCommand startFirstCommandCopy = new StartTimerCommand(INDEX_FIRST_TASK);
        assertTrue(startFirstTimerCommand.equals(startFirstCommandCopy));

        // different types -> returns false
        assertFalse(startFirstTimerCommand.equals(1));

        // null -> returns false
        assertFalse(startFirstTimerCommand.equals(null));

        // different commands -> returns false
        assertFalse(startFirstTimerCommand.equals(startSecondTimerCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}