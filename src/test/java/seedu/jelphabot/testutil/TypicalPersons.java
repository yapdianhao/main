package seedu.jelphabot.testutil;

import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_AMY;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.task.Task;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
// TODO rewrite needed
public class TypicalPersons {

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

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder()
            .withDescription(VALID_NAME_AMY)
            .withModuleCode(VALID_MODULE_CODE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withDescription(VALID_NAME_BOB)
            .withModuleCode(VALID_MODULE_CODE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code JelphaBot} with all the typical persons.
     */
    public static JelphaBot getTypicalJelphaBot() {
        JelphaBot ab = new JelphaBot();
        for (Task task : getTypicalPersons()) {
            ab.addPerson(task);
        }
        return ab;
    }

    public static List<Task> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
