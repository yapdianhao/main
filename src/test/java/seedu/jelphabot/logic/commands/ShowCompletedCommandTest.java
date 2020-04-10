// @@author Eden
package seedu.jelphabot.logic.commands;

import static seedu.jelphabot.logic.commands.CommandTestUtil.showTasksWithSpecifiedStatus;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import org.junit.jupiter.api.BeforeEach;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.Status;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ShowCompletedCommand.
 */
public class ShowCompletedCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
        showTasksWithSpecifiedStatus(expectedModel, Status.COMPLETE);
    }

    // @Test
    // public void execute_listIsFilteredByPredicate_success() {
    //     showTasksWithSpecifiedStatus(model, Status.COMPLETE);
    //     assertCommandSuccess(new ShowCompletedCommand(), model, ShowCompletedCommand.MESSAGE_SUCCESS, expectedModel);
    // }

}
