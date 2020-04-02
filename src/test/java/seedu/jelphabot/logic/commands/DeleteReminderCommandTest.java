package seedu.jelphabot.logic.commands;

//import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
//import seedu.jelphabot.model.reminder.Reminder;

public class DeleteReminderCommandTest {

    private Model model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredReminder_success() {
        //Reminder reminderToDelete = model.getFilteredReminderList().get(INDEX_FIRST_TASK.getZeroBased());
        //DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_FIRST_TASK);
        //String expectedMessage = String.format(DeleteReminderCommand.MESSAGE_DELETE_REMINDER_SUCCESS,
        //INDEX_FIRST_TASK.getOneBased());
        //ModelManager expectedModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
        //expectedModel.deleteReminder(reminderToDelete);
        //assertCommandSuccess(deleteReminderCommand, model, expectedMessage, expectedModel);
    }
}
