package seedu.jelphabot.testutil;

import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Phone;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_MODULE_CODE = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Description description;
    private Phone phone;
    private ModuleCode moduleCode;
    private Set<Tag> tags;

    public PersonBuilder() {
        description = new Description(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        phone = taskToCopy.getPhone();
        moduleCode = taskToCopy.getModuleCode();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.description = new Description(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.moduleCode = new ModuleCode(email);
        return this;
    }

    public Task build() {
        return new Task(description, phone, moduleCode, tags);
    }

}
