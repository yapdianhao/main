package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.productivity.RunningTimers;

/**
 * An UI component that displays information of a {@code RunningTimers}.
 */
public class TimersCard extends UiPart<Region> {

    private static final String FXML = "TimersCard.fxml";

    public final RunningTimers runningTimers;

    @FXML
    private HBox cardPane;
    @FXML
    private Label timers;

    public TimersCard(RunningTimers runningTimers) {
        super(FXML);
        this.runningTimers = runningTimers;

        timers.setText(runningTimers.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimersCard)) {
            return false;
        }

        // state check
        TimersCard card = (TimersCard) other;
        return timers.getText().equals(card.timers.getText());
    }
}
