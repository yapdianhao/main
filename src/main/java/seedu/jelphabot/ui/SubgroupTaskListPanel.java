package seedu.jelphabot.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.tasklist.SubgroupTaskList;

import java.util.logging.Logger;

/**
 * Panel containing one grouped list of tasks.
 * Tasks are further sorted into pinned, dueToday, dueThisWeek, dueSomeday
 */
public class SubgroupTaskListPanel extends UiPart<Region> {

    private static final String FXML = "SubgroupTaskListPanel.fxml";
    private static final int PREF_CELL_HEIGHT = 100;
    private static final NumberBinding START_INDEX = Bindings.createIntegerBinding(() -> 1);

    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private TitledPane category;

    @FXML
    private ListView<Task> groupingList;

    private SubgroupTaskList subGroupTaskList;

    public SubgroupTaskListPanel(SubgroupTaskList subGroupTaskList) {
        super(FXML);
        this.subGroupTaskList = subGroupTaskList;
        category.setText(subGroupTaskList.getGroupName());
        groupingList.setCellFactory(viewCell -> new SubgroupTaskListViewCell());
        groupingList.prefHeightProperty().bind(subGroupTaskList.sizeBinding().multiply(PREF_CELL_HEIGHT));
        groupingList.setItems(subGroupTaskList.getList());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code GroupedTaskCard}.
     */
    class SubgroupTaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(
                    task,
                    START_INDEX.add(subGroupTaskList.startIndexBinding()).add(getIndex())
                ).getRoot());
            }
        }
    }
}

