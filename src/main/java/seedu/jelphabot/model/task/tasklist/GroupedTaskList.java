package seedu.jelphabot.model.task.tasklist;

import java.util.function.BiFunction;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;

/**
 * Interface representing a TaskList which is split into sub-groups by predefined groups.
 * Each 'GroupedTaskList' is a container for `ObservableList&lt;Task> objects,
 * each containing a unique filter over the full task list.
 * Classes which extend GroupedTaskList are expected to provide a getter method for each grouping defined.
 */
public interface GroupedTaskList extends ViewTaskList {
    static GroupedTaskList makeGroupedTaskList(ObservableList<Task> tasks, Category category,
        PinnedTaskList pinnedTasks) {
        return category.construct(tasks, pinnedTasks);
    }

    Category getCategory();

    ObservableList<SubgroupTaskList> getLists();

    /**
     * GroupedTaskList.Groupings define a set of fixed enum mappings from the commandArgument to the corresponding
     * constructor.
     */
    enum Category {
        DATE("date", GroupedByDateTaskList::new),
        MODULE("module", GroupedByModuleTaskList::new);

        public final String commandArgument;
        private final BiFunction<ObservableList<Task>, PinnedTaskList, GroupedTaskList> constructor;

        Category(String commandArgument,
            BiFunction<ObservableList<Task>, PinnedTaskList, GroupedTaskList> groupedTaskListConstructor) {
            this.commandArgument = commandArgument;
            this.constructor = groupedTaskListConstructor;
        }

        private GroupedTaskList construct(ObservableList<Task> tasks, PinnedTaskList pinnedTasks) {
            return constructor.apply(tasks, pinnedTasks);
        }
    }
}
