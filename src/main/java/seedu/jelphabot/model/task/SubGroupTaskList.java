package seedu.jelphabot.model.task;

import javafx.collections.ObservableList;

/**
 * Wrapper class for a ObservableList Tasks.
 */
public class SubGroupTaskList {
    private final String groupName;
    private final ObservableList<Task> taskList;

    SubGroupTaskList(String groupName, ObservableList<Task> taskList) {
        this.groupName = groupName;
        this.taskList = taskList;
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
        return taskList.size();
    }
}
