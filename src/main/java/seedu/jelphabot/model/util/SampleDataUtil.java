package seedu.jelphabot.model.util;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code JelphaBot} with sample data.
 */
// TODO update with new sample data
public class SampleDataUtil {
    public static Task[] getSamplePersons() {
        return new Task[] {
            new Task(new Description("Alex Yeoh"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"), new ModuleCode("CS3230"),
                getTagSet("friends")),
            new Task(new Description("Bernice Yu"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"), new ModuleCode("ACC1101"),
                getTagSet("colleagues", "friends")),
            new Task(new Description("Charlotte Oliveiro"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"), new ModuleCode("ST2334"),
                getTagSet("neighbours")),
            new Task(new Description("David Li"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"), new ModuleCode("PF1103"),
                getTagSet("family")),
            new Task(new Description("Irfan Ibrahim"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"), new ModuleCode("MA1521"),
                getTagSet("classmates")),
            new Task(new Description("Roy Balakrishnan"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"), new ModuleCode("MA1101R"),
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
