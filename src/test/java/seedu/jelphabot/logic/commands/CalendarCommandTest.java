package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;
import seedu.jelphabot.ui.CalendarPanel;
import seedu.jelphabot.ui.MainWindow;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code CalendarCommand}.
 */
public class CalendarCommandTest {
    private Model model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJelphaBot(), new UserPrefs());
    private CalendarPanel calendarPanel = MainWindow.getCalendarPanel();

    @Test
    public void execute_calendar_success() {
        CommandResult expectedCommandResult = new CommandResult(CalendarCommand.MESSAGE_SWITCH_PANEL_ACKNOWLEDGEMENT,
            false, false, false, true, false);
        assertCommandSuccess(new CalendarCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        TaskDueWithinDayPredicate firstPredicate = new TaskDueWithinDayPredicate();
        DateFormat format = new SimpleDateFormat("MMM-d-yyyy");
        Date date = null;
        try {
            date = format.parse("jan-1-2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TaskDueWithinDayPredicate secondPredicate = new TaskDueWithinDayPredicate(date);

        CalendarCommand calendarFirstCommand = new CalendarCommand(firstPredicate, calendarPanel);
        CalendarCommand calendarSecondCommand = new CalendarCommand(secondPredicate, calendarPanel);

        // same object -> returns true
        assertTrue(calendarFirstCommand.equals(calendarFirstCommand));

        // same values -> returns true
        CalendarCommand calendarFirstCommandCopy = new CalendarCommand(firstPredicate, calendarPanel);
        assertTrue(calendarFirstCommand.equals(calendarFirstCommandCopy));

        // different types -> returns false
        assertFalse(calendarFirstCommand.equals(1));

        // null -> returns false
        assertFalse(calendarFirstCommand.equals(null));

        // different commands -> returns false
        assertFalse(calendarFirstCommand.equals(calendarSecondCommand));
    }
}
