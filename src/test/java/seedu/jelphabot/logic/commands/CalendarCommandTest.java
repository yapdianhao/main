package seedu.jelphabot.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;

import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.logic.commands.CalendarCommand.MESSAGE_SWITCH_PANEL_ACKNOWLEDGEMENT;

public class CalendarCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_calendar_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SWITCH_PANEL_ACKNOWLEDGEMENT, false,
            false, false, true);
        assertCommandSuccess(new CalendarCommand(), model, expectedCommandResult, expectedModel);
    }
}
