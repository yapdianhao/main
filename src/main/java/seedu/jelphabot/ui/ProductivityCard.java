package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.productivity.Productivity;

/**
 * An UI component that displays information of a {@code Productivity}.
 */
public class ProductivityCard extends UiPart<Region> {
    private static final String FXML = "ProductivityCard.fxml";

    public final Productivity productivity;

    @FXML
    private HBox cardPane;
    @FXML
    private Label tasksCompleted;
    @FXML
    private Label runningTimer;
    @FXML
    private Label timeSpentToday;
    @FXML
    private ProgressBar tasksCompletionProgress;

    public ProductivityCard(Productivity productivity) {
        super(FXML);
        this.productivity = productivity;
        tasksCompleted.setText(productivity.getTasksCompleted().toString());
        runningTimer.setText(productivity.getRunningTimers().toString());
        timeSpentToday.setText(productivity.getTimeSpentToday().toString());
        tasksCompletionProgress.setProgress(productivity.getTasksCompleted().getPercentage());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProductivityCard)) {
            return false;
        }

        // state check
        ProductivityCard card = (ProductivityCard) other;
        return tasksCompleted.getText().equals(card.tasksCompleted.getText())
                   && runningTimer.getText().equals(card.runningTimer.getText())
                   && timeSpentToday.getText().equals(card.timeSpentToday.getText())
                   && productivity.equals(card.productivity);
    }
}
