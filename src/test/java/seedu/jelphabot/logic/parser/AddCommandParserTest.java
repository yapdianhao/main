package seedu.jelphabot.logic.parser;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.MODULE_CODE_DESC_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.MODULE_CODE_DESC_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.jelphabot.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.jelphabot.logic.commands.CommandTestUtil.TAG_DESC_UNGRADED;
import static seedu.jelphabot.logic.commands.CommandTestUtil.TAG_DESC_GRADED;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_UNGRADED;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.jelphabot.testutil.TypicalTasks.AMY;
import static seedu.jelphabot.testutil.TypicalTasks.BOB;

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
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_UNGRADED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_UNGRADED, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_ASSIGNMENT + DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_UNGRADED, new AddCommand(expectedTask));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_UNGRADED, new AddCommand(expectedTask));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_ASSIGNMENT + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_UNGRADED, new AddCommand(expectedTask));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_UNGRADED, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB).withTags(VALID_TAG_UNGRADED, VALID_TAG_GRADED)
                                            .build();
        assertParseSuccess(parser, DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_GRADED + TAG_DESC_UNGRADED, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, DESCRIPTION_DESC_ASSIGNMENT + MODULE_CODE_DESC_ASSIGNMENT,
            new AddCommand(expectedTask)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_DESCRIPTION_TUTORIAL + MODULE_CODE_DESC_TUTORIAL,
            expectedMessage
        );

        // missing phone prefix
        assertParseFailure(parser, DESCRIPTION_DESC_TUTORIAL + VALID_PHONE_BOB + MODULE_CODE_DESC_TUTORIAL,
            expectedMessage
        );

        // missing module code prefix
        assertParseFailure(parser, DESCRIPTION_DESC_TUTORIAL + VALID_MODULE_CODE_TUTORIAL,
            expectedMessage
        );

        // missing address prefix
        assertParseFailure(parser, DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL + VALID_ADDRESS_BOB,
            expectedMessage
        );

        // all prefixes missing
        assertParseFailure(parser, VALID_DESCRIPTION_TUTORIAL + VALID_PHONE_BOB + VALID_MODULE_CODE_TUTORIAL + VALID_ADDRESS_BOB,
            expectedMessage
        );
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_GRADED + TAG_DESC_UNGRADED, Description.MESSAGE_CONSTRAINTS);

        // invalid module code
        assertParseFailure(parser, DESCRIPTION_DESC_TUTORIAL + INVALID_MODULE_CODE_DESC
                                       + TAG_DESC_GRADED + TAG_DESC_UNGRADED, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL
                                       + INVALID_TAG_DESC + VALID_TAG_UNGRADED, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MODULE_CODE_DESC_TUTORIAL,
            Description.MESSAGE_CONSTRAINTS
        );

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_TUTORIAL + MODULE_CODE_DESC_TUTORIAL
                                       + TAG_DESC_GRADED + TAG_DESC_UNGRADED,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE)
        );
    }
}
