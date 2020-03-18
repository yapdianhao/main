package seedu.jelphabot.logic.commands;

import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.ReminderPredicate;

public class ReminderCommandTest {

    private Model model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJelphaBot(), new UserPrefs());


    @Test
    public void execute_ListIsFilteredByReminderSuccess() {
        ReminderPredicate predicate = new ReminderPredicate();
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(new ReminderCommand(), model, ReminderCommand.MESSAGE_URGENT_TASKS, expectedModel);
    }

}