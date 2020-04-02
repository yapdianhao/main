package seedu.jelphabot.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.task.Task;

public class SummaryCard extends UiPart<Region> {

    private static final String FXML = "SummaryCard.fxml";

    ObservableList<Task> dueTodayTaskList;

    ObservableList<Task> completedTodayTaskList;

    @FXML
    private Label breakline;

    @FXML
    private Label tasksDueToday;

    @FXML
    private Label tasksCompletedToday;

    public SummaryCard(ObservableList<Task> dueTodayTaskList, ObservableList<Task> completedTodayTaskList) {
        super(FXML);
        this.dueTodayTaskList = dueTodayTaskList;
        this.completedTodayTaskList = completedTodayTaskList;
    }
}
