package seedu.jelphabot.logic.parser;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.MODULE_CODE_DESC_LAB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.MODULE_CODE_DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.NAME_DESC_LAB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.NAME_DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.jelphabot.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.jelphabot.logic.commands.CommandTestUtil.TAG_DESC_SCHOOL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.TAG_DESC_PROJECT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_SCHOOL;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.jelphabot.testutil.TypicalTasks.LAB;
import static seedu.jelphabot.testutil.TypicalTasks.JOB;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.logic.commands.AddCommand;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.testutil.TaskBuilder;

// TODO rewrite required to replace missing fields
public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(JOB).withTags(VALID_TAG_PROJECT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JOB + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_SCHOOL, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_LAB + NAME_DESC_JOB + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_SCHOOL, new AddCommand(expectedTask));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_JOB + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_SCHOOL, new AddCommand(expectedTask));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, NAME_DESC_JOB + MODULE_CODE_DESC_LAB + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_SCHOOL, new AddCommand(expectedTask));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_JOB + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_SCHOOL, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(JOB).withTags(VALID_TAG_PROJECT, VALID_TAG_SCHOOL)
                                            .build();
        assertParseSuccess(parser, NAME_DESC_JOB + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_PROJECT + TAG_DESC_SCHOOL, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(LAB).withTags().build();
        assertParseSuccess(parser, NAME_DESC_LAB + MODULE_CODE_DESC_LAB,
            new AddCommand(expectedTask)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_DESC_JOB + MODULE_CODE_DESC_JOB + TAG_DESC_SCHOOL,
            expectedMessage
        );

        // missing module code prefix
        assertParseFailure(parser, NAME_DESC_JOB + VALID_MODULE_CODE_JOB + TAG_DESC_SCHOOL,
            expectedMessage
        );

        // missing tag prefix
        assertParseFailure(parser, NAME_DESC_JOB + MODULE_CODE_DESC_JOB + VALID_TAG_PROJECT,
            expectedMessage
        );

        // all prefixes missing
        assertParseFailure(parser, VALID_DESC_JOB + VALID_MODULE_CODE_JOB + VALID_TAG_PROJECT,
            expectedMessage
        );
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_PROJECT + TAG_DESC_SCHOOL, Description.MESSAGE_CONSTRAINTS);

        // invalid module code
        assertParseFailure(parser, NAME_DESC_JOB + INVALID_MODULE_CODE_DESC
                                       + TAG_DESC_PROJECT + TAG_DESC_SCHOOL, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_JOB + MODULE_CODE_DESC_JOB
                                       + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_MODULE_CODE_DESC,
            Description.MESSAGE_CONSTRAINTS
        );

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_JOB + MODULE_CODE_DESC_JOB
                                       + TAG_DESC_PROJECT + TAG_DESC_SCHOOL,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE)
        );
    }
}
