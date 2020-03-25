package seedu.jelphabot.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Priority;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Default Task 1";
    public static final String DEFAULT_STATUS = "INCOMPLETE";
    public static final String DEFAULT_DATETIME = "May-1-2020 00 01";
    public static final String DEFAULT_MODULE_CODE = "TES1000";
    public static final String DEFAULT_PRIORITY = "0";

    private Description description;
    private Status status;
    private DateTime dateTime;
    private ModuleCode moduleCode;
    private Priority priority;
    private Set<Tag> tags;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TaskBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        status = Status.toStatus(DEFAULT_STATUS);
        dateTime = new DateTime(DEFAULT_DATETIME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        priority = Priority.toPriority(DEFAULT_PRIORITY);
        tags = new HashSet<>();
        startTime = LocalDateTime.MAX;
        endTime = LocalDateTime.MAX;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        status = taskToCopy.getStatus();
        dateTime = taskToCopy.getDateTime();
        moduleCode = taskToCopy.getModuleCode();
        priority = taskToCopy.getPriority();
        tags = new HashSet<>(taskToCopy.getTags());
        startTime = taskToCopy.getStartTime();
        endTime = taskToCopy.getEndTime();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String name) {
        this.description = new Description(name);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(String status) {
        this.status = Status.toStatus(status);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Task} that we are building.
     */
    public TaskBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Task} that we are building.
     */
    public TaskBuilder withPriority(String priority) {
        this.priority = Priority.toPriority(priority);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Task} that we are building.
     */
    public TaskBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Task build() {
        return new Task(description, status, dateTime, moduleCode, priority, tags, startTime, endTime);
    }

}
