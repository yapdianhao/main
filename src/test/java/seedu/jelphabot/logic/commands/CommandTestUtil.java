package seedu.jelphabot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.jelphabot.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.jelphabot.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.jelphabot.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.DescriptionContainsKeywordsPredicate;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_DESC_JOB = "Work";
    public static final String VALID_DESC_LAB = "Laboratory";
    public static final String VALID_MODULE_CODE_JOB = "CP3200";
    public static final String VALID_MODULE_CODE_LAB = "CS2100";
    public static final String VALID_TAG_SCHOOL = "school";
    public static final String VALID_TAG_PROJECT = "project";

    public static final String NAME_DESC_JOB = " " + PREFIX_DESCRIPTION + VALID_DESC_JOB;
    public static final String NAME_DESC_LAB = " " + PREFIX_DESCRIPTION + VALID_DESC_LAB;
    public static final String MODULE_CODE_DESC_JOB = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_JOB;
    public static final String MODULE_CODE_DESC_LAB = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_LAB;
    public static final String TAG_DESC_SCHOOL = " " + PREFIX_TAG + VALID_TAG_SCHOOL;
    public static final String TAG_DESC_PROJECT = " " + PREFIX_TAG + VALID_TAG_PROJECT;

    // '&' not allowed in description
    public static final String INVALID_NAME_DESC = " " + PREFIX_DESCRIPTION + "James&";
    // prefix requires at least 2 characters
    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULE_CODE + "C2103T";
    // empty tag not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + ""; 

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_LAB;
    public static final EditCommand.EditTaskDescriptor DESC_JOB;

    static {
        DESC_LAB = new EditPersonDescriptorBuilder().withDescription(VALID_DESC_LAB).withTags(VALID_TAG_PROJECT).build();
        DESC_JOB = new EditPersonDescriptorBuilder().withDescription(VALID_DESC_JOB)
                .withTags(VALID_TAG_SCHOOL, VALID_TAG_PROJECT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)} that
     * takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered task list and selected task in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        JelphaBot expectedJelphaBot = new JelphaBot(actualModel.getJelphaBot());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedJelphaBot, actualModel.getJelphaBot());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given
     * {@code targetIndex} in the {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getDescription().fullDescription.split("\\s+");
        model.updateFilteredTaskList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
