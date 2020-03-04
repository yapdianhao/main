package seedu.jelphabot.testutil;

import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ASSIGNMENT = new TaskBuilder().withDescription("Assignment 1").withModuleCode("MA1101R")
                                              .withDateTime("Feb-1-2020 11 00").withTags("school").build();
    public static final Task BOOK_REPORT = new TaskBuilder().withDescription("Book Report 1").withModuleCode("GES1028")
                                               .withDateTime("Feb-1-2020 11 00").withTags("project", "school").build();
    public static final Task CLASS = new TaskBuilder().withDescription("Yoga class")
                                         .withDateTime("Feb-1-2020 11 00").withModuleCode("YG1011").build();
    public static final Task DATE = new TaskBuilder().withDescription("Daniel Meier").withDateTime("Feb-1-2020 11 00")
                                        .withModuleCode("DT1101").build();
    public static final Task ERRAND = new TaskBuilder().withDescription("Milk errand").withModuleCode("MYB1101")
                                          .withDateTime("Feb-1-2020 11 00").build();
    public static final Task FINALS = new TaskBuilder().withDescription("Open book finals").withModuleCode("CS3230")
                                          .withTags("school").withDateTime("Feb-1-2020 11 00").build();
    public static final Task GROUP_WORK = new TaskBuilder().withDescription("Group project").withModuleCode("CS2103T")
                                              .withDateTime("Feb-1-2020 11 00").withTags("project", "school").build();

    // Manually added
    public static final Task HOMEWORK = new TaskBuilder().withDescription("Homework 1")
            .withModuleCode("CS3230").build();
    public static final Task INTERNSHIP = new TaskBuilder().withDescription("Internship at Google")
            .withModuleCode("CP3202").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task JOB = new TaskBuilder().withDescription(VALID_DESCRIPTION_ASSIGNMENT)
                                       .withModuleCode(VALID_MODULE_CODE_ASSIGNMENT)
                                       .withTags(VALID_TAG_GRADED, VALID_TAG_PROJECT).build();
    public static final Task LAB = new TaskBuilder().withDescription(VALID_DESCRIPTION_TUTORIAL)
                                       .withModuleCode(VALID_MODULE_CODE_TUTORIAL)
                                       .withTags(VALID_TAG_PROJECT).build();

    public static final String KEYWORD_MATCHING_TUTORIAL = "Tutorial"; // A keyword that matches TUTORIAL

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
        return new ArrayList<>(Arrays.asList(ASSIGNMENT, BOOK_REPORT, CLASS, DATE, ERRAND, FINALS, GROUP_WORK));
    }
}
