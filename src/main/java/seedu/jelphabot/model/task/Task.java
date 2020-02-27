package seedu.jelphabot.model.task;

import seedu.jelphabot.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Description description;
    private final ModuleCode moduleCode;
    // TODO properly define status and dateTime
    private final Status status;
    private final DateTime dateTime;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Description description, Status status, DateTime dateTime, ModuleCode moduleCode, Set<Tag> tags) {
        requireAllNonNull(description, status, dateTime, moduleCode, tags);
        this.description = description;
        this.status = status;
        this.dateTime = dateTime;
        this.moduleCode = moduleCode;
    }


    public Description getDescription() {
        return description;
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

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns true if both persons of the same description have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getModuleCode().equals(getModuleCode())
                && otherTask.getDateTime().equals(getDateTime());
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
                && otherTask.getModuleCode().equals(getModuleCode())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, moduleCode, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" ModuleCode: ")
                .append(getModuleCode())
                .append(" DateTime: ")
                .append(getDateTime())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
