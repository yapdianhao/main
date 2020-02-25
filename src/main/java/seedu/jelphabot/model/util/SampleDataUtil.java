package seedu.jelphabot.model.util;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Phone;
import seedu.jelphabot.model.task.Task;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code JelphaBot} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSamplePersons() {
        return new Task[] {
            new Task(new Description("Alex Yeoh"), new Phone("87438807"), new ModuleCode("alexyeoh@example.com"),
                getTagSet("friends")),
            new Task(new Description("Bernice Yu"), new Phone("99272758"), new ModuleCode("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Task(new Description("Charlotte Oliveiro"), new Phone("93210283"), new ModuleCode("charlotte@example.com"),
                getTagSet("neighbours")),
            new Task(new Description("David Li"), new Phone("91031282"), new ModuleCode("lidavid@example.com"),
                getTagSet("family")),
            new Task(new Description("Irfan Ibrahim"), new Phone("92492021"), new ModuleCode("irfan@example.com"),
                getTagSet("classmates")),
            new Task(new Description("Roy Balakrishnan"), new Phone("92624417"), new ModuleCode("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyJelphaBot getSampleJelphaBot() {
        JelphaBot sampleAb = new JelphaBot();
        for (Task sampleTask : getSamplePersons()) {
            sampleAb.addPerson(sampleTask);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
