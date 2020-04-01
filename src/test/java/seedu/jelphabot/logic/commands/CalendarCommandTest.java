package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code CalendarCommand}.
 */
public class CalendarCommandTest {
    private Model model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJelphaBot(), new UserPrefs());

    @Test
    public void execute_calendar_success() {
        CommandResult expectedCommandResult =
            new CommandResult(CalendarCommand.MESSAGE_SWITCH_PANEL_ACKNOWLEDGEMENT).isShowCalendar();
        assertCommandSuccess(new CalendarCommand(), model, expectedCommandResult, expectedModel);
    }

    //TODO can add comparison for YearMonth param
    @Test
    public void equals() {
        TaskDueWithinDayPredicate firstPredicate = new TaskDueWithinDayPredicate();
        LocalDate date = LocalDate.now().plusMonths(1).plusDays(1);
        TaskDueWithinDayPredicate secondPredicate = new TaskDueWithinDayPredicate(date);

        CalendarCommand calendarFirstCommand = new CalendarCommand(firstPredicate);
        CalendarCommand calendarSecondCommand = new CalendarCommand(secondPredicate);

        // same object -> returns true
        assertTrue(calendarFirstCommand.equals(calendarFirstCommand));

        // same values -> returns true
        CalendarCommand calendarFirstCommandCopy = new CalendarCommand(firstPredicate);
        assertTrue(calendarFirstCommand.equals(calendarFirstCommandCopy));

        // different types -> returns false
        assertFalse(calendarFirstCommand.equals(1));

        // null -> returns false
        assertFalse(calendarFirstCommand.equals(null));

        // different commands -> returns false
        assertFalse(calendarFirstCommand.equals(calendarSecondCommand));
    }
}
