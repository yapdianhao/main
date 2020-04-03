package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.productivity.TasksCompleted;

/**
 * An UI component that displays information of a {@code TasksCompleted}.
 */
public class TaskCompletionCard extends UiPart<Region> {
    private static final String FXML = "TaskCompletionCard.fxml";

    public final TasksCompleted tasksCompleted;

    @FXML
    private HBox cardPane;
    @FXML
    private Label completed;
    @FXML
    private Label remark;
    @FXML
    private Label overdueStatus;
    @FXML
    private ProgressBar tasksCompletionProgress;

    public TaskCompletionCard(TasksCompleted tasksCompleted) {
        super(FXML);
        this.tasksCompleted = tasksCompleted;

        String[] completionProductivity = tasksCompleted.toStringArray();
        completed.setText("   " + completionProductivity[0]);
        remark.setText(completionProductivity[1]);
        overdueStatus.setText(completionProductivity[2]);
        tasksCompletionProgress.setProgress(tasksCompleted.getPercentage());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCompletionCard)) {
            return false;
        }

        // state check
        TaskCompletionCard card = (TaskCompletionCard) other;
        return completed.getText().equals(card.completed.getText())
                   && remark.getText().equals(card.remark.getText())
                   && overdueStatus.getText().equals(card.overdueStatus.getText());
    }
}
