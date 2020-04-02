package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.task.GroupedTaskList;
import seedu.jelphabot.model.task.SubGroupTaskList;

/**
 * Panel containing the list of tasks.
 * Tasks are further sorted into subgroups
 */
public class GroupedTaskListPanel extends UiPart<Region> {

    private static final String FXML = "GroupedTaskListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @javafx.fxml.FXML
    private ListView<SubGroupTaskList> taskListGroups;

    private final GroupedTaskList groupedTaskList;

    public GroupedTaskListPanel(GroupedTaskList groupedTaskList) {
        super(FXML);
        this.groupedTaskList = groupedTaskList;
        taskListGroups.setCellFactory(viewCell -> new GroupedTaskListPanel.GroupedTaskListViewCell());
        taskListGroups.setItems(groupedTaskList.getList());
    }

    public GroupedTaskList.Category getCategory() {
        return groupedTaskList.getCategory();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code GroupedTaskCard}.
     */
    class GroupedTaskListViewCell extends ListCell<SubGroupTaskList> {
        @Override
        protected void updateItem(SubGroupTaskList task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SubgroupTaskListPanel(task).getRoot());
            }
        }
    }
}
