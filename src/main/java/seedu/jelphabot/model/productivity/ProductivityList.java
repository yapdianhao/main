package seedu.jelphabot.model.productivity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.UniqueTaskList;

import java.util.Iterator;

public class ProductivityList implements Iterable<Productivity> {

    private final ObservableList<Productivity> internalList = FXCollections.observableArrayList();
    private final ObservableList<Productivity> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Productivity> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Productivity> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof UniqueTaskList // instanceof handles nulls
                           && internalList.equals(((ProductivityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
