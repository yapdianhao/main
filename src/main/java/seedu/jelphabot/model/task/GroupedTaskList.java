package seedu.jelphabot.model.task;

import javafx.collections.ObservableList;

/**
 * Interface representing a TaskList which is split into sub-groups by predefined groups.
 * Each 'GroupedTaskList' is a container for `ObservableList<Task>` objects,
 * each containing a unique filter over the full task list.
 * Classes which extend GroupedTaskList are expected to provide a getter method for each grouping defined.
 */
public interface GroupedTaskList {
    /**
     * The intended method through which instances of GroupedList are retrieved.
     * @param taskList the full taskList returned from {#code JelphaBot.uniqueTaskList}.
     * @return The corresponding GroupedTaskList for that grouping.
     */
    public ObservableList<Task> getGroupedList(ObservableList<Task> taskList);
}

// TODO fill an enum that defines existing groupings.
