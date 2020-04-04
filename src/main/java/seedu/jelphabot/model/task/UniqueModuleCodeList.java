package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.exceptions.DuplicateModuleCodeException;

/**
 * A list of module codes that enforces uniqueness between its elements and does not allow nulls.
 * Two ModuleCodes are considered equal if they have the same module code String.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameTask(Task)
 */
public class UniqueModuleCodeList implements Iterable<ModuleCode> {

    private final ObservableList<ModuleCode> internalList = FXCollections.observableArrayList();
    private final ObservableList<ModuleCode> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ModuleCode toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a ModuleCode to the list.
     * The ModuleCode must not already exist in the list.
     */
    public void add(ModuleCode toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleCodeException();
        }
        internalList.add(toAdd);
    }

    @Override
    public Iterator<ModuleCode> iterator() {
        return null;
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate ModuleCodes.
     */
    public void setModuleCodes(UniqueModuleCodeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setModuleCodes(List<Task> tasks) {
        requireAllNonNull(tasks);
        HashSet<ModuleCode> replacement = new HashSet<>();
        for (Task task : tasks) {
            replacement.add(task.getModuleCode());
        }
        internalList.setAll(replacement);
    }
}
