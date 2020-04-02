package seedu.jelphabot.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
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
    private ListView<SubgroupTaskListPanel> taskListGroups;


    public GroupedTaskListPanel(GroupedTaskList groupedTaskList) {
        super(FXML);

        ArrayList<SubgroupTaskListPanel> groupedPanels = new ArrayList<>();
        for (SubGroupTaskList subGroup : groupedTaskList) {
            groupedPanels.add(new SubgroupTaskListPanel(subGroup));
        }
        taskListGroups.setCellFactory(viewCell -> new GroupedTaskListPanel.GroupedTaskListViewCell());
        taskListGroups.setItems(FXCollections.observableArrayList(groupedPanels));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code GroupedTaskCard}.
     */
    class GroupedTaskListViewCell extends ListCell<SubgroupTaskListPanel> {
        @Override
        protected void updateItem(SubgroupTaskListPanel task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(task.getRoot());
            }
        }
    }
}
