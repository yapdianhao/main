package seedu.jelphabot.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Priority;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;

/**
 * Contains utility methods for populating {@code JelphaBot} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[]{
            new Task(new Description("Individual Assignment 1"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"),
                new ModuleCode("CS3230"), Priority.LOW, getTagSet("friends")
            ),
            new Task(new Description("Tutorial 3"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"),
                new ModuleCode("ACC1101"), Priority.LOW, getTagSet("colleagues", "friends")
            ),
            new Task(new Description("MidTerm Revision Papers"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"),
                new ModuleCode("ST2334"), Priority.MEDIUM, getTagSet("neighbours")
            ),
            new Task(new Description("Consultation with David Li"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"),
                new ModuleCode("PF1103"), Priority.LOW, getTagSet("family")
            ),
            new Task(new Description("Tutorial 4"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"),
                new ModuleCode("MA1521"), Priority.LOW, getTagSet("classmates")
            ),
            new Task(new Description("Graded Homework 3"), Status.INCOMPLETE, new DateTime("Jan-1-2020 22 00"),
                new ModuleCode("MA1101R"), Priority.HIGH, getTagSet("colleagues")
            ),
            new Task(new Description("Lab 1"), Status.INCOMPLETE, new DateTime("1/1/2020 12 00"),
                new ModuleCode("CN1103"), Priority.HIGH, getTagSet("schoolwork")
            ),
            new Task(new Description("Assignment 3"), Status.INCOMPLETE, new DateTime("1-May-2020 11 11"),
                new ModuleCode("PC1232"), Priority.MEDIUM, getTagSet("schoolwork")
            ),
            new Task(new Description("Readings"), Status.INCOMPLETE, new DateTime("May/1/2020 22 00"),
                new ModuleCode("EC1103"), Priority.MEDIUM, getTagSet("readings")
            )
        };
    }

    public static ReadOnlyJelphaBot getSampleJelphaBot() {
        JelphaBot sampleAb = new JelphaBot();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
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
