package seedu.jelphabot.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.jelphabot.logic.commands.AddCommand;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.testutil.TaskBuilder;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.*;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.jelphabot.testutil.TypicalTasks.JOB;
import static seedu.jelphabot.testutil.TypicalTasks.LAB;

// TODO rewrite required to replace missing fields
public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(JOB).withTags(VALID_TAG_PROJECT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_GRADED, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_TUTORIAL + DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_GRADED, new AddCommand(expectedTask));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_GRADED, new AddCommand(expectedTask));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_TUTORIAL + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_GRADED, new AddCommand(expectedTask));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_GRADED, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(JOB).withTags(VALID_TAG_PROJECT, VALID_TAG_GRADED)
                                            .build();
        assertParseSuccess(parser, DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_PROJECT + TAG_DESC_GRADED, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(LAB).withTags().build();
        assertParseSuccess(parser, DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL,
            new AddCommand(expectedTask)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_DESCRIPTION_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT + TAG_DESC_GRADED,
            expectedMessage
        );

        // missing module code prefix
        assertParseFailure(parser, DESCRIPTION_DESC_ASSIGNMENT + VALID_MODULE_CODE_ASSIGNMENT + TAG_DESC_GRADED,
            expectedMessage
        );

        // missing tag prefix
        assertParseFailure(parser, DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT + VALID_TAG_PROJECT,
            expectedMessage
        );

        // all prefixes missing
        assertParseFailure(parser, VALID_DESCRIPTION_ASSIGNMENT + VALID_MODULE_CODE_ASSIGNMENT + VALID_TAG_PROJECT,
            expectedMessage
        );
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_PROJECT + TAG_DESC_GRADED, Description.MESSAGE_CONSTRAINTS);

        // invalid module code
        assertParseFailure(parser, DESCRIPTION_DESC_ASSIGNMENT + INVALID_MODULE_CODE_DESC
                                       + TAG_DESC_PROJECT + TAG_DESC_GRADED, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT
                                       + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_MODULE_CODE_DESC,
            Description.MESSAGE_CONSTRAINTS
        );

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT
                                       + TAG_DESC_PROJECT + TAG_DESC_GRADED,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE)
        );
    }
}
