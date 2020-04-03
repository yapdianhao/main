package seedu.jelphabot.model.task.tasklist;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Wrapper class for a ObservableList Tasks.
 */
public class SubgroupTaskList {
    private final String groupName;
    private final ObservableList<Task> taskList;
    private final IntegerBinding size;
    private final NumberBinding startIndex;

    SubgroupTaskList(String groupName, ObservableList<Task> taskList, NumberBinding startIndex) {
        this.groupName = groupName;
        this.taskList = taskList;
        this.size = Bindings.size(taskList);
        this.startIndex = startIndex;
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

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public NumberBinding sizeBinding() {
        return size;
    }

    public NumberBinding startIndexBinding() {
        return startIndex;
    }

    public void addListener(ListChangeListener<Task> deleteOnEmptyList) {
        taskList.addListener(deleteOnEmptyList);
    }
}
