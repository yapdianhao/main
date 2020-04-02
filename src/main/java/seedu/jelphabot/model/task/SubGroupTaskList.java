package seedu.jelphabot.model.task;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.collections.ObservableList;

/**
 * Wrapper class for a ObservableList Tasks.
 */
public class SubGroupTaskList {
    private final String groupName;
    private final ObservableList<Task> taskList;
    private final IntegerBinding size;
    private final NumberBinding startingIndex;

    SubGroupTaskList(String groupName, ObservableList<Task> taskList, NumberBinding startingIndex) {
        this.groupName = groupName;
        this.taskList = taskList;
        this.size = Bindings.size(taskList);
        this.startingIndex = startingIndex;
    }

    public ObservableList<Task> getList() {
        return taskList;
    }

    public String getGroupName() {
        return groupName;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return size.intValue();
    }

    public NumberBinding sizeBinding() {
        return size;
    }
}
