package seedu.jelphabot.model.util;

import static seedu.jelphabot.model.util.SampleDateUtil.FIVE_DAYS_LATER;
import static seedu.jelphabot.model.util.SampleDateUtil.LAST_NIGHT;
import static seedu.jelphabot.model.util.SampleDateUtil.LAST_WEEK;
import static seedu.jelphabot.model.util.SampleDateUtil.NEXT_MONTH;
import static seedu.jelphabot.model.util.SampleDateUtil.NEXT_WEEK;
import static seedu.jelphabot.model.util.SampleDateUtil.TODAY_MORNING;
import static seedu.jelphabot.model.util.SampleDateUtil.TONIGHT;

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
            new Task(new Description("Individual Assignment 1"), Status.INCOMPLETE, new DateTime(LAST_NIGHT),
                new ModuleCode("CS3230"), Priority.HIGH, getTagSet("graded")
            ),
            new Task(new Description("Tutorial 3"), Status.INCOMPLETE, new DateTime(LAST_WEEK),
                new ModuleCode("ACC1101"), Priority.LOW, getTagSet("classpart", "SU-able")
            ),
            new Task(new Description("MidTerm Revision Papers"), Status.INCOMPLETE, new DateTime(NEXT_MONTH),
                new ModuleCode("ST2334"), Priority.MEDIUM, getTagSet("exam")
            ),
            new Task(new Description("Consultation with David Li"), Status.INCOMPLETE, new DateTime(NEXT_WEEK),
                new ModuleCode("PF1103"), Priority.MEDIUM, getTagSet("consult", "prepare")
            ),
            new Task(new Description("Tutorial 4"), Status.INCOMPLETE, new DateTime(TODAY_MORNING),
                new ModuleCode("ACC1101"), Priority.LOW, getTagSet("classpart", "Su-able")
            ),
            new Task(new Description("Graded Homework 3"), Status.INCOMPLETE, new DateTime(FIVE_DAYS_LATER),
                new ModuleCode("MA1101R"), Priority.HIGH, getTagSet("graded", "SU-able")
            ),
            new Task(new Description("Lab 1"), Status.INCOMPLETE, new DateTime(FIVE_DAYS_LATER),
                new ModuleCode("MA1101R"), Priority.LOW, getTagSet("ungraded", "SU-able")
            ),
            new Task(new Description("Assignment 3"), Status.INCOMPLETE, new DateTime(TONIGHT),
                new ModuleCode("ST2334"), Priority.MEDIUM, getTagSet("schoolwork")
            ),
            new Task(new Description("Readings"), Status.INCOMPLETE, new DateTime(NEXT_MONTH),
                new ModuleCode("EC1103"), Priority.MEDIUM, getTagSet("readings", "SU-able")
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
