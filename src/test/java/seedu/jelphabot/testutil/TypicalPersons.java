package seedu.jelphabot.testutil;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.jelphabot.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
// TODO rewrite needed
public class TypicalPersons {

    public static final Task ALICE = new TaskBuilder().withName("Alice Pauline")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withName("Carl Kurz")
            .withModuleCode("heinz@example.com").build();
    public static final Task DANIEL = new TaskBuilder().withName("Daniel Meier")
            .withModuleCode("cornelia@example.com").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withName("Elle Meyer")
            .withModuleCode("werner@example.com").build();
    public static final Task FIONA = new TaskBuilder().withName("Fiona Kunz")
            .withModuleCode("lydia@example.com").build();
    public static final Task GEORGE = new TaskBuilder().withName("George Best")
            .withModuleCode("anna@example.com").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier")
            .withModuleCode("stefan@example.com").build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller")
            .withModuleCode("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder()
            .withName(VALID_NAME_AMY)
            .withModuleCode(VALID_MODULE_CODE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB)
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
