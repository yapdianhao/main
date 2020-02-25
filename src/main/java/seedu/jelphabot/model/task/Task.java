package seedu.jelphabot.model.task;

import seedu.jelphabot.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Description description;
    private final Phone phone;
    private final ModuleCode moduleCode;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Description description, Phone phone, ModuleCode moduleCode, Set<Tag> tags) {
        requireAllNonNull(description, phone, moduleCode, tags);
        this.description = description;
        this.phone = phone;
        this.moduleCode = moduleCode;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return description;
    }

    public Phone getPhone() {
        return phone;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same description have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription())
                && (otherTask.getPhone().equals(getPhone()) || otherTask.getModuleCode().equals(getModuleCode()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getPhone().equals(getPhone())
                && otherTask.getModuleCode().equals(getModuleCode())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, phone, moduleCode, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" Phone: ")
                .append(getPhone())
                .append(" ModuleCode: ")
                .append(getModuleCode())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
