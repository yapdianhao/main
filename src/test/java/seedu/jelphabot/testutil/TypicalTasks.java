package seedu.jelphabot.testutil;

import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_UNGRADED;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
// TODO rewrite needed
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withDescription("Alice Pauline")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withDescription("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withDescription("Carl Kurz")
            .withModuleCode("heinz@example.com").build();
    public static final Task DANIEL = new TaskBuilder().withDescription("Daniel Meier")
            .withModuleCode("cornelia@example.com").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withDescription("Elle Meyer")
            .withModuleCode("werner@example.com").build();
    public static final Task FIONA = new TaskBuilder().withDescription("Fiona Kunz")
            .withModuleCode("lydia@example.com").build();
    public static final Task GEORGE = new TaskBuilder().withDescription("George Best")
            .withModuleCode("anna@example.com").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withDescription("Hoon Meier")
            .withModuleCode("stefan@example.com").build();
    public static final Task IDA = new TaskBuilder().withDescription("Ida Mueller")
            .withModuleCode("hans@example.com").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder()
            .withDescription(VALID_DESCRIPTION_ASSIGNMENT)
            .withModuleCode(VALID_MODULE_CODE_ASSIGNMENT)
            .withTags(VALID_TAG_UNGRADED).build();
    public static final Task BOB = new TaskBuilder().withDescription(VALID_DESCRIPTION_TUTORIAL)
            .withModuleCode(VALID_MODULE_CODE_TUTORIAL)
            .withTags(VALID_TAG_GRADED, VALID_TAG_UNGRADED)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code JelphaBot} with all the typical persons.
     */
    public static JelphaBot getTypicalJelphaBot() {
        JelphaBot ab = new JelphaBot();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
