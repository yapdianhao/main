package seedu.jelphabot.logic.parser;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.jelphabot.logic.commands.CommandTestUtil.MODULE_CODE_DESC_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.MODULE_CODE_DESC_LAB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.NAME_DESC_LAB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.TAG_DESC_PROJECT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.TAG_DESC_SCHOOL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESC_LAB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_JOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_LAB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_SCHOOL;
import static seedu.jelphabot.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.jelphabot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.jelphabot.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.EditCommand;
import seedu.jelphabot.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.testutil.EditPersonDescriptorBuilder;

// TODO rewrite in order: Description, Status, DateTime, ModuleCode, Priority, Set<Tag> tags
public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESC_LAB, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_LAB, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_LAB, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Description.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC,
            ModuleCode.MESSAGE_CONSTRAINTS); // invalid module code

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_SCHOOL + TAG_DESC_PROJECT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_SCHOOL + TAG_EMPTY + TAG_DESC_PROJECT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_SCHOOL + TAG_DESC_PROJECT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_MODULE_CODE_DESC + INVALID_TAG_DESC,
            Description.MESSAGE_CONSTRAINTS
        );
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput =
            targetIndex.getOneBased() + TAG_DESC_PROJECT + MODULE_CODE_DESC_LAB + NAME_DESC_LAB
                + TAG_DESC_SCHOOL;

        EditCommand.EditTaskDescriptor descriptor =
            new EditPersonDescriptorBuilder().withDescription(VALID_DESC_LAB).withModuleCode(VALID_MODULE_CODE_LAB)
                .withTags(VALID_TAG_SCHOOL, VALID_TAG_PROJECT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_LAB;

        EditTaskDescriptor descriptor =
            new EditPersonDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_LAB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_LAB;
        EditCommand.EditTaskDescriptor descriptor = new EditPersonDescriptorBuilder().withDescription(VALID_DESC_LAB)
                                                        .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module code
        userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_LAB;
        descriptor = new EditPersonDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_LAB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_SCHOOL;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_PROJECT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput =
            targetIndex.getOneBased() + MODULE_CODE_DESC_LAB + TAG_DESC_SCHOOL
                + MODULE_CODE_DESC_LAB + TAG_DESC_SCHOOL
                + MODULE_CODE_DESC_JOB + TAG_DESC_PROJECT;

        EditTaskDescriptor descriptor = new EditPersonDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_JOB)
                                            .withTags(VALID_TAG_PROJECT, VALID_TAG_SCHOOL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + "";
        EditCommand.EditTaskDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_JOB;
        descriptor = new EditPersonDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_JOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditTaskDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
