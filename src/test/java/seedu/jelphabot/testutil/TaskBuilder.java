package seedu.jelphabot.testutil;

import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.*;
import seedu.jelphabot.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Person objects.
 */
// TODO rewrite class
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_MODULE_CODE = "CS2103";

    private Description description;
    private ModuleCode moduleCode;
    private Set<Tag> tags;

    public TaskBuilder() {
        description = new Description(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        moduleCode = taskToCopy.getModuleCode();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.description = new Description(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public TaskBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    // /**
    // * Sets the {@code Phone} of the {@code Person} that we are building.
    // */
    // public PersonBuilder withPhone(String phone) {
    // this.phone = new Phone(phone);
    // return this;
    // }

    /**
     * Sets the {@code ModuleCode} of the {@code Person} that we are building.
     */
    public TaskBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    public Task build() {
        return new Task(description,  Status.INCOMPLETE, new DateTime("Jan-20-2020 01 20"), moduleCode, tags);
    }

}
