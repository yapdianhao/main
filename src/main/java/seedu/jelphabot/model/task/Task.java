package seedu.jelphabot.model.task;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.jelphabot.model.tag.Tag;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Description description;
    private final ModuleCode moduleCode;
    private final DateTime dateTime;
    private final Status status;
    private final Priority priority;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Description description, Status status, DateTime dateTime, ModuleCode moduleCode,
        Priority priority, Set<Tag> tags) {
        requireAllNonNull(description, status, dateTime, moduleCode, tags);
        this.description = description;
        this.status = status;
        this.dateTime = dateTime;
        this.moduleCode = moduleCode;
        this.priority = priority;
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

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns true if both tasks of the same description also occur at the same dateTime and have the same ModuleCode.
     * This defines a weaker notion of equality between two tasks.
     * Mainly used to check for duplicate tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                   && otherTask.getDescription().equals(getDescription())
                   && otherTask.getDateTime().equals(getDateTime())
                   && otherTask.getModuleCode().equals(getModuleCode());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     * Used to check for two instances of the same model entity.
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
                   && otherTask.getStatus().equals(getStatus())
                   && otherTask.getDateTime().equals(getDateTime())
                   && otherTask.getModuleCode().equals(getModuleCode())
                   && otherTask.getPriority().equals(getPriority())
                   && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, dateTime, moduleCode, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
            .append(" Status: ")
            .append(getStatus())
            .append(" DateTime: ")
            .append(getDateTime())
            .append(" ModuleCode: ")
            .append(getModuleCode())
            .append(" Priority: ")
            .append(getPriority())
            .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
