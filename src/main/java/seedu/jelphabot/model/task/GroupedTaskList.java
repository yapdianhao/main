package seedu.jelphabot.model.task;

import java.util.function.Function;

import javafx.collections.ObservableList;

/**
 * Interface representing a TaskList which is split into sub-groups by predefined groups.
 * Each 'GroupedTaskList' is a container for `ObservableList<Task>` objects,
 * each containing a unique filter over the full task list.
 * Classes which extend GroupedTaskList are expected to provide a getter method for each grouping defined.
 */
public interface GroupedTaskList extends Iterable<ObservableList<Task>> {
    static GroupedTaskList makeGroupedTaskList(ObservableList<Task> tasks, Grouping group) {
        return group.construct(tasks);
    }

    enum Grouping {
        /**
         * The const enum mappings provide a mapping from the commandArgument to the corresponding constructor
         */
        DATE("DATE_GROUPING", GroupedByDateTaskList::new),
        MODULE("MODULE_GROUPING", GroupedByModuleTaskList::new);

        public final String commandArgument;
        private final Function<ObservableList<Task>, GroupedTaskList> constructor;

        Grouping(String commandArgument, Function<ObservableList<Task>, GroupedTaskList> groupedTaskListConstructor) {
            this.commandArgument = commandArgument;
            this.constructor = groupedTaskListConstructor;
        }

        private GroupedTaskList construct(ObservableList<Task> tasks) {
            return constructor.apply(tasks);
        }
    }
}
