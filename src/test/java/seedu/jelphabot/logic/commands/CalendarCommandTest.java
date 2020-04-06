package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.testutil.TypicalTasks.*;
import static seedu.jelphabot.testutil.TypicalTasks.FINALS;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.predicates.DescriptionContainsKeywordsPredicate;
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

    //TODO fix null pointer error next week for commented out tests
    @Test
    public void equals_for_predicateConstructor() {
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

    @Test
    public void equals_for_yearMonthConstructor() {

        YearMonth first = YearMonth.now();
        YearMonth second = YearMonth.parse("Mar-2020", DateTimeFormatter.ofPattern("MMM-uuuu"));

        CalendarCommand calendarFirstCommand = new CalendarCommand(first);
        CalendarCommand calendarSecondCommand = new CalendarCommand(second);

        // same object -> returns true
        assertTrue(calendarFirstCommand.equals(calendarFirstCommand));

        // same values -> returns true
        CalendarCommand calendarFirstCommandCopy = new CalendarCommand(first);
        assertTrue(calendarFirstCommand.equals(calendarFirstCommandCopy));

        // different types -> returns false
        assertFalse(calendarFirstCommand.equals(1));

        // null -> returns false
        assertFalse(calendarFirstCommand.equals(null));

        // different commands -> returns false
        assertFalse(calendarFirstCommand.equals(calendarSecondCommand));
    }

    @Test
    public void equals_for_bothConstructors() {

        TaskDueWithinDayPredicate firstPredicate = new TaskDueWithinDayPredicate();
        LocalDate date = LocalDate.now().plusMonths(1).plusDays(1);
        TaskDueWithinDayPredicate secondPredicate = new TaskDueWithinDayPredicate(date);

        YearMonth first = YearMonth.now();
        YearMonth second = YearMonth.parse("Mar-2020", DateTimeFormatter.ofPattern("MMM-uuuu"));

        CalendarCommand calendarFirstCommand = new CalendarCommand(firstPredicate, first);
        CalendarCommand calendarSecondCommand = new CalendarCommand(secondPredicate, second);

        // same object -> returns true
        assertTrue(calendarFirstCommand.equals(calendarFirstCommand));

        // same values -> returns true
        CalendarCommand calendarFirstCommandCopy = new CalendarCommand(firstPredicate, first);
        assertTrue(calendarFirstCommand.equals(calendarFirstCommandCopy));

        // different types -> returns false
        assertFalse(calendarFirstCommand.equals(1));

        // null -> returns false
        assertFalse(calendarFirstCommand.equals(null));

        // different commands -> returns false
        assertFalse(calendarFirstCommand.equals(calendarSecondCommand));
    }

    @Test
    public void execute_calendarDate_TodayTasksFound() {
        String expectedMessage = String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW,
            model.getFilteredCalendarTaskList().size());
        TaskDueWithinDayPredicate predicate = new TaskDueWithinDayPredicate(CLASS.getDateTime().getDate());
        CalendarCommand command = new CalendarCommand(predicate);
        expectedModel.updateFilteredCalendarTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ASSESSMENT, BOOK_REPORT, CLASS, DATE, ERRAND, FINALS, GROUP_WORK), model.getFilteredCalendarTaskList());
    }
}
